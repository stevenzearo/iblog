import {RegisterUserRequest} from "./user/RegisterUserRequest";
import {Ajax, AjaxProps, Method} from "../util/ajax";
import {SERVER_DOMAIN} from "../react-app-env";

export class UserWebService {
    static register(authId: string | null, request: RegisterUserRequest, func?: (result: any) => any): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/user`,
            headers: {"x-auth-id": authId},
            data: {name: request.name, age: request.age, email: request.email, password: request.password},
        };
        Ajax.ajax(Method.POST, props).then(func);
    }

    static getCurrent(authId: string | null, func?: (result: any) => any): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/user/current`,
            headers: {"x-auth-id": authId},
        };
        Ajax.ajax(Method.GET, props).then(func);
    }

    static login(authId: string | null, email: string, password: string, func?: (result: any) => any): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/user/login`,
            headers: {"x-auth-id": authId},
            params: {email: email, password: password}
        };
        Ajax.ajax(Method.POST, props).then(func);
    }

    static logout(authId: string | null, func?: (result: any) => void): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/user/logout`,
            headers: {"x-auth-id": authId},
        };
        Ajax.ajax(Method.PUT, props).then(func);
    }
}
