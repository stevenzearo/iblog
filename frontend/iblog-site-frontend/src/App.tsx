import React from 'react';
import './App.css';
import RegisterPage from './module/register/register'

class App extends React.Component {
    render(): React.ReactNode {
        return (
            <div className="App">
                <header className="App-header">
                    <div>
                        <RegisterPage/>
                    </div>
                </header>
            </div>
        );
    }


}

export default App;
