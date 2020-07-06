import React from 'react';
import {AdminWebService} from "../../api/admin/AdminWebService";
import Admin from "./component/admin";
import {History} from "history";
import "./home.css"
import "../../component/SubmitButton.css"

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

    componentWillMount(): void {
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
        this.props.history.push("/login", {isLogin: false});
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

            return <ul>
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
            <div className={'content'}>
                <div className={'head'}>
                    <h1>Hello, {!!this.state.admin ? this.state.admin.name : ""}!</h1>

                </div>
                <div className={'menu'}>
                    <h1>this is menu</h1>

                </div>
                <div className={'center'}>

                    <div className={'admin-info'}>{this.getAdminInfo()}</div>
                    <button className={"submit-button"} onClick={this.logout}>SIGN OUT</button>
                </div>
                <div className={'foot'}>this is foot</div>

            </div>
        );
    }
}

export default Home;
