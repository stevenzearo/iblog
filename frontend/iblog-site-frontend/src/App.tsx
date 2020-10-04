import React from 'react';
import './App.css';
import RegisterPage from './module/register/register'
import {BrowserRouter, Route} from 'react-router-dom';
import Home from "./module/home/home";
import LoginPage from "./module/login/login";

class App extends React.Component {
    render(): React.ReactNode {
        return (
            <React.StrictMode>
                <BrowserRouter>
                    <Route path="/" exact component={RegisterPage}/>
                    <Route exact path='/home' component={Home}/>
                    <Route exact path='/register' component={RegisterPage}/>
                    <Route exact path='/login' component={LoginPage}/>
                </BrowserRouter>
            </React.StrictMode>
        );
    }


}

export default App;
