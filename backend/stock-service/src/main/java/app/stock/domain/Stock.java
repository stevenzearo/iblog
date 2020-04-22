package app.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * @author steve
 */
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "code")
    public String code;

    @Column(name = "block_code")
    public String blockCode;

    @Column(name = "name")
    public String name;

    @Column(name = "latest")
    public Double latest;

    @Column(name = "increased")
    public Double increased;

    @Column(name = "increased_rate")
    public Double increasedRate;

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

    @Column(name = "volume_rate")
    public Double volumeRate;

    @Column(name = "amount")
    public Double amount;
}
