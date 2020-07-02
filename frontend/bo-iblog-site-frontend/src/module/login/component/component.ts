import {FocusEventHandler} from "react";

export interface ComponentProp {
    onBlur?: FocusEventHandler;
    onFocus?: FocusEventHandler;
}