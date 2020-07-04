import {FocusEventHandler} from "react";
import {History} from "history";

export interface ComponentProp {
    onBlur?: FocusEventHandler;
    onFocus?: FocusEventHandler;
}