import axios from 'axios'

export interface AjaxProps {
    url: string,
    params?: any,
    data?: any,
    headers?: any,
}

export interface RequestError {
    statusCode: number,
    errorCode: string | null,
    message: string
}

export enum Method {
    GET = "GET",
    PUT = "PUT",
    POST = "POST",
    DELETE = "DELETE"
}

export const Ajax = {
    ajax: (method: Method, props: AjaxProps): Promise<any> => {
        return axios({
            url: props.url,
            method: method,
            params: props.params,
            data: props.data,
            headers: props.headers,
            withCredentials: true,
            validateStatus: status => {
                return !!status
            }
        });
    }
};
