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
@Table(name = "groups")
public class Group {
    @Id
    @NotNull
    @NotBlank
    @Column(name = "id")
    public String id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    public String name;

    @NotNull
    @NotBlank
    @Column(name = "created_by")
    public String createBy;

    @NotNull
    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "Group{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", createBy='" + createBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}