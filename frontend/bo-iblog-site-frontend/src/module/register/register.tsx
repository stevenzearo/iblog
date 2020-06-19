import React from 'react';
import CryptoJS from 'crypto-js';
import $ from 'jquery';
import '../../index.css';
import '../../component/SubmitButton.css';
import './validateCode.css';
import TextInput from '../../component/TextInput';
import {Link} from 'react-router-dom';
import {ValidateCode} from "./validateCode";

export interface RegisterPageProps {
}


export interface RegisterPageState {
    confirmStatus: boolean;
    confirmResult: string;
}
// todo use state replace jquery
class RegisterPage extends React.Component<RegisterPageProps, RegisterPageState> {
    constructor(props: any) {
        super(props);
        this.state = {
            confirmStatus: false,
            confirmResult: "please check whether confirmed password is the same as password!"
        }
    }

    registerCheck = () => {
        let userName: any = $('#user-name').val();
        let password: any = $('#password').val();
        // var passwordToConfirm: string = $('#password-to-confirm').val();
        let userValidateCode: any = $('#user-validate-code').val();
        if (userValidateCode.trim() === '') {
            alert('validate code can not be blank!');
        }

        // todo check validate code

        if (userName.trim() === '' || password === '') {
            alert('user name and password can not be blank!');
            return;
        }
        password = CryptoJS.SHA256(password).toString();
        alert(userName + ':' + password);
        /*var data = {
            'user_name': userName,
            'password': password
        };*/
    };

    confirmPassword = () => {
        const password: any = $("#password").val();
        const passwordToConfirm: any = $("#password-to-confirm").val();
        this.setState((state: RegisterPageState) => {
            if (password && passwordToConfirm && password === passwordToConfirm) {
                return {confirmStatus: true, confirmResult: "correct!"}
            }
            return {
                confirmStatus: false,
                confirmResult: "please check whether confirmed password is the same as password!"
            };
        })
    };

    render() {
        return (
            <div className='login-page'>
                <TextInput id='user-name' name='user-name' label='用户名' type='text' placeholder='请输入用户名'/>
                <TextInput id='password' name='password' label='密码' type='password' placeholder='请输入密码'/>
                <TextInput id='password-to-confirm' name='password-to-confirm' label='确认密码' type='password'
                           placeholder='请确认密码' onBlur={this.confirmPassword}/>
                <ValidateCode refreshSecond={30}/>
                <TextInput id='user-validate-code' name='user-validate-code' label='验证码' type='text'
                           placeholder='请输入验证码'/>
                <button className='submit-button' onClick={this.registerCheck}>提交</button>
                <Link to="/home">
                    <button className='submit-button'>Go Home</button>
                </Link>
            </div>
        );
    }
}


export default RegisterPage;
