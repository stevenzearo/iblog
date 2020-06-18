import React from 'react';
import '../index/index.css';
import './TextInput.css';
import {TextInputProp, TextInputState} from "./TextInputProp";

class TextInput extends React.Component<TextInputProp, TextInputState> {
    public id: string | undefined;
    public name: any;
    public label: any;
    public type: any;
    public placeholder: string | undefined;

    constructor(props: any) {
        super(props);
        this.id = props.id;
        this.name = props.name;
        this.label = props.label;
        this.type = props.type;
        this.placeholder = props.placeholder;
        this.state = {id: props.id, value: ''};
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
                <label htmlFor={this.id}>{this.label} :</label>
                <input id={this.id} name={this.name} type={this.type} placeholder={this.placeholder}
                       onBlur={this.setValue}/>
            </div>
        );
    }
}

export default TextInput;