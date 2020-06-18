export class CreateUserRequest {
    public userName: string;
    public email: string;
    public password: string;
    constructor(userName: string, email: string,password: string) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

// ((in_time < out_time) < 入住) or (离开 < (in_time < out_time))