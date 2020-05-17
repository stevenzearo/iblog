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
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "code")
    public String code;

    @Column(name = "name")
    public String name;

    @Column(name = "created_time")
    public ZonedDateTime createTime;

    @Column(name = "created_by")
    public String createBy;

    @Column(name = "updated_time")
    public ZonedDateTime updatedTime;

    @Column(name = "updated_by")
    public String updatedBy;}
