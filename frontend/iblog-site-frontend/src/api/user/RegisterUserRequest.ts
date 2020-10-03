export class RegisterUserRequest {
    public name: string | null;
    public age: number | null;
    public email: string;
    public password: string;

    constructor(name: string | null, age: number | null, email: string, password: string) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}
