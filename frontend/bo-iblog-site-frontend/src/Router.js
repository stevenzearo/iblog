import React from 'react';
import { BrowserRouter as Router, Route} from "react-router-dom";
import App from './App';
import Home from './home';

function router() {
    return <Router>
        <Route exact path='/' component={App}/>
        <Route exact path='/home' component={Home}/>
    </Router>
}

export default router;