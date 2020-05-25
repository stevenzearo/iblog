import React from 'react';
import './App.css';
import LoginPage from './login'
import RegisterPage from './register'

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <div className="login">
                    <LoginPage/>
                    <RegisterPage/>
                </div>
            </header>
        </div>
    );
}

export default App;
