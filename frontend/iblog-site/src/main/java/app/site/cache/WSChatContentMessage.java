package app.site.cache;

/**
 * @author steve
 */
public class WSChatContentMessage {
    public WSChatMessage.ChatMember from;

    public WSChatMessage.ChatMember to;

    public String content;

    @Override
    public String toString() {
        return "WSChatContentMessage{" +
            "from=" + from +
            ", to=" + to +
            ", content='" + content + '\'' +
            '}';
    }
}
