package app.site.api.chatgroup;

import app.validation.annotation.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
public class ListChatGroupAJAXResponse {
    @NotNull
    public List<Group> groups = List.of();

    public static class Group {
        @NotNull
        public String id;

        @NotNull
        public String groupName;

        @NotNull
        public List<ChatMember> chatMembers = List.of();

        @NotNull
        public ZonedDateTime createdTime;
    }

    public static class ChatMember {
        @NotNull
        public Long userId;

        @NotNull
        public String name;
    }
}