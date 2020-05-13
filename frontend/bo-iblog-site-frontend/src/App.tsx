import React from 'react';

import logo from './logo.svg';
import './App.css';
import LoginPage from './login'
import RegisterPage from './register'
import {UserWebService} from "./api/UserWebService";

function App() {
    var userWebService: UserWebService = new UserWebService();
    var data: any = userWebService.get(1);
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    Edit <code>src/App.js</code> and save to reload.
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                    {data}
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
