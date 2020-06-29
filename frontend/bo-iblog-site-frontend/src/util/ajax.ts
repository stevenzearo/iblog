import axios from 'axios'

export interface AjaxProps {
    url: string,
    func: (value: any) => void,
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
    get: (props: AjaxProps): void => {
        axios.get(props.url, {params: props.params, data: props.data, headers: props.headers, withCredentials: true})
            .then(props.func);
    },

    ajax: (method: Method, props: AjaxProps): void => {
        axios(props.url, {method: method, params: props.params, data: props.data, headers: props.headers, withCredentials: true})
            .then(props.func, (result) => {alert(JSON.stringify(result))});
    }
};


