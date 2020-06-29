import {Ajax, AjaxProps, Method, RequestError} from "../../util/ajax";
export const headers = {userId: ""};
export class AdminWebService {
    static login(email: String, password: string, func: (result: any) => void): void {
        const props: AjaxProps = {
            url: "http://localhost:8410/admin/login",
            params: {email: email, password: password},
        };
        Ajax.ajax(Method.POST, props).then(func);
    }

    static logout(func: (result: any) => void): void {
        const props: AjaxProps = {
            url: "http://localhost:8410/admin/logout",
        };
        Ajax.ajax(Method.GET, props).then(func);
    }
}
