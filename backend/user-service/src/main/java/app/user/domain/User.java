package app.user.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author steve
 */
@Entity
@Table(name = "users")
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

    @Column(name = "password")
    public String password;

    @Override
    public String toString() {
        return "User{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", age=" + age
            + ", email='" + email + '\''
            + ", password='" + password + '\''
            + '}';
    }
}
