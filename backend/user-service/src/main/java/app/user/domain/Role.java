package app.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author steve
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @NotNull
    @NotBlank
    @Column(name = "id")
    public String id;

    @NotNull
    @NotBlank
    @Column(name = "group_id")
    public String groupId;

    @NotNull
    @NotBlank
    @Column(name = "name")
    public String name;

    @NotNull
    @NotBlank
    @Column(name = "authority")
    public Authority authority;
}
