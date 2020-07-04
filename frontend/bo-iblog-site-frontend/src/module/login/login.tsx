import React from 'react';
import '../../index.css';
import './login.css';
import '../../component/SubmitButton.css';
import {History} from "history";
import {Email, EmailState} from './component/email';
import Password, {PasswordState} from "./component/password";
import {AdminWebService} from "../../api/admin/AdminWebService";
import Admin from "../home/admin";

export interface User {
    email: string | null;
    password: string | null;
}

export interface LoginPageProps {
    history: History;
}

export interface LoginPageState {
    isToHome: boolean;
    homePath: string | any;
    user: User | null;
}

export class LoginPage extends React.Component<LoginPageProps, LoginPageState> {
    private emailInput: any;
    private passwordInput: any;

    constructor(props: LoginPageProps) {
        super(props);
        this.state = {
            isToHome: false,
            homePath: "/home",
            user: {
                email: null,
                password: null
            }
        };
        this.emailInput = React.createRef();
        this.passwordInput = React.createRef();

    }

    setEmailInput = (ref: any) => {
        this.emailInput = ref;
    };


    setPasswordInput = (ref: any) => {
        this.passwordInput = ref;
    };

    loginCheck = () => {
        const email: string = this.emailInput.textInput.input.value;
        const password: string = this.passwordInput.textInput.input.value;
        AdminWebService.login(email, password, (result) => {
            if (result.status && result.status === 200 || result.status === 409) {
                this.props.history.push("/home", {admin: new Admin(null, null, null, "qq@qq.com", null), isLogin: true});
            } else {
                alert("login failed!!!");
            }
        });
    };

    onSuccess = (result: any) => {
    };

    checkEmail = () => {
        const email: string = this.emailInput.textInput.input.value;
        if (email == null || email.trim() === '') {
            this.emailInput.setState((state: EmailState) => {
                return {emailIsValid: false, emailCheckResult: "email can not be null or blank"}
            });
        } else {
            this.emailInput.setState((state: EmailState) => {
                return {emailIsValid: true, emailCheckResult: "valid"}
            });
        }
    };


    checkPassword = () => {
        const password: string = this.passwordInput.textInput.input.value;
        if (password == null || password.trim() === '') {
            this.passwordInput.setState((state: PasswordState) => {
                return {passwordIsValid: false, passwordCheckResult: "password can not be null or blank"}
            });
        } else {

            this.passwordInput.setState((state: PasswordState) => {
                return {passwordIsValid: true, passwordCheckResult: "valid"};
            });
        }
    };

    render() {
        return (
            <div className='login-page'>
                <div className='login-head'>sigin in</div>
                <Email ref={this.setEmailInput} onBlur={this.checkEmail}/>
                <Password ref={this.setPasswordInput} onBlur={this.checkPassword}/>
                <button className='submit-button' onClick={this.loginCheck}>提交</button>
            </div>
        );
    }
}

export default LoginPage;
