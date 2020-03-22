import React from 'react';
import CryptoJS from 'crypto-js';
import $ from 'jquery';
import './index.css';
import './component/SubmitButton.css';
import TextInput from './component/TextInput'

const refreshSecond = 30;

class RegisterPage extends React.Component {
    constructor(props) {
        super(props);
    }

    registerCheck = () => {
        var userName: string = $("#user-name").val();
        var password: string = $("#password").val();
        var passwordToConfirm: string = $("#password-to-confirm").val();
        var userValidateCode: string = $("#user-validate-code").val();
        if (userValidateCode.trim() === '') {
            alert("validate code can not be blank!");
        }

        // todo check validate code

        if (userName.trim() === '' || password === '') {
            alert("user name and password can not be blank!");
            return;
        }
        password = CryptoJS.SHA256(password).toString();
        alert(userName + ':' + password);
        var data = {
            'user_name': userName,
            'password': password
        };
        $.ajax({
            type: '',
            url: '',
            data: data,
            success: this.onSuccess,
            dataType: 'application/json'
        })
    };

    onSuccess = (result: User) => {
        alert(result.name + ":" + result.password)
    };

    render() {
        return (
            <div className='login-page'>
                <TextInput id='user-name' name='user-name' label='用户名' type='text' placeholder='请输入用户名'/>
                <TextInput id='password' name='password' label='密码' type='password' placeholder='请输入密码'/>
                <TextInput id='password-to-confirm' name='password-to-confirm' label='确认密码' type='password' placeholder='请确认密码'/>
                <ValidateCode/>
                <TextInput id='user-validate-code' name='user-validate-code' label='validate code' type='text'
                           placeholder='请输入验证码'/>
                <button className='submit-button' onClick={this.loginCheck}>提交</button>
            </div>
        );
    }
}

class ValidateCode extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            timeCounter: 0,
            refreshable: false,
            validateCode: "000000"
        }
    }


    tick() {
        this.setState(function (state) {
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
        this.setState(function (state) {
            var counter = state.timeCounter;
            if (counter <= 30) {
                alert("please refresh later");
                return state;
            }
            // todo get new validate code
            var validateCode = '000001';
            counter = 0;
            return {timeCounter: 0, refreshable: false, validateCode: validateCode}
        })
    };

    render() {
        var secondLeft = refreshSecond - this.state.timeCounter;
        return (
            <div>
                <label htmlFor='server-validate-code'>验证码</label>
                <input id='server-validate-code' type='text' contentEditable={false} value={this.state.validateCode}/>
                <span>{secondLeft > 0 ? secondLeft : 0}秒后可刷新</span>
                <button disabled={!this.state.refreshable} onClick={this.refreshValidateCode}>重新获取验证码</button>
            </div>
        );
    }

}

export default RegisterPage;