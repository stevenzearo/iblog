import {User} from "./module/User";

export interface HomeProp {
    user: User | null;
}

export interface HomeState {
    user: User | null;
}