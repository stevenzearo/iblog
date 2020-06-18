import React from 'react';
import './App.css';
import RegisterPage from './module/register/register'

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <div className="login">
                    <RegisterPage/>
                </div>
            </header>
        </div>
    );
}

export default App;
