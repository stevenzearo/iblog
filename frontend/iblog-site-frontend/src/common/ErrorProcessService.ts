import {RequestError} from "../util/ajax";
import {AuthWebService} from "../api/AuthWebService";

export class ErrorProcessService {
    static processError(errorData: RequestError | any, history: any) {
        this.processAuthInvalid(errorData);
        this.processLoginRequired(errorData, history);
    }

    static processAuthInvalid(errorData: RequestError | any): void {
        if (!!errorData.errorCode && (errorData.errorCode === "AUTH_INVALID" || errorData.errorCode === "AUTH_EXPIRED")) {
            AuthWebService.setAuth();
        }
    }

    static processLoginRequired(errorData: RequestError | any, history: any) {
        if (!!errorData.errorCode && errorData.errorCode === "LOGIN_REQUIRED") {
            history.push("/login", {isLogin: false});
        }
    }
}