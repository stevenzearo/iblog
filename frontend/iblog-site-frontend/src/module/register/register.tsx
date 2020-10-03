import React from 'react';
import CryptoJS from 'crypto-js';
import '../../index.css';
import '../../component/SubmitButton.css';
import './validateCode.css';
import {Link} from 'react-router-dom';
import {ValidateCode} from "./validateCode";
import TextInput from "../../component/TextInput";

export interface RegisterPageProps {
}


export interface RegisterPageState {
    name: string | any;
    email: string | any;
    password: string | any;
    passwordToConfirm: String | any;
    validateCode: String | any;
    userValidateCode: String | any;
    confirmStatus: boolean;
    confirmResult: string;
}

// todo use state replace jquery
class RegisterPage extends React.Component<RegisterPageProps, RegisterPageState> {
    constructor(props: any) {
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            passwordToConfirm: "",
            validateCode: "",
            userValidateCode: "",
            confirmStatus: false,
            confirmResult: "please check whether confirmed password is the same as password!"
        }
    }

    setUserName = () => {
        // var userName = e.prototype.val();
        alert(1)
    };
    confirmPassword = () => {
        const password = this.state.password;
        const passwordToConfirm = this.state.passwordToConfirm;
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

    registerCheck = () => {
        if (this.state.userValidateCode.trim() === '') {
            alert('validate code can not be blank!');
        }

        // todo check validate code

        if (this.state.name.trim().isEmpty() || this.state.password.trim().isEmpty()) {
            alert('user name and password can not be blank!');
            return;
        }
        const password = CryptoJS.SHA256(this.state.password).toString();
        alert(this.state.name + ':' + password);
        /*var data = {
            'user_name': userName,
            'password': password
        };*/
    };

    render() {
        return (
            <div className='login-page'>
                <TextInput id='user-name' name='user-name' label='用户名' type='text' placeholder='请输入用户名'
                           onBlur={this.setUserName.bind(this)}/>
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
