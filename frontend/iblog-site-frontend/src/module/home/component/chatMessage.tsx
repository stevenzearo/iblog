import React from "react";
import {WSChatMessage} from "../../../api/ws/WSChatMessage";
import "./chatMessage.css"

export interface ChatMessageProp {
}

export interface ChatMessageState {
    messages: WSChatMessage[];
}

export class ChatMessage extends React.Component<ChatMessageProp, ChatMessageState> {

    constructor(props: any, context: any) {
        super(props, context);
        this.state = {messages: []}
    }

    pushMessage = (message: WSChatMessage): void => {
        this.setState((state: ChatMessageState) => {
            let chatMessages = state.messages;
            chatMessages[chatMessages.length] = message;
            return {messages: chatMessages};
        });
    };

    render(): React.ReactNode {
        return <div>
            <div className="chat-message">
                <div className="chat-head">chat message</div>
                <div>
                    <div className="group-users">group users</div>
                    <div className="chat-content">
                        <p className="message">chat content content content</p>

                    </div>
                </div>
            </div>
        </div>;
    }
}
