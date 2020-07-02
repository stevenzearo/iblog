import {CreateUserRequest} from "./user/CreateUserRequest";
import {GetUserResponse} from "./user/GetUserResponse";

export class UserWebService {
    get(id: number): GetUserResponse | null {
        return null;
    }

    create(user: CreateUserRequest) {
    }
}
