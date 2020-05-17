package app.stock.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author steve
 */

public enum RecordType{
    @Enumerated(EnumType.STRING)
    STOCK,
    @Enumerated(EnumType.STRING)
    BLOCK,
    @Enumerated(EnumType.STRING)
    MARKET
}
