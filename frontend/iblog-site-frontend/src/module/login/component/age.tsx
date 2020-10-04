import React from "react";
import TextInput from "../../../component/TextInput";
import {ComponentProp} from "./component";

export interface AgeProp extends ComponentProp {
}


export interface AgeState {
    ageIsValid: boolean;
    ageCheckResult: string;
}

export class Age extends React.Component<AgeProp, AgeState> {
    public textInput: any;

    constructor(props: Readonly<AgeProp>) {
        super(props);
        this.textInput = React.createRef();
        this.state = {ageIsValid: false, ageCheckResult: ""}
    }

    setAgeInput = (ref: any) => {
        this.textInput = ref;
    };

    render() {
        return <div>
            <TextInput ref={this.setAgeInput} id='user-age' name='user-age' label='Age' type='text'
                       placeholder='Input your age here' onBlur={this.props.onBlur} onMouseLeave={this.props.onMouseLeave}/>
            <span>{this.state.ageCheckResult}</span>
        </div>
    }
}

export default Age;
