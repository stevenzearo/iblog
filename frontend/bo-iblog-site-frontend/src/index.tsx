import React from 'react';
import ReactDOM from "react-dom";
import {BrowserRouter, Route} from 'react-router-dom';
import './index/index.css';
import * as serviceWorker from './serviceWorker';
import App from "./App";
import Home from "./module/home/home";
import LoginPage from "./module/login/login";
import RegisterPage from "./module/register/register";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <Route exact path='/' component={App}/>
            <Route exact path='/home' component={Home}/>
            <Route exact path='/register' component={RegisterPage}/>
            <Route exact path='/login' component={LoginPage}/>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
