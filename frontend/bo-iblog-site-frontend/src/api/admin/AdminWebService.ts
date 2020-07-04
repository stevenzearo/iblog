import {Ajax, AjaxProps, Method} from "../../util/ajax";
import {SERVER_DOMAIN} from "../../react-app-env";

export class AdminWebService {
    static login(email: String, password: string, func: (result: any) => void): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/admin/login`,
            params: {email: email, password: password},
        };
        Ajax.ajax(Method.POST, props).then(func);
    }

    static logout(func?: (result: any) => void): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/admin/logout`,
        };
        Ajax.ajax(Method.GET, props).then(func);
    }
}
