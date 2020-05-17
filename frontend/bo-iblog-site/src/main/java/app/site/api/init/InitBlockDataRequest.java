package app.site.api.init;

import javax.validation.constraints.NotNull;

/**
 * @author steve
 */
public class InitBlockDataRequest {
    @NotNull
    public String initDataPath = "data/block_info2.json";
    @NotNull
    public String codeField = "f12";
    @NotNull
    public String nameField = "f14";
}
