import React from 'react';
import '../index.css';
import './TextInput.css';

export interface TextInputProp {
    id: string | undefined;
    name: any;
    label: any;
    type: any;
    placeholder: string | undefined;
    onBlur?: (e: React.FocusEventHandler) => void;
    onFocus?: (e: React.FocusEventHandler) => void;
}

export interface TextInputState {
    id: string | any;
    value: string | any;
}

export class TextInput extends React.Component<TextInputProp, TextInputState> {


    constructor(props: Readonly<TextInputProp>) {
        super(props);
        this.state = {id: "", value: ""}
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
                <input id={this.props.id} name={this.props.name} type={this.props.type}
                       placeholder={this.props.placeholder}
                       onBlur={this.setValue}/>
            </div>
        );
    }
}

export default TextInput;
