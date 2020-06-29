import {Ajax, AjaxProps, Method, RequestError} from "../../util/ajax";

export class AdminWebService {
    static login(email: String, password: string): boolean {
        let isLogin = false;
        const props: AjaxProps = {
            url: "http://localhost:8410/admin/login",
            func: (result) => {
                alert(JSON.stringify(result));
                alert(result.status);
                if (result.status === 200) {
                    isLogin = true;
                    alert(result.status);
                }
            },
            params: {email: email, password: password}
        };
        Ajax.ajax(Method.POST, props);
        alert("isLogin: "+ isLogin);
        return isLogin;
    }

    static logout(): void {
        const props: AjaxProps = {
            url: "http://localhost:8410/admin/logout",
            func: () => {
                alert("logout");
            },
        };
        Ajax.ajax(Method.GET, props);
    }
}
