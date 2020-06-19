import React from 'react';
import {User} from "../../model/User";
import {HomeProp, HomeState} from "./HomeProp";
import {UserComponentProp, UserComponentState} from "./UserComponentProp";

class Home extends React.Component<HomeProp, HomeState> {
    public user: User | null;

    constructor(props: any) {
        super(props);
        this.user = props.user;
    }

    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <UserComponent user={this.user}/>
            </div>
        );
    }
}

class UserComponent extends React.Component<UserComponentProp, UserComponentState> {

    constructor(props: any, context: any) {
        super(props, context);
        this.state = {user: props.user}
    }

    setUser(user: User) {
        this.setState( (state: UserComponentState) : UserComponentState | any => {
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

export default Home;
