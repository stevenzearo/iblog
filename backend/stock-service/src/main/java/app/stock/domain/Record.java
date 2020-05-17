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
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "code")
    public String code;

    @Column(name = "name")
    public String name;

    @Column(name = "record_type")
    public RecordType recordType;

    @Column(name = "date")
    public ZonedDateTime date;

    @Column(name = "latest")
    public Double latest;

    @Column(name = "open")
    public Double open;

    @Column(name = "close")
    public Double close;

    @Column(name = "high")
    public Double high;

    @Column(name = "low")
    public Double low;

    @Column(name = "volume")
    public Double volume;

    @Column(name = "amplitude")
    public Double amplitude;

    @Column(name = "change_rate")
    public Double changeRate;

    @Column(name = "created_name")
    public ZonedDateTime createTime;

    @Column(name = "created_by")
    public String createBy;

    @Column(name = "updated_name")
    public ZonedDateTime updatedTime;

    @Column(name = "updated_by")
    public String updatedBy;
}
