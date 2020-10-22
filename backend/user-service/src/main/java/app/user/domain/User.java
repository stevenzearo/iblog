package app.user.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Entity
@Table(name = "`users`")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "age")
    public Integer age;

    @Column(name = "email")
    public String email;

    public Gender gender;

    @Column(name = "password")
    public String password;

    @NotNull
    @NotBlank
    @Column(name = "salt")
    public String salt;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "iterated_times")
    public Integer iteratedTimes;

    @NotNull
    @NotBlank
    @Column(name = "created_by")
    public String createBy;

    @NotNull
    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", createBy='" + createBy + '\'' +
            ", createdTime=" + createdTime +
            '}';
    }
}
