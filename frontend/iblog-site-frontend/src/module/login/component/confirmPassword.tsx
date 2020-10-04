import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface ConfirmPasswordProp extends ComponentProp {
}


export interface ConfirmPasswordState {
    confirmPasswordIsValid: boolean;
    confirmPasswordCheckResult: string;
}

export class ConfirmPassword extends React.Component<ConfirmPasswordProp, ConfirmPasswordState> {
    public textInput: any;

    constructor(props: Readonly<ConfirmPasswordProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {confirmPasswordIsValid: false, confirmPasswordCheckResult: ""}
    }

    setConfirmPasswordInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setConfirmPasswordInput} id='confirmPassword' name='confirmPassword'
                       label='Confirm' type='password'
                       placeholder='Confirm your password' onBlur={this.props.onBlur}
                       onMouseLeave={this.props.onMouseLeave}/>
            <span>
                {this.state.confirmPasswordCheckResult}
            </span>
        </div>;

    }
}