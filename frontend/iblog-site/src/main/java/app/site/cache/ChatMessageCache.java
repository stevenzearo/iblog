package app.site.cache;

/**
 * @author steve
 */
public class ChatMessageCache {
    public String id;

    public String groupId;

    public ChatMember from;

    public ChatMember to;

    public byte[] content;

    public boolean isRead;

    public static class ChatMember {
        public String userId;

        public String name;
    }
}
