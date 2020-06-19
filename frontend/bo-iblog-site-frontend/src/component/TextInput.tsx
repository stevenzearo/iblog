import React from 'react';
import '../index/index.css';
import './TextInput.css';
import {TextInputProp, TextInputState} from "./TextInputProp";

export interface Interface {

}
class TextInput extends React.Component<TextInputProp, TextInputState> {

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
                <input id={this.props.id} name={this.props.name} type={this.props.type} placeholder={this.props.placeholder}
                       onBlur={this.setValue}/>
            </div>
        );
    }
}

export default TextInput;
