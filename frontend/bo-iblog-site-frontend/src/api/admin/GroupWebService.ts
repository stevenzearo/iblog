import {Ajax, AjaxProps, Method} from "../../util/ajax";
import {headers} from "./AdminWebService";

export class ListGroupResponse {
    public groups: Group[] | null;

    constructor(groups: Group[]) {
        this.groups = groups;
    }
}

export class Group {
    public id: string;
    public name: string;
    public roles?: Role[];

    constructor(id: string, name: string, roles?: Role[]) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}

export class Role {
    public name: string;
    public authority: AuthorityView;

    constructor(name: string, authority: AuthorityView) {
        this.name = name;
        this.authority = authority;
    }
}

export enum AuthorityView {
    ALL = "ALL",
    BO_SITE = "BO_SITE",
    FRONT_SITE = "FRONT_SITE"
}


export class GroupWebService {
    static list(func: (result: any) => any) {
        const props: AjaxProps = {
            url: "http://localhost:8410/ajax/group",
        };
        Ajax.ajax(Method.GET, props).then(func);
    }

    static get(id: String): Group | null {
        return null;
    }
}
