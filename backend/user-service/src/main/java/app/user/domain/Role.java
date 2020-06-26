package app.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Entity
@Table(name = "`roles`")
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
    @Column(name = "authority")
    public Authority authority;

    @NotNull
    @NotBlank
    @Column(name = "created_by")
    public String createBy;

    @NotNull
    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "Role{" +
            "id='" + id + '\'' +
            ", groupId='" + groupId + '\'' +
            ", name='" + name + '\'' +
            ", authority=" + authority +
            ", createBy='" + createBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
