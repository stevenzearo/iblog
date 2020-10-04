import React from 'react';
import CryptoJS from 'crypto-js';
import {History} from "history";
import './register.css'
import '../../component/SubmitButton.css';
import './validateCode.css';
import {Link} from 'react-router-dom';
import {ValidateCode} from "./validateCode";
import Email, {EmailState} from "./component/email";
import Password, {PasswordState} from "./component/password";
import Name from "../login/component/name";
import Age from "../login/component/age";
import {ConfirmPassword} from "../login/component/confirmPassword";
import {VerifyCode} from "../login/component/verifyCode";
import {UserWebService} from "../../api/UserWebService";
import {AuthWebService} from "../../api/AuthWebService";
import {RegisterUserRequest} from "../../api/user/RegisterUserRequest";
import {ErrorProcessService} from "../../common/ErrorProcessService";

export interface RegisterPageProps {
    history: History;
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
    private verifyCodeRef: any;
    private verifyCodeInput: any;


    constructor(props: any) {
        super(props);
        this.state = {};

        this.ageInput = React.createRef();
        this.emailInput = React.createRef();
        this.passwordInput = React.createRef();
        this.confirmPasswordInput = React.createRef();
        this.verifyCodeInput = React.createRef();
        this.verifyCodeRef = React.createRef();
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

    setVerifyCodeRef = (ref: any) => {
        this.verifyCodeRef = ref;
    };

    setVerifyCodeInput = (ref: any) => {
        this.verifyCodeInput = ref;
    };

    checkName = () => {
        // var name: string | null = this.nameInput.textInput.input.value;
        this.nameInput.setState((state: EmailState) => {
            return {nameIsValid: true, nameCheckResult: "valid"}
        });
    };

    checkAge = () => {
        var ageStr: string | null = this.ageInput.textInput.input.value;
        if (!!ageStr) ageStr = ageStr.trim();
        if (ageStr == null || ageStr.length === 0) {
            this.ageInput.setState((state: EmailState) => {
                return {ageIsValid: true, ageCheckResult: "valid"}
            });
            return;
        }

        const age = Number(ageStr);
        if (Number.isInteger(age)) {
            if (age <= 200) {
                this.ageInput.setState((state: EmailState) => {
                    return {ageIsValid: true, ageCheckResult: "valid"}
                });
            }
            return;
        }
        this.ageInput.setState((state: EmailState) => {
            return {ageIsValid: false, ageCheckResult: "age must be a integer and less than 200"}
        });
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
        // todo
        const password: string = this.passwordInput.textInput.input.value;
        if (password == null || password.trim() === '') {
            this.passwordInput.setState((state: PasswordState) => {
                return {passwordIsValid: false, passwordCheckResult: "password can not be null or blank"}
            });
        } else if (password.length < 8) {
            this.passwordInput.setState((state: PasswordState) => {
                return {passwordIsValid: false, passwordCheckResult: "password length must longer than 8"}
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


        if (passwordToConfirm.trim() === password.trim()) {
            this.confirmPasswordInput.setState((state: PasswordState) => {
                return {confirmPasswordIsValid: true, confirmPasswordCheckResult: "correct!"};
            });
        } else {
            this.confirmPasswordInput.setState((state: PasswordState) => {
                return {
                    confirmPasswordIsValid: false,
                    confirmPasswordCheckResult: "confirmed password need to be the same as password!"
                }
            });
        }
    };

    checkVerifyCode = () => {
        const userVerifyCode: string = this.verifyCodeInput.textInput.input.value;
        const verifyCode: string = this.verifyCodeRef.state.validateCode;
        if (userVerifyCode === verifyCode) {
            this.verifyCodeInput.setState((state: PasswordState) => {
                return {
                    verifyCodeIsValid: true,
                    verifyCodeCheckResult: "valid"
                }
            });
            return;
        }
        this.verifyCodeInput.setState((state: PasswordState) => {
            return {
                verifyCodeIsValid: false,
                verifyCodeCheckResult: "invalid verify code"
            }
        });
    };

    checkAll = (): boolean => {
        return this.nameInput.state.nameIsValid
            && this.ageInput.state.ageIsValid
            && this.emailInput.state.emailIsValid
            && this.passwordInput.state.passwordIsValid
            && this.confirmPasswordInput.state.confirmPasswordIsValid
            && this.verifyCodeInput.state.verifyCodeIsValid;
    };

    register = () => {
        var isValid = this.checkAll();
        if (!isValid) {
            alert("please check your register info again.");
            return;
        }
        var name: string | null = this.nameInput.textInput.input.value;
        if (name === null || name.trim().length === 0) {
            name = null;
        } else {
            name = name.trim();
        }

        const ageStr: string | null = this.ageInput.textInput.input.value;
        var age;
        if (ageStr === null || ageStr.length === 0) {
            age = null;
        } else {
            age = Number(ageStr);
        }
        var email: string = this.emailInput.textInput.input.value;
        email = email.trim();

        var password: string = this.passwordInput.textInput.input.value;
        password = password.trim();
        password = CryptoJS.SHA256(password).toString();

        var request = new RegisterUserRequest(name, age, email, password);
        UserWebService.register(AuthWebService.getAuthFromLocalStorage(), request, (result) => {
            if (!!result.status && result.status === 200) {
                alert(1);
                this.props.history.push("/login", {isLogin: false});
            } else if (!!result.data) {
                ErrorProcessService.processError(result.data, this.props.history);
                if (result.status === 409 && result.data.errorCode === "EMAIL_EXIST") {
                    alert(result.data.message);
                }
            }
        })
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
                <ValidateCode ref={this.setVerifyCodeRef} refreshSecond={30}/>
                <VerifyCode ref={this.setVerifyCodeInput} onBlur={this.checkVerifyCode}/>
                <div className='register-btn'>

                    <button className='submit-button' onClick={this.register}>Submit</button>
                    <Link to="/home">
                        <button className='submit-button'>Go Home</button>
                    </Link>
                </div>
            </div>
        );
    }
}


export default RegisterPage;
