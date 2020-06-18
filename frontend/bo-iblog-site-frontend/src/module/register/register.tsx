import React from 'react';
import CryptoJS from 'crypto-js';
import $ from 'jquery';
import '../../index/index.css';
import '../../component/SubmitButton.css';
import '../../component/validateCode.css';
import TextInput from '../../component/TextInput';
import {Link} from 'react-router-dom';

const refreshSecond = 30;

class RegisterPage extends React.Component {
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
        var data = {
            'user_name': userName,
            'password': password
        };
    };

    render() {
        return (
            <div className='login-page'>
                <TextInput id='user-name' name='user-name' label='用户名' type='text' placeholder='请输入用户名'/>
                <TextInput id='password' name='password' label='密码' type='password' placeholder='请输入密码'/>
                <PasswordConfirmation/>
                <ValidateCode/>
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

class ValidateCode extends React.Component {
    private timeId: any;
    public state: any;

    constructor(props: any) {
        super(props);
        this.state = {
            timeCounter: 0,
            refreshable: false,
            validateCode: '000000'
        }
    }


    tick() {
        this.setState(function (state: any) {
            var refreshable = false;
            if (state.timeCounter >= refreshSecond) refreshable = true;
            return {timeCounter: state.timeCounter++, refreshable: refreshable, validateCode: state.validateCode}
        })
    }

    // parent method
    componentDidMount() {
        this.timeId = setInterval(() => this.tick(), 1000);
    }

    // parent method
    componentWillUnmount() {
        clearInterval(this.timeId);
    }

    getValidateCode = () => {
        // todo use session to get a validate code
    };

    refreshValidateCode = () => {
        this.setState(function (state: any) {
            var counter = state.timeCounter;
            if (counter <= 30) {
                alert('please refresh later');
                return state;
            }
            // todo get new validate code
            var validateCode = '000001';
            counter = 0;
            return {timeCounter: 0, refreshable: false, validateCode: validateCode}
        })
    };

    render() {
        const state = this.state;
        var secondLeft = refreshSecond - state.timeCounter;
        return (
            <div id='validate-code'>
                <div className='validate-code'>
                    <label htmlFor='server-validate-code'>验证码:</label>
                    <span id='server-validate-code'>{this.state.validateCode}</span>
                </div>
                <div className='count-down'>
                    <span>{secondLeft > 0 ? secondLeft : 0}秒后可刷新</span>

                    <button disabled={!this.state.refreshable} onClick={this.refreshValidateCode}>重新获取验证码</button>
                </div>
            </div>
        );
    }

}

class PasswordConfirmation extends React.Component<any, any> {
    style: any = {float: 'none'};

    constructor(props: any) {
        super(props);
        this.state = {
            confirmStatus: false,
            confirmResult: "please check whether confirmed password is the same as password!"
        }
    }

    confirmPassword = () => {
        const password: any = $("#password").val();
        const passwordToConfirm: any = $("#password-to-confirm").val();
        this.setState((state: any) => {
            if (password && passwordToConfirm && password === passwordToConfirm) {
                return {confirmStatus: true, confirmResult: "correct!"}
            }
            return {
                confirmStatus: false,
                confirmResult: "please check whether confirmed password is the same as password!"
            };
        })
    };

    render(): React.ReactNode {
        return <div onBlur={this.confirmPassword} style={this.style}>
            <TextInput id='password-to-confirm' name='password-to-confirm' label='确认密码' type='password'
                       placeholder='请确认密码'/>
            <span>{this.state.confirmResult}</span>
        </div>
    }
}

export default RegisterPage;