import React from 'react';
import {AdminWebService} from "../../api/admin/AdminWebService";
import {GroupWebService, ListGroupResponse} from "../../api/admin/GroupWebService";
import Admin from "./admin";
import {History} from "history";

export interface HomeProp {
    history: History;
}

export interface HomeState {
    admin: Admin | null,
    data?: any | null,
    isLogin: boolean
}

class Home extends React.Component<HomeProp, HomeState> {

    constructor(props: any) {
        super(props);
        this.state = {
            admin: props.admin,
            data: null,
            isLogin: false
        };

    }

    componentDidMount(): void {
        if (!!this.props.history.location.state) {
            let historyState: HomeState | any = this.props.history.location.state;
            this.setState((state) => {
                return {admin: historyState.admin, data: historyState.data, isLogin: historyState.isLogin}
            })
        }
    }

    updateGroupInfo = (listGroupResponse: ListGroupResponse) => {
        this.setState(state => {
            return {
                admin: state.admin,
                data: listGroupResponse ? listGroupResponse.groups : state.data,
                isLogin: true
            }
        });
    };

    setIsLogin = (result: any) => {
        if (result.status === 200) {
            this.setState((state) => {
                return {
                    admin: state.admin,
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
        this.setState((state) => {
            return {
                admin: null,
                data: null,
                isLogin: false
            };
        });
        if (this.state.isLogin) {
            AdminWebService.logout();
        }
    };

    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <div>
                    <ul>
                        <li>admin：{}</li>
                        <li>email: {!!this.state.admin ? this.state.admin.email : ""}</li>
                    </ul>
                </div>
                <button onClick={this.login}>login</button>
                <button onClick={this.logout}>logout</button>
                <span>id: {!!this.state.data ? this.state.data[0].id : null}</span><span>name: {!!this.state.data ? this.state.data[0].name : null}</span>
            </div>
        );
    }
}

export default Home;
