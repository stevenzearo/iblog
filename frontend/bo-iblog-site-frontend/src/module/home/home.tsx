import React from 'react';
import User from './user';
import UserComponent from "./userComponent";
import {AdminWebService} from "../../api/admin/AdminWebService";
import {GroupWebService} from "../../api/admin/GroupWebService";

export interface HomeProp {
}

export interface HomeState {
    user: User | null,
    data?: any | null,
    isLogin: boolean
}

class Home extends React.Component<HomeProp, HomeState> {

    constructor(props: any) {
        super(props);
        this.state = {
            user: props.user,
            data: null,
            isLogin: false
        };
    }

    static listGroups(func: (value: any) => void) {
        return GroupWebService.list(func);
    }

    login = () => {
        if (this.state.isLogin) return;

        AdminWebService.login("qq12@qq.com", "1234", (result) => {
            if (result.status === 200 || result.status === 409) {
                this.setState((state) => {
                    return {
                        user: state.user,
                        data: null,
                        isLogin: true
                    }
                });
                Home.listGroups(
                    (result) => {
                        this.setState(state => {
                            return {
                                user: state.user,
                                data: result.data ? result.data.groups : state.data,
                                isLogin: state.isLogin
                            }
                        })
                    }
                );
            }
        });

    };

    logout = () => {
        if (this.state.isLogin) {
            AdminWebService.logout((result) => {
                if (result.status == 200) {
                    this.setState((state) => {
                        return {
                            user: state.user,
                            data: null,
                            isLogin: false
                        };
                    })
                }

            });
        }
    };

    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <UserComponent user={this.state.user}/>
                <button onClick={this.login}>login</button>
                <button onClick={this.logout}>logout</button>
                <span>id: {!!this.state.data ? this.state.data[0].id : null}</span><span>name: {!!this.state.data ? this.state.data[0].name : null}</span>
            </div>
        );
    }
}

export default Home;
