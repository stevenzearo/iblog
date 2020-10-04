import React from 'react';
import '../../index.css';
import '../../component/SubmitButton.css';
import './validateCode.css';
import {Link} from 'react-router-dom';
import {ValidateCode} from "./validateCode";
import TextInput from "../../component/TextInput";
import Email, {EmailState} from "./component/email";
import Password, {PasswordState} from "./component/password";
import Name from "../login/component/name";
import Age from "../login/component/age";
import {ConfirmPassword} from "../login/component/confirmPassword";
import {VerifyCode} from "../login/component/verifyCode";

export interface RegisterPageProps {
}


export interface RegisterPageState {

}

// todo use state replace jquery
class RegisterPage extends React.Component<RegisterPageProps, RegisterPageState> {
    private ageInput: any;
    private nameInput: any;
    private emailInput: any;
    private passwordInput: any;
    private confirmPasswordInput: any;
    private verifyCodeInput: any;


    constructor(props: any) {
        super(props);
        this.state = {};

        this.ageInput = React.createRef();
        this.emailInput = React.createRef();
        this.passwordInput = React.createRef();
        this.confirmPasswordInput = React.createRef();
        this.verifyCodeInput = React.createRef();
    }


    setNameInput = (ref: any) => {
        this.nameInput = ref;
    };

    setAgeInput = (ref: any) => {
        this.ageInput = ref;
    };


    setEmailInput = (ref: any) => {
        this.emailInput = ref;
    };


    setPasswordInput = (ref: any) => {
        this.passwordInput = ref;
    };

    setConfirmPasswordInput = (ref: any) => {
        this.confirmPasswordInput = ref;
    };


    setVerifyCodeInput = (ref: any) => {
        this.verifyCodeInput = ref;
    };

    checkName = () => {

    };

    checkAge = () => {

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

    checkConfirmPassword = () => {
        const password: string = this.passwordInput.textInput.input.value;
        const passwordToConfirm = this.confirmPasswordInput.textInput.input.value;


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

    checkVerifyCode = () => {

    };

    checkAll = () => {

    };

    register = () => {
        /*var data = {
            'user_name': userName,
            'password': password
        };*/
    };

    render() {
        return (
            <div className='register-page'>
                <div className='register-head'>REGISTER PAGE</div>

                <Name ref={this.setNameInput} onBlur={this.checkName}/>
                <Age ref={this.setAgeInput} onBlur={this.checkAge}/>
                <Email ref={this.setEmailInput} onBlur={this.checkEmail}/>
                <Password ref={this.setPasswordInput} onBlur={this.checkPassword}/>

                <ConfirmPassword ref={this.setConfirmPasswordInput} onBlur={this.checkConfirmPassword}/>
                <ValidateCode refreshSecond={30}/>
                <VerifyCode ref={this.setVerifyCodeInput} onBlur={this.checkVerifyCode}/>
                <button className='submit-button' onFocus={this.checkAll} onClick={this.register}>Submit</button>
                <Link to="/home">
                    <button className='submit-button'>Go Home</button>
                </Link>
            </div>
        );
    }
}


export default RegisterPage;
