import axios from 'axios'

export interface AjaxProps {
    url: string,
    func: ({}) => {} | any | void,
    params?: any,
    data?: any,
    headers?: any,
    onError?: (error: Error | {}) => {} | any | void
}

export interface Error {
    statusCode: number,
    errorCode: string | null,
    message: string
}

export const Ajax = {
    get: (props: AjaxProps) => {
        axios.get(props.url, {params: props.params, data: props.data, headers: props.headers})
            .then(props.func)
            .catch(props.onError)
    }
};


