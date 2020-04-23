package app.stock.api.stock;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author steve
 */
public class AddStockRequest {
    public String code;
    public String blockCode;
    public String name;
    public Double latest;
    public Double increased;
    public Double increasedRate;
    public Double open;
    public Double close;
    public Double high;
    public Double low;
    public Double volume;
    public Double volumeRate;
    public Double amount;
}
