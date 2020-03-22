import React from 'react';
import logo from './logo.svg';
import './App.css';
import LoginPage from './login.js'
import RegisterPage from './register.js'

function App() {
    return (
        <div className="App">
            <div className="login">
                <LoginPage/>
                <RegisterPage/>
            </div>
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
                </a>
            </header>
        </div>
    );
}

export default App;
