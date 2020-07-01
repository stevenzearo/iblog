import {Ajax, AjaxProps, Method} from "../../util/ajax";
import {SERVER_DOMAIN} from "../../react-app-env";

export class CreateGroupAJAXRequest {
    public name: string;

    constructor(name: string) {
        this.name = name;
    }
}

export class ListGroupResponse {
    public groups: Group[] | null;

    constructor(groups: Group[]) {
        this.groups = groups;
    }
}

export class GetGroupAJAXResponse {
    public id: string;
    public name: string;
    public roles: Role[];

    constructor(id: string, name: string, roles: Role[]) {
        this.id = id;
        this.name = name;
        this.roles = roles;
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


export class CreateRoleAJAXRequest {
    public name: string;
    public authority: AuthorityView;

    constructor(name: string, authority: AuthorityView) {
        this.name = name;
        this.authority = authority;
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
    static list(func: (listGroupResponse: ListGroupResponse) => any) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group`,
        };
        Ajax.ajax(Method.GET, props).then(result => result.data).then(func);
    }

    static get(id: string, func: (response: GetGroupAJAXResponse) => void) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group/${id}`,
        };
        Ajax.ajax(Method.GET, props).then(func);
    }

    static create(request: CreateGroupAJAXRequest, func: (result: any) => void) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group`,
            data: request
        };
        Ajax.ajax(Method.POST, props).then(func);
    }

    static remove(id: string, func: (result: any) => void) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group/${id}`,
        };
        Ajax.ajax(Method.DELETE, props).then(func);

    }

    static createRole(groupId: string, request: CreateRoleAJAXRequest, func: (result: any) => void) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group/${groupId}/role`,
            data: request
        };
        Ajax.ajax(Method.DELETE, props).then(func);
    }

    static removeRole(groupId: string, id: string, func: (result: any) => void) {
        const props: AjaxProps = {
            url: `${SERVER_DOMAIN}/ajax/group/${groupId}/role/${id}`,
        };
        Ajax.ajax(Method.DELETE, props).then(func);
    }
}
