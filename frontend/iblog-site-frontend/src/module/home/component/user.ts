export class User {
    public id: number;
    public name: string;
    public age: number;
    public email: string;
    public password?: string;

    constructor(id: number, name: string, age: number, email: string, password?: string) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}

export default User
