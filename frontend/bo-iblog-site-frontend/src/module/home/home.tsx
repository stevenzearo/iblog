import React from 'react';
import {AdminWebService} from "../../api/admin/AdminWebService";
import {GroupWebService, ListGroupResponse} from "../../api/admin/GroupWebService";
import Admin from "./component/admin";
import {History} from "history";
import "./home.css"

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
        AdminWebService.getCurrent((result => {
            if (result.status && result.status === 200 && !!result.data) {
                this.setState((state: HomeState) => {
                    return {admin: result.data, data: state.data, isLogin: true};
                })
            } else {
                this.props.history.push("/login", {isLogin: false});
            }
        }));
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

    getAdminInfo() {
        const admin = this.state.admin;
        if (!!admin) {
            const roles = admin.group.roles;
            let roleNodes = {};
            if (!!roles) {
                roleNodes = roles.map(role => {
                    return <li>{role.name}: {role.authority}</li>;
                });
            }


            return <ul className={'admin-info'}>
                admin info
                <li>id: {admin.id}</li>
                <li>name: {admin.name}</li>
                <li>email: {admin.email}</li>
                <li>
                    group: {admin.group.name}
                    {
                        !!roles ? <ul>roles:{roleNodes}</ul> : null
                    }
                </li>
            </ul>;
        }
    }

    render() {
        return (
            <div>
                <h1>Hello, {!!this.state.admin ? this.state.admin.name : ""}!</h1>
                {this.getAdminInfo()}
                <button onClick={this.logout}>logout</button>
                <span>id: {!!this.state.data ? this.state.data[0].id : null}</span><span>name: {!!this.state.data ? this.state.data[0].name : null}</span>
            </div>
        );
    }
}

export default Home;
