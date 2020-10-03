import {FocusEventHandler, MouseEventHandler} from "react";

export interface ComponentProp {
    onBlur?: FocusEventHandler;
    onFocus?: FocusEventHandler;
    onMouseLeave?: MouseEventHandler;
    onMouseEnter?: MouseEventHandler;
}
