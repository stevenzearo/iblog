package app.user.domain;


import app.validation.annotation.NotNull;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "admin_activities", indexes = {@Index(name = "ix_created_time", columnList = "created_time", unique = false)})
public class AdminActivity {
    @Id
    @Column(name = "id")
    public String id;

    @NotNull
    @Column(name = "admin_id")
    public String adminId;

    @NotNull
    @Column(name = "comment")
    public String comment;

    @NotNull
    @Column(name = "created_by")
    public String createdBy;

    @NotNull
    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "AdminActivity{" +
            "id='" + id + '\'' +
            ", admin_id='" + adminId + '\'' +
            ", comment='" + comment + '\'' +
            ", created_by='" + createdBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
