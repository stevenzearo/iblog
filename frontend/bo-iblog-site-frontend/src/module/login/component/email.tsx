import React from "react";
import TextInput from "../../../component/TextInput";

export interface EmailProp {

}
export const Email: React.FC<EmailProp> = () => {
    return <TextInput id='user-name' name='user-name' label='email' type='text' placeholder='please input your email'/>;
};

