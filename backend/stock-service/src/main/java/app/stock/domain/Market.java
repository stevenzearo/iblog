package app.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Entity
@Table(name = "markets")
public class Market {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "code")
    public String code;

    @Column(name = "name")
    public String name;

    @Column(name = "created_name")
    public ZonedDateTime createTime;

    @Column(name = "created_by")
    public String createBy;

    @Column(name = "updated_name")
    public ZonedDateTime updatedTime;

    @Column(name = "updated_by")
    public String updatedBy;
}
