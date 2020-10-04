import {Ajax, AjaxProps, Method} from "../util/ajax";
import {SERVER_DOMAIN} from "../react-app-env";

export class AuthWebService {
    static getAuth(func?: (result: any) => any): void {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/auth`,
        };
        Ajax.ajax(Method.GET, props).then(func);
    }

    static setAuth(): void {
        AuthWebService.getAuth((result => {
            if (result.status === 200 && !!result.data) {
                localStorage.setItem("x-auth-id", result.data);
            }
        }));
    }

    static getAuthFromLocalStorage(): string | null {
        return localStorage.getItem("x-auth-id");
    }
}