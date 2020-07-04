import {Group} from "../../api/admin/GroupWebService";

export class Admin {
    public id: number | null;
    public name: string | null;
    public age: number | null;
    public email: string | null;
    public group: Group | null;

    constructor(id: number | null, name: string | null, age: number | null, email: string | null, group: Group | null) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.group = group;
    }
}

export default Admin
