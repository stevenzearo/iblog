import {Group} from "../../../api/admin/GroupWebService";

export class Admin {
    public id: string;
    public name: string | null;
    public email: string;
    public group: Group;

    constructor(id: string, email: string, group: Group) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.group = group;
    }
}

export default Admin
