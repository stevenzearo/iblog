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
import {WSChatMessage, WSChatMessageType} from "../../api/ws/WSChatMessage";
import {ChatMessage} from "./component/chatMessage";

export interface HomeProp {
    history: History;
}

export interface HomeState {
    user: User | null,
    data?: any | null,
    isLogin: boolean,
}

function initWS(): WebSocket {
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
                console.log(data.userJoinMessage.chatMember.name);
            } else if (data.type === WSChatMessageType.CHAT) {
                console.log(data.chatContentMessage.from.name);
                console.log(data.chatContentMessage.content);
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
        };
        this.chatMessageRef = React.createRef();
    }

    componentWillMount(): void {
        UserWebService.getCurrent(AuthWebService.getAuthFromLocalStorage(), (result => {
            if (!!result.status && result.status === 200 && !!result.data) {
                webSocket = initWS();
                this.setState((state: HomeState) => {
                    return {
                        user: result.data,
                        data: state.data,
                        isLogin: true,
                    };
                });

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
                            isLogin: false
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
        let message = "";
        if (webSocket == null || webSocket.readyState === webSocket.CLOSING || webSocket.readyState === webSocket.CLOSED) {
            webSocket = initWS();
        }
        webSocket.send(message)
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
                    <button className={"submit-button"} onClick={this.sendMsg}>SEND MSG</button>
                    <button className={"submit-button"} onClick={this.logout}>SIGN OUT</button>
                </div>
                <div className={'foot'}>this is foot</div>
            </div>
        );
    }
}

export default Home;
