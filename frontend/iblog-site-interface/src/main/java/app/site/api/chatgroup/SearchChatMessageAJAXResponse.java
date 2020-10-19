package app.site.api.chatgroup;

import app.validation.annotation.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author steve
 */
public class SearchChatMessageAJAXResponse {
    @NotNull
    public List<Message> messages = List.of();

    public static class Message {
        @NotNull
        public String id;

        @NotNull
        public ChatMember from;

        public ChatMember to;

        @NotNull
        public String content;

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
