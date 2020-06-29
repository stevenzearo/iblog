import {Ajax, AjaxProps} from "../../util/ajax";

export class ListGroupResponse {
    public groups: Group[];

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
    static list(): Group[] {
        let groups: Group[] = [];
        let result2;
        const props: AjaxProps = {
            url: "http://localhost:8410/ajax/group",
            // alert(data.groups.length);
        func: (result) => {
                if (result.status === 200) {
                    groups = result.data.groups;
                } else {
                    result.rejct();
                }
            // alert(result.data.groups[0].id + "");
                // result = data.groups;
                // result2 = result;
            },
        };
        Ajax.get(props);
        // alert(JSON.stringify(result));
        props.data = result2;
        return groups;
    }

    static get(id: String): Group | null {
        return null;
    }
}
