import React, {ComponentState, CSSProperties} from "react";
import {ComponentProp} from "../module/login/component/component";

export interface SlideProp extends ComponentProp {
    slideSettings: SlideSettings;
}

export interface SlideState extends ComponentState {
    style: CSSProperties
}

export enum ChangingCSSProp {
    WIDTH = "width",
    HEIGHT = "height"
}

export enum SlideFrom {
    LEFT = "left",
    RIGHT = 'right',
    TOP = 'top',
    BOTTOM = 'bottom'
}


export class SlideSettings {
    public num: number;
    public changingCSSProp?: ChangingCSSProp;
    public type: SlideDistanceType;

    constructor(num: number, type: SlideDistanceType, slideFrom: SlideFrom) {
        this.num = num;
        this.type = type;
        if (slideFrom === SlideFrom.LEFT || slideFrom === SlideFrom.RIGHT) {
            this.changingCSSProp = ChangingCSSProp.WIDTH;
        } else {
            this.changingCSSProp = ChangingCSSProp.HEIGHT;
        }
    }
}

export enum SlideDistanceType {
    PX = 'px',
    RATE = '%'
}

export class SlideComponent extends React.Component<SlideProp, SlideState> {
    private timeId: NodeJS.Timeout | any = null;


    constructor(props: Readonly<SlideProp>) {
        super(props);
        let propVal = 0 + props.slideSettings.type;
        let propKey = this.props.slideSettings.changingCSSProp;
        if (propKey === ChangingCSSProp.WIDTH) {
            this.state = {
                style: {width: propVal, position: "relative", display: "block"}
            };
        } else {
            this.state = {
                style: {height: propVal, position: "relative", display: "block"}
            };
        }
    }


    componentDidUpdate(nextProps: Readonly<SlideProp>, nextState: Readonly<SlideState>, nextContext: any): boolean {
        if (!!nextState.style.width) {
            let widthVal = Number(nextState.style.width.toLocaleString().split(this.props.slideSettings.type)[0]);
            return widthVal < this.props.slideSettings.num;
        } else if (!!nextState.style.height) {
            let heightVal = Number(nextState.style.height.toLocaleString().split(this.props.slideSettings.type)[0]);
            return heightVal < this.props.slideSettings.num;
        } else return false;
    }

    componentWillUpdate(nextProps: Readonly<SlideProp>, nextState: Readonly<SlideState>, nextContext: any): void {
        let propVal = 0;
        if (!!nextState.style.width) {
            propVal = Number(nextState.style.width.toLocaleString().split(this.props.slideSettings.type)[0]);
        } else if (!!nextState.style.height) {
            propVal = Number(nextState.style.height.toLocaleString().split(this.props.slideSettings.type)[0]);
        } else {
            propVal = this.props.slideSettings.num;
        }
        if (propVal >= this.props.slideSettings.num) {
            clearInterval(this.timeId);
        }
    }



    componentDidMount(): void {
        this.timeId = setInterval(() => this.slide(), 20);
    }

    // parent method
    componentWillUnmount() {
        clearInterval(this.timeId);
    }


    slide() {
        this.setState((state: SlideState) => {
                if (!!state.style.width) {
                    let widthVal = Number(state.style.width.toLocaleString().split(this.props.slideSettings.type)[0]);
                    widthVal++;
                    let propVal = widthVal + this.props.slideSettings.type;
                    return {style: {width: propVal, position: "relative", display: "block"}};
                } else if (!!state.style.height) {
                    let heightVal = Number(state.style.height.toLocaleString().split(this.props.slideSettings.type)[0]);
                    heightVal++;
                    let propVal = heightVal + this.props.slideSettings.type;
                    return {style: {height: propVal, position: "relative", display: "block"}};
                } else return {style: {position: "relative", display: "block"}}
            });
        // alert(JSON.stringify(this.state))
    }

    render() {
        return <div style={this.state.style}>hello</div>;
    }
}