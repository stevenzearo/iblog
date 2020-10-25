package app.site.service;

import app.site.api.chatgroup.CreateAndJoinGroupAJAXRequest;
import app.site.api.chatgroup.CreateAndJoinGroupAJAXResponse;
import app.site.api.chatgroup.ListChatGroupAJAXResponse;
import app.site.api.chatgroup.GetCroupChatMessageAJAXResponse;
import app.site.cache.ChatGroup;
import app.site.cache.ChatGroupCache;
import app.site.cache.ChatMessageCache;
import app.site.cache.User;
import app.site.cache.UserCache;
import app.site.cache.WSChatMessage;
import app.site.cache.WSChatMessageType;
import app.site.web.Context;
import app.site.web.ErrorCodes;
import app.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class ChatGroupService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserCache userCache;
    @Autowired
    ChatGroupCache chatGroupCache;
    @Autowired
    ChatMessageCache chatMessageCache;

    public CreateAndJoinGroupAJAXResponse createAndJoin(CreateAndJoinGroupAJAXRequest request, Long userId) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.id = UUID.randomUUID().toString();
        chatGroup.name = request.name;
        chatGroup.createdUserId = userId;
        chatGroup.createdTime = ZonedDateTime.now();

        chatGroupCache.save(chatGroup);
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(String.format(Context.CHAT_GROUP_SET + ":%s", chatGroup.id), String.valueOf(userId));
        return buildCreateAndJoinGroupAJAXResponse(chatGroup);
    }

    public ListChatGroupAJAXResponse list() {
        List<ChatGroup> groups = chatGroupCache.findAll();
        List<Long> groupOwnerIds = groups.stream().map(group -> group.createdUserId).collect(Collectors.toList());
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        Set<Long> groupUserIdSet = new HashSet<>(groupOwnerIds);
        Map<String, Set<Long>> groupUserIdMap = new HashMap<>();
        redisTemplate.multi();
        groups.forEach(group -> {
            Set<String> members = opsForSet.members(String.format(Context.CHAT_GROUP_SET + ":%s", group.id));
            if (members != null) {
                Set<Long> memberIdSet = members.stream().map(Long::valueOf).collect(Collectors.toSet());
                groupUserIdSet.addAll(memberIdSet);
                groupUserIdMap.put(group.id, memberIdSet);
            }
        });
        redisTemplate.exec();
        Map<Long, User> userMap = new HashMap<>();
        if (!groupUserIdSet.isEmpty()) {
            List<User> users = userCache.findAllByIdIn(new ArrayList<>(groupUserIdSet));
            userMap = users.stream().collect(Collectors.toMap(user -> user.id, user -> user));
        }
        return buildListChatGroupAJAXResponse(groups, groupUserIdMap, userMap);
    }

    public void join(String groupId, Long userId) throws NotFoundException {
        chatGroupCache.findById(groupId).orElseThrow(() -> new NotFoundException(ErrorCodes.CHAT_GROUP_NOT_FOUND, String.format("chat group not found, groupId = %s", groupId)));
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(userId));
    }

    public GetCroupChatMessageAJAXResponse getChatMessages(String groupId) throws NotFoundException {
        chatGroupCache.findById(groupId).orElseThrow(() -> new NotFoundException(ErrorCodes.CHAT_GROUP_NOT_FOUND, String.format("chat group not found, groupId = %s", groupId)));
        List<WSChatMessage> messages = chatMessageCache.findAllByGroupIdAndType(groupId, WSChatMessageType.CHAT);
        if (messages == null) messages = List.of();
        return buildGetCroupChatMessageAJAXResponse(messages);
    }

    public void leave(String groupId, Long userId) throws NotFoundException {
        chatGroupCache.findById(groupId).orElseThrow(() -> new NotFoundException(ErrorCodes.CHAT_GROUP_NOT_FOUND, String.format("chat group not found, groupId = %s", groupId)));
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.remove(String.format(Context.CHAT_GROUP_SET + ":%s", groupId), String.valueOf(userId));
    }

    private CreateAndJoinGroupAJAXResponse buildCreateAndJoinGroupAJAXResponse(ChatGroup chatGroup) {
        CreateAndJoinGroupAJAXResponse response = new CreateAndJoinGroupAJAXResponse();
        response.groupId = chatGroup.id;
        response.groupName = chatGroup.name;
        response.createdTime = ZonedDateTime.now();
        return response;
    }

    private ListChatGroupAJAXResponse buildListChatGroupAJAXResponse(List<ChatGroup> groups, Map<String, Set<Long>> groupUserIdMap, Map<Long, User> userMap) {
        ListChatGroupAJAXResponse response = new ListChatGroupAJAXResponse();
        response.groups = groups.stream().map(group -> {
            ListChatGroupAJAXResponse.Group groupView = new ListChatGroupAJAXResponse.Group();
            groupView.id = group.id;
            groupView.groupName = group.name;
            groupView.createdTime = group.createdTime;
            groupView.owner = buildChatMember(userMap.get(group.createdUserId));
            Set<Long> userIds = groupUserIdMap.get(group.id);
            groupView.chatMembers = userIds == null ? List.of() : userIds.stream().map(id -> buildChatMember(userMap.get(id))).collect(Collectors.toList());
            return groupView;
        }).collect(Collectors.toList());
        return response;
    }

    private ListChatGroupAJAXResponse.ChatMember buildChatMember(User user) {
        ListChatGroupAJAXResponse.ChatMember chatMember = new ListChatGroupAJAXResponse.ChatMember();
        if (user != null) {
            chatMember.userId = user.id;
            chatMember.name = user.name;
        }
        return chatMember;
    }

    private GetCroupChatMessageAJAXResponse buildGetCroupChatMessageAJAXResponse(List<WSChatMessage> messages) {
        GetCroupChatMessageAJAXResponse response = new GetCroupChatMessageAJAXResponse();
        response.messages = messages.stream().map(message -> {
            GetCroupChatMessageAJAXResponse.Message messageView = new GetCroupChatMessageAJAXResponse.Message();
            messageView.id = message.id;
            messageView.from = transferChatMember(message.chatContentMessage.from);
            messageView.to = transferChatMember(message.chatContentMessage.to);
            messageView.content = message.chatContentMessage.content;
            messageView.createdTime = message.createdTime;
            return messageView;
        }).collect(Collectors.toList());
        return response;
    }

    private GetCroupChatMessageAJAXResponse.ChatMember transferChatMember(WSChatMessage.ChatMember chatMember) {
        if (chatMember == null) return null;
        GetCroupChatMessageAJAXResponse.ChatMember chatMemberView = new GetCroupChatMessageAJAXResponse.ChatMember();
        chatMemberView.userId = chatMember.userId;
        chatMemberView.name = chatMember.name;
        return chatMemberView;
    }
}
