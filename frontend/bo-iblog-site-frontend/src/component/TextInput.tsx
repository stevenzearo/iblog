import React from 'react';
import '../index.css';
import './TextInput.css';
import {ComponentProp} from "../module/login/component/component";

export interface TextInputProp extends ComponentProp {
    id: string | undefined;
    name: any;
    label: any;
    type: any;
    placeholder: string | undefined;
}

export interface TextInputState {
    id: string | any;
    value: string | any;
}

export class TextInput extends React.Component<TextInputProp, TextInputState> {

    public input: any | null;

    constructor(props: Readonly<TextInputProp>) {
        super(props);
        this.state = {id: "", value: ""};
        this.input = React.createRef();
    }

    setValue = () => {
        this.setState(function (state: any) {
            var text = state.id.value;
            state = {id: state.id, value: text};
            return state;
        });
    };

    render() {
        return (
            <div className='text-input'>
                <label htmlFor={this.props.id}>{this.props.label} :</label>
                <input ref={(ref) => this.input = ref} id={this.props.id} name={this.props.name} type={this.props.type}
                       placeholder={this.props.placeholder} onBlur={this.props.onBlur} onMouseLeave={this.props.onMouseLeave}/>
            </div>
        );
    }
}

export default TextInput;
