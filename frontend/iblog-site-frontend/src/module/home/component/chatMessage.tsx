import React from "react";
import "./chatMessage.css"

export interface ChatMessageProp {
}

export interface ChatMessageState {
    messageNodes: React.ReactNode[] | null;
    userNodes: React.ReactNode[] | null;
    messageInputRef: any
}

export class ChatMessage extends React.Component<ChatMessageProp, ChatMessageState> {

    constructor(props: any, context: any) {
        super(props, context);
        this.state = {messageNodes: null, userNodes: null, messageInputRef: React.createRef()}
    }

    setMessageInputRef = (ref: any) => {
        this.setState((state) => {
            return {messageNodes: state.messageNodes, userNodes: state.userNodes, messageInputRef: ref}
        })
    };

    render(): React.ReactNode {
        return <div>
            <div className="chat-message">
                <div className="chat-head">chat message</div>
                <div>
                    <div className="group-info">
                        <div className="group-info-head">
                            group users
                        </div>
                        <div className="group-info-content">
                            {!!this.state.userNodes ? this.state.userNodes : ""}
                        </div>

                    </div>
                    <div className="chat-content">
                        <div className="chat-content-head">chat content</div>
                        <div className="chat-content-body">
                            {!!this.state.messageNodes ? this.state.messageNodes : ""}
                        </div>
                        <div className="input-content">
                            <input ref={this.setMessageInputRef} type="textarea"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>;
    }
}
