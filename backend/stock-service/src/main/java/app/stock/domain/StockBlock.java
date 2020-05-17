package app.stock.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Entity
@Table(name = "stock_blocks")
@IdClass(StockBlockPrimaryKey.class)
public class StockBlock {
    @Id
    @Column(name = "stock_id")
    public Long stockId;

    @Id
    @Column(name = "block_id")
    public Long blockId;

    @Column(name = "stock_name")
    public String stockName;

    @Column(name = "block_name")
    public String blockName;

    @Column(name = "created_name")
    public ZonedDateTime createTime;

    @Column(name = "created_by")
    public String createBy;

    @Column(name = "updated_name")
    public ZonedDateTime updatedTime;

    @Column(name = "updated_by")
    public String updatedBy;
}
