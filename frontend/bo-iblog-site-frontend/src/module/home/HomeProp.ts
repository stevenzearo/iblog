import {User} from "./User";

export interface HomeProp {
    user: User | null;
}

export interface HomeState {
    user: User | null;
}
