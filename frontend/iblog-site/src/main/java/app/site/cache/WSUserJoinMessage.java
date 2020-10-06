package app.site.cache;

/**
 * @author steve
 */
public class WSUserJoinMessage {
    public WSChatMessage.ChatMember chatMember;

    @Override
    public String toString() {
        return "WSUserJoinMessage{" +
            "chatMember=" + chatMember +
            '}';
    }
}
