import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface EmailProp extends ComponentProp {
}


export interface EmailState {

}

export class Email extends React.Component<EmailProp, EmailState> {
    public textInput: any;

    constructor(props: Readonly<EmailProp>) {
        super(props);
        this.textInput = React.createRef();
    }

    setEmailInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <TextInput ref={this.setEmailInput} id='user-name' name='user-name' label='email' type='text'
                          placeholder='please input your email' onBlur={this.props.onBlur}/>;
    }
}

export default Email;
