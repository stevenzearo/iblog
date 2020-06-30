import React from 'react';
import User from './user';
import UserComponent from "./userComponent";
import {AdminWebService} from "../../api/admin/AdminWebService";
import {GroupWebService, ListGroupResponse} from "../../api/admin/GroupWebService";

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

    updateGroupInfo = (listGroupResponse: ListGroupResponse) => {
        this.setState(state => {
            return {
                user: state.user,
                data: listGroupResponse ? listGroupResponse.groups : state.data,
                isLogin: true
            }
        });
    };

    setIsLogin = (result: any) => {
        if (result.status === 200) {
            this.setState((state) => {
                return {
                    user: state.user,
                    data: null,
                    isLogin: true
                };
            })
        }
        GroupWebService.list(this.updateGroupInfo);
    };

    login = () => {
        if (this.state.isLogin) return;
        // AdminWebService.login("qq12@qq.com", "1234", this.setIsLogin);
        AdminWebService.login("qq@qq.com", "aaaa", this.setIsLogin);
    };

    logout = () => {
        if (this.state.isLogin) {
            AdminWebService.logout((result) => {
                if (result.status === 200) {
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
