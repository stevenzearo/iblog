package app.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author steve
 */
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "id")
    public String id;

    @NotNull
    @NotBlank
    @Column(name = "group_id")
    public String groupId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    public String name;

    @NotNull
    @NotBlank
    @Column(name = "password")
    public String password;

    @NotNull
    @NotBlank
    @Column(name = "email")
    public String email;

    @NotNull
    @NotBlank
    @Column(name = "salt")
    public String salt;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "iterated_times")
    public Integer iteratedTimes;
}
