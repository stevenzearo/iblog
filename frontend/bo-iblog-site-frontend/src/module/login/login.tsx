import React from 'react';
import '../../index.css';
import './login.css';
import '../../component/SubmitButton.css';
import {History} from "history";
import {Email, EmailState} from './component/email';
import Password, {PasswordState} from "./component/password";
import {AdminWebService} from "../../api/admin/AdminWebService";

export interface LoginPageProps {
    history: History;
}

export interface LoginPageState {
    isLogin: boolean;
}

export class LoginPage extends React.Component<LoginPageProps, LoginPageState> {
    private emailInput: any;
    private passwordInput: any;

    constructor(props: LoginPageProps) {
        super(props);
        this.state = {
            isLogin: false
        };
        this.emailInput = React.createRef();
        this.passwordInput = React.createRef();

    }


    componentWillMount(): void {

    }

    componentDidMount(): void {
        if (!!this.props.history.location.state) {
            let historyState: LoginPageState | any = this.props.history.location.state;
            if (!!historyState.isLogin) {
                this.setState((state) => {
                    return {isLogin: historyState.isLogin}
                });
            }
        }

        AdminWebService.getCurrent((result => {
            if (result.status === 200 && !!result.data) {
                this.setState((state: LoginPageState) => {
                    return {isLogin: true};
                });
                this.props.history.push("/home", {admin: null, data: null, isLogin: true});
            } else {
                this.setState((state: LoginPageState) => {
                    return {isLogin: false};
                });
            }
        }));
    }

    setEmailInput = (ref: any) => {
        this.emailInput = ref;
    };


    setPasswordInput = (ref: any) => {
        this.passwordInput = ref;
    };

    login = () => {
        if (this.state.isLogin) {
            this.props.history.push("/home");
        }
        const email: string = this.emailInput.textInput.input.value;
        const password: string = this.passwordInput.textInput.input.value;
        AdminWebService.login(email, password, (result) => {
            if (result.status && result.status === 200 || result.status === 409) {
                this.setState({isLogin: true});
                this.props.history.push("/home", {admin: null, data: null, isLogin: true});
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
                <button className='submit-button' onClick={this.login}>提交</button>
            </div>
        );
    }
}

export default LoginPage;
