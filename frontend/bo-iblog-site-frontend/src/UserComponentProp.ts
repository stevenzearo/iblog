import {User} from "./model/User";

export interface UserComponentProp {
    user: User | null;
}

export interface UserComponentState {
    user: User | null;
}