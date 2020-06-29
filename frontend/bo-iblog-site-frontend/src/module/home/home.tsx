import React from 'react';
import User from './user';
import UserComponent from "./userComponent";
import {AdminWebService} from "../../api/admin/AdminWebService";
import {Group, GroupWebService} from "../../api/admin/GroupWebService";

export interface HomeProp {
}

export interface HomeState {
    user: User | null,
    data?: any,
    isLogin: boolean
}

class Home extends React.Component<HomeProp, HomeState> {

    constructor(props: any) {
        super(props);
        this.state = {
            user: props.user,
            data: null,
            isLogin: false
        }
    }

    login = () => {
        if (this.state.isLogin) return;

        var isLogin = AdminWebService.login("qq@qq.com", "aaaa");
        this.setState((state) => {
            let groupData: Group[] = [];
            if (state.data == null) {
                groupData = GroupWebService.list();
            }
            return {
                user: state.user,
                data: groupData.length > 0 ? groupData : state.data,
                isLogin: isLogin
            }
        })
    };

    logout = () => {
        if (this.state.isLogin) {
            AdminWebService.logout();
        }
        this.setState((state) => {
            return {
                user: state.user,
                data: state.data,
                isLogin: false
            }
        })

    };

    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <UserComponent user={this.state.user}/>
                <button onClick={this.login}>login</button>
                <button onClick={this.logout}>logout</button>
                <span>id: {this.state.data == null ? null : this.state.data[0].id}</span><span>name: {this.state.data == null ? null : this.state.data[0].name}</span>
            </div>
        );
    }
}

export default Home;
