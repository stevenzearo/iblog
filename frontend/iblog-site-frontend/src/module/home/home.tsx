import React from 'react';
import {History} from "history";
import "./home.css"
import "../../component/SubmitButton.css"
import {SlideComponent, SlideDistanceType, SlideFrom, SlideSettings} from "../../component/slide";
import User from "./component/user";
import {UserWebService} from "../../api/UserWebService";
import {AuthWebService} from "../../api/AuthWebService";
import {ErrorProcessService} from "../../common/ErrorProcessService";
import {WS_ENDPOINT_PREFIX} from "../../react-app-env";
import {ChatMember, WSChatContentMessage, WSChatMessage, WSChatMessageType} from "../../api/ws/WSChatMessage";
import {ChatMessage, ChatMessageState} from "./component/chatMessage";

export interface HomeProp {
    history: History;
}

export interface HomeState {
    user: User | null,
    data?: any | null,
    isLogin: boolean,
    messages: WSChatContentMessage[];
    chatGroupUsers: ChatMember[];
}

function setGroupUsers(chatMessageRef: any, chatGroupUsers: ChatMember[], chatMember: ChatMember) {
    let exist = chatGroupUsers.map(u => u.userId).filter(id => id === chatMember.userId).length > 0;
    if (exist) return;

    chatGroupUsers[chatGroupUsers.length] = chatMember;
    let userNodes: React.ReactNode[] = [];
    chatGroupUsers.forEach((value, index) => {
        userNodes[userNodes.length] = <div key={index} className="group-info-user">
            <p>id: {value.userId}</p>
            <p>name: {value.name}</p>
        </div>
    });
    chatMessageRef.setState((state: ChatMessageState) => {
        return {messageNodes: state.messageNodes, userNodes: userNodes}
    });
}

function setChatMessages(messages: WSChatContentMessage[], chatGroupUsers: ChatMember[], chatContentMessage: WSChatContentMessage, chatMessageRef: any) {
    setGroupUsers(chatMessageRef, chatGroupUsers, chatContentMessage.from);
    messages[messages.length] = chatContentMessage;

    let messageNodes: React.ReactNode[] = [];
    messages.forEach((value, index) => {
        messageNodes[messageNodes.length] = <div key={index} className="chat-info">
            <div className="chat-from-user">
                <p>id:{value.from.userId}</p>
                <p>name:{value.from.name}</p>
            </div>
            {
                !!value.to ?
                    <div>
                        <p>{value.to.userId}</p>
                        <p>{value.to.name}</p>
                    </div>
                    : ""
            }
            <div className="chat-content-message">says: {value.content}</div>
        </div>
    });
    chatMessageRef.setState((state: ChatMessageState) => {
        return {
            messageNodes: messageNodes,
            userNodes: state.userNodes,
            messageInputRef: state.messageInputRef
        }
    });
}

function initWS(chatMessageRef: any, chatGroupUsers: ChatMember[], messages: WSChatContentMessage[]): WebSocket {
    let webSocket = new WebSocket(`${WS_ENDPOINT_PREFIX}/ws/chat/group-0001/user-auth/${AuthWebService.getAuthFromLocalStorage()}`)
    webSocket.onopen = function (event: any) {
    };

    webSocket.onclose = function (event: any) {
    };

    webSocket.onmessage = function (event: MessageEvent) {
        let data: WSChatMessage = JSON.parse(event.data);
        if (data) {
            console.log(data.type);
            if (data.type === WSChatMessageType.USER_JOIN) {
                let chatMember = data.userJoinMessage.chatMember;
                setGroupUsers(chatMessageRef, chatGroupUsers, chatMember);
            } else if (data.type === WSChatMessageType.CHAT) {
                setChatMessages(messages, chatGroupUsers, data.chatContentMessage, chatMessageRef);
            }
        }
    };
    return webSocket;
}

let webSocket: WebSocket | null = null;

class Home extends React.Component<HomeProp, HomeState> {
    private chatMessageRef: any;

    constructor(props: any) {
        super(props);
        this.state = {
            user: props.user,
            data: null,
            isLogin: false,
            chatGroupUsers: [],
            messages: []
        };
        this.chatMessageRef = React.createRef();
    }

    componentWillMount(): void {
        UserWebService.getCurrent(AuthWebService.getAuthFromLocalStorage(), (result => {
            if (!!result.status && result.status === 200 && !!result.data) {
                webSocket = initWS(this.chatMessageRef, this.state.chatGroupUsers, this.state.messages);
                this.setState((state: HomeState) => {
                    return {
                        user: result.data,
                        data: state.data,
                        isLogin: true,
                        messages: state.messages,
                        chatGroupUsers: state.chatGroupUsers
                    };
                });

                if (!!this.state.user) {
                    var chatMember: ChatMember = new ChatMember(this.state.user.id, this.state.user?.name);
                    setGroupUsers(this.chatMessageRef, this.state.chatGroupUsers, chatMember);
                }
            } else if (!!result.data) {
                ErrorProcessService.processError(result.data, this.props.history);
            }
        }));
    }

    setChatMessageRef = (ref: any): void => {
        this.chatMessageRef = ref;
    };

    logout = () => {
        if (this.state.isLogin) {
            UserWebService.logout(AuthWebService.getAuthFromLocalStorage(), (result => {
                if (result.status === 200) {
                    this.setState((state) => {
                        return {
                            user: null,
                            data: null,
                            isLogin: false,
                            messages: state.messages,
                            chatGroupUsers: state.chatGroupUsers
                        };
                    });
                    this.props.history.push("/login", {isLogin: false});
                } else {
                    if (!!result.data) {
                        ErrorProcessService.processError(result.data, this.props.history);
                    }
                }

            }));
        }

    };

    sendMsg = () => {
        let message = this.chatMessageRef.state.messageInputRef.value;
        if (message == null || message.trim().length === 0) return;
        if (webSocket == null || webSocket.readyState === webSocket.CLOSING || webSocket.readyState === webSocket.CLOSED) {
            webSocket = initWS(this.chatMessageRef, this.state.chatGroupUsers, this.state.messages);
        }
        let hasSent = false;
        let triedTimes = 0;
        while (!hasSent && triedTimes <= 10) {
            if (!!webSocket && webSocket.readyState === webSocket.OPEN) {
                webSocket.send(message);
                hasSent = true;
            }
            triedTimes++;
        }
        if (!!this.state.user) {
            var from = new ChatMember(this.state.user.id, this.state.user.name);
            let content = new WSChatContentMessage(from, null, message);
            setChatMessages(this.state.messages, this.state.chatGroupUsers, content, this.chatMessageRef);
        }
    };

    getUserInfo = () => {
        const user = this.state.user;
        if (!!user) {
            return <ul>
                user info
                <li>id: {user.id}</li>
                <li>name: {user.name}</li>
                <li>email: {user.email}</li>
            </ul>;
        }
    };

    render() {
        return (
            <div className="content">
                <div className={'head'}>
                    <h1>Hello, {!!this.state.user ? this.state.user.name : ""}!</h1>

                </div>
                <div className={'menu'}>
                    <h1>this is menu</h1>
                    <SlideComponent slideSettings={new SlideSettings(100, SlideDistanceType.RATE, SlideFrom.LEFT)}/>
                </div>
                <div className={'center'}>
                    <div className={'user-info'}>{this.getUserInfo()}</div>
                    <ChatMessage ref={this.setChatMessageRef}/>
                    <div className={"btns"}>
                        <button className={"submit-button"} onClick={this.sendMsg}>SEND MSG</button>
                        <button className={"submit-button"} onClick={this.logout}>SIGN OUT</button>
                    </div>
                </div>
                <div className={'foot'}>this is foot</div>
            </div>
        );
    }
}

export default Home;
