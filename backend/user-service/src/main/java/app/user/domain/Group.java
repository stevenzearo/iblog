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
}