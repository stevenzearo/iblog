import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface VerifyCodeProp extends ComponentProp {
}


export interface VerifyCodeState {
    verifyCodeIsValid: boolean;
    verifyCodeCheckResult: string;
}

export class VerifyCode extends React.Component<VerifyCodeProp, VerifyCodeState> {
    public textInput: any;

    constructor(props: Readonly<VerifyCodeProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {verifyCodeIsValid: false, verifyCodeCheckResult: ""}
    }

    setVerifyCodeInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setVerifyCodeInput} id='verifyCode' name='verifyCode'
                       label='Code' type='text'
                       placeholder='Input verify code' onBlur={this.props.onBlur}
                       onMouseLeave={this.props.onMouseLeave}/>
            <span>
                {this.state.verifyCodeCheckResult}
            </span>
        </div>;

    }
}