import React from "react";
import {User} from "./user";

export interface UserComponentProp {
    user: User | null;
}

export interface UserComponentState {
    user: User | null;
}

export class UserComponent extends React.Component<UserComponentProp, UserComponentState> {

    constructor(props: any, context: any) {
        super(props, context);
        this.state = {user: props.user}
    }

    setUser(user: User) {
        this.setState((state: UserComponentState): UserComponentState | any => {
            return {id: user.id, name: user.name, age: user.age, email: user.email}
        })
    }

    // parent method
    componentDidMount() {
    }

    // parent method
    componentWillUnmount() {
    }

    render() {
        return (
            <div>
                <ul>
                    user info:
                    <li>{this.state.user?.id}</li>
                    <li>{this.state.user?.name}</li>
                    <li>{this.state.user?.age}</li>
                    <li>{this.state.user?.email}</li>
                </ul>
            </div>
        );
    }
}

export default UserComponent;
