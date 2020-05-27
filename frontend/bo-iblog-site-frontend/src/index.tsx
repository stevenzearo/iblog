import React from 'react';
import ReactDOM from "react-dom";
import { Router, Route } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import './index/index.css';
import * as serviceWorker from './serviceWorker';
import App from "./App";
import Home from "./home";
import LoginPage from "./login";
import RegisterPage from "./register";

ReactDOM.render(
    <React.StrictMode>
        <Router history={createBrowserHistory()}>
            <Route exact path='/' component={App}/>
            <Route exact path='/home' component={Home}/>
            <Route exact path='/register' component={RegisterPage}/>
            <Route exact path='/login' component={LoginPage}/>
        </Router>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
