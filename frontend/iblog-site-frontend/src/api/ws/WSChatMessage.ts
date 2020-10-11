export class WSChatMessage {
    public id: string;
    public groupId: string;
    public type: WSChatMessageType;
    public chatContentMessage: WSChatContentMessage;
    public userJoinMessage: WSUserJoinMessage;
    public createdTime: Date;


    constructor(id: string, groupId: string, type: WSChatMessageType, chatContentMessage: WSChatContentMessage, userJoinMessage: WSUserJoinMessage, createdTime: Date) {
        this.id = id;
        this.groupId = groupId;
        this.type = type;
        this.chatContentMessage = chatContentMessage;
        this.userJoinMessage = userJoinMessage;
        this.createdTime = createdTime;
    }
}

export class ChatMember {
    public userId: number;
    public name: string;


    constructor(userId: number, name: string) {
        this.userId = userId;
        this.name = name;
    }
}

export enum WSChatMessageType {
    USER_JOIN = "USER_JOIN",
    CHAT = "CHAT"
}

export class WSUserJoinMessage {
    public chatMember: ChatMember;

    constructor(chatMember: ChatMember) {
        this.chatMember = chatMember;
    }
}

export class WSChatContentMessage {
    public from: ChatMember;
    public to: ChatMember | null;
    public content: string;

    constructor(from: ChatMember, to: ChatMember | null, content: string) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}