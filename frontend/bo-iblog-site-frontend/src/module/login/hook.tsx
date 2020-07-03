import React, {useState} from "react";


export const ButtonHook = function () {
    const [count, setCount] = useState(0);
    let element = React.createElement("span");
    element.props.defaultValue = "hello";
    return <div><button onClick={() => {setCount(count + 1)}}>{count}</button></div>;
};
