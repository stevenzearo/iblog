import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface NameProp extends ComponentProp {
}


export interface NameState {
    nameIsValid: boolean;
    nameCheckResult: string;
}

export class Name extends React.Component<NameProp, NameState> {
    public textInput: any;

    constructor(props: Readonly<NameProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {nameIsValid: false, nameCheckResult: ""}
    }

    setNameInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setNameInput} id='user-name' name='user-name' label='Name' type='text'
                       placeholder='Input your name here' onBlur={this.props.onBlur} onMouseLeave={this.props.onMouseLeave}/>
            <span>{this.state.nameCheckResult}</span>
        </div>
    }
}

export default Name;
