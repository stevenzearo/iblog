import React from 'react';
import CryptoJS from 'crypto-js';
import $ from 'jquery';
import '../../index.css';
import '../../component/SubmitButton.css';
import TextInput from '../../component/TextInput'
import {History} from "history";

export interface LoginPageProps {
    history: History
}

export interface LoginPageState {
    isToHome: boolean;
    homePath: string | any;
}

class LoginPage extends React.Component<LoginPageProps, LoginPageState> {

    constructor(props: LoginPageProps) {
        super(props);
        this.state = {
            isToHome: false,
            homePath: "/home"
        }
    }

    loginCheck = () => {
        let userName: any = $("#user-name").val();
        let password: any = $("#password").val();
        if (userName.trim() === '' || password === '') {
            alert("user name and password can not be blank!");
            return;
        }
        password = CryptoJS.SHA256(password).toString();
        // alert(userName + ':' + password);
        /*var data = {
            'user_name': userName,
            'password': password
        };*/
        /*$.ajax({
            type: 'GET',
            url: '',
            data: data,
            success: this.onSuccess,
            dataType: 'application/json'
        })*/
        this.onSuccess(null);
    };

    onSuccess = (result: any) => {
        // alert(result?.name + ":" + result?.password);
        this.setState((state: LoginPageState): LoginPageState => {
            return {
                isToHome: true,
                homePath: state.homePath
            };
        });
        this.props.history.push(this.state.homePath)
    };

    render() {
        return (
            <div className='login-page'>
                <TextInput id='user-name' name='user-name' label='用户名' type='text' placeholder='请输入用户名'/>
                <TextInput id='password' name='password' label='密码' type='password' placeholder='请输入密码'/>
                <button className='submit-button' onClick={this.loginCheck}>提交</button>
            </div>
        );
    }
}

export default LoginPage;
