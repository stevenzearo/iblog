import React from 'react';
import '../../index.css';
import './login.css';
import '../../component/SubmitButton.css';
import TextInput from '../../component/TextInput'
import {History} from "history";
import {Email} from './component/email';

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

    }

    setEmailInput = (ref: any) => {
        this.emailInput = ref;
    };

    loginCheck = () => {
    };

    onSuccess = (result: any) => {
    };

    alertEmail = () => {
      alert(this.emailInput.textInput.input.value);
    };

    render() {
        return (
            <div className='login-page'>
                <div className='login-head'>sigin in</div>
                <Email ref={this.setEmailInput} onBlur={this.alertEmail}/>
                <TextInput id='password' name='password' label='密码' type='password' placeholder='请输入密码'/>
                <button className='submit-button' onClick={this.loginCheck}>提交</button>
            </div>
        );
    }
}

export default LoginPage;
