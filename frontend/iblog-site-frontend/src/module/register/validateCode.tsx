import React from "react";

export interface ValidateCodeProps {
    refreshSecond: number | any;
}

export interface ValidateCodeState {
    timeCounter: number;
    refreshable: boolean;
    validateCode: string;
}

export class ValidateCode extends React.Component<ValidateCodeProps, ValidateCodeState> {
    timeId: NodeJS.Timeout | any;

    constructor(props: ValidateCodeProps) {
        super(props);

        this.state = {
            timeCounter: 0,
            refreshable: false,
            validateCode: '000000'
        }
    }


    tick() {
        let refreshSecond = this.props.refreshSecond;
        this.setState(function (state: ValidateCodeState) {
            var refreshable = false;
            if (state.timeCounter >= refreshSecond) refreshable = true;
            return {timeCounter: state.timeCounter++, refreshable: refreshable, validateCode: state.validateCode}
        })
    }

    // parent method
    componentDidMount() {
        this.timeId = setInterval(() => this.tick(), 1000);
    }

    // parent method
    componentWillUnmount() {
        clearInterval(this.timeId);
    }

    getValidateCode = () => {
        // todo use session to get a validate code
    };

    refreshValidateCode = () => {
        this.setState(function (state: any) {
            var counter = state.timeCounter;
            if (counter <= 30) {
                alert('please refresh later');
                return state;
            }
            // todo get new validate code
            var validateCode = '000001';
            counter = 0;
            return {timeCounter: 0, refreshable: false, validateCode: validateCode}
        })
    };

    render() {
        const state = this.state;
        var secondLeft = this.props.refreshSecond - state.timeCounter;
        return (
            <div id='validate-code'>
                <div className='validate-code'>
                    <label htmlFor='server-validate-code' className='validate-code-label'>Code:</label>
                    <p id='server-validate-code' className='validate-code-content'>{this.state.validateCode}</p>
                </div>
                <div className='count-down'>
                    <span>refresh after {secondLeft > 0 ? secondLeft : 0} sec</span>

                    <button className="refresh-btn" disabled={!this.state.refreshable} onClick={this.refreshValidateCode}>refresh</button>
                </div>
            </div>
        );
    }

}
