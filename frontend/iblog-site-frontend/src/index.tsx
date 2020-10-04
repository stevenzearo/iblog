import React from 'react';
import ReactDOM from "react-dom";
import './index.css';
import * as serviceWorker from './serviceWorker';
import App from "./App";
import {AuthWebService} from "./api/AuthWebService";

const authId = localStorage.getItem("x-auth-id");
if (authId == null || authId.length === 0) {
    AuthWebService.setAuth();
}
ReactDOM.render(
    <App/>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
