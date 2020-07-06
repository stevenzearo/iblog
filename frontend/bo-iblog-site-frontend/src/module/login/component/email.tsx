import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface EmailProp extends ComponentProp {
}


export interface EmailState {
    emailIsValid: boolean;
    emailCheckResult: string;
}

export class Email extends React.Component<EmailProp, EmailState> {
    public textInput: any;

    constructor(props: Readonly<EmailProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {emailIsValid: false, emailCheckResult: ""}
    }

    setEmailInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setEmailInput} id='user-name' name='user-name' label='email' type='text'
                       placeholder='please input your email' onBlur={this.props.onBlur} onMouseLeave={this.props.onMouseLeave}/>
            <span>{this.state.emailCheckResult}</span>
        </div>
    }
}

export default Email;
