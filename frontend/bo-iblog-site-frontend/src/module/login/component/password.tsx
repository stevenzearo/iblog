import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface PasswordProp extends ComponentProp {
}


export interface PasswordState {
    passwordIsValid: boolean;
    passwordCheckResult: string;
}

export class Password extends React.Component<PasswordProp, PasswordState> {
    public textInput: any;

    constructor(props: Readonly<PasswordProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {passwordIsValid: false, passwordCheckResult: ""}
    }

    setPasswordInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setPasswordInput} id='password' name='password' label='password' type='password'
                       placeholder='please input your password' onBlur={this.props.onBlur} onMouseLeave={this.props.onMouseLeave}/>
            <span>
                {this.state.passwordCheckResult}
            </span>
        </div>;

    }
}

export default Password;
