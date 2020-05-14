import React from 'react';

import logo from './logo.svg';
import './App.css';
import LoginPage from './login'
import RegisterPage from './register'
import {UserWebService} from "./api/UserWebService";
import {GetUserResponse} from "./api/user/GetUserResponse";

function App() {
    var userWebService: UserWebService = new UserWebService();
    var data: GetUserResponse | null = userWebService.get(1);
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    Edit <code>src/App.js</code> and save to reload.
                    {data == null ? "no value" : data.id + ":" + data.name + ":" + data.age + ":" + data.email}
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
                <div className="login">
                    <LoginPage/>
                    <RegisterPage/>
                </div>
            </header>
        </div>
    );
}

export default App;
