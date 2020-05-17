package app.site.api;

import app.site.api.init.InitBlockDataResponse;
import app.site.api.init.InitStockDataResponse;
import app.stock.api.BlockWebService;
import app.stock.api.StockWebService;
import app.stock.api.block.CreateBlockRequest;
import app.stock.api.block.CreateBlockResponse;
import app.stock.api.stock.AddStockRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author steve
 */
@RestController
public class DataInitAJAXWebService {
    private static final String REQUESTED_BY = "bo-iblog-site";
    private static final String INIT_PROPERTIES = "init.properties";
    private static final String INIT_BLOCK_DIR = "init.block.dir";
    private static final String INIT_BLOCK_CODE_FIELD = "init.block.code.field";
    private static final String INIT_BLOCK_NAME_FIELD = "init.block.name.field";
    private static final String INIT_STOCK_DIR = "init.stock.dir";
    private static final String INIT_STOCK_CODE_FIELD = "init.stock.code.field";
    private static final String INIT_STOCK_MARKET_FIELD = "init.stock.market.field";
    private static final String INIT_STOCK_NAME_FIELD = "init.stock.name.field";
    @Autowired(required = false)
    BlockWebService blockWebService;
    @Autowired(required = false)
    StockWebService stockWebService;

    @RequestMapping(value = "/iblog/test/init", method = RequestMethod.POST)
    String test() {
        return "hello, world!";
    }

    @RequestMapping(value = "/iblog/block/init", method = RequestMethod.POST)
    InitBlockDataResponse initBlockData() {
        InitBlockDataResponse initBlockDataResponse = new InitBlockDataResponse();
        initBlockDataResponse.ids = new ArrayList<>();
        try {
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(INIT_PROPERTIES);
            String dataDir = propertiesConfiguration.getString(INIT_BLOCK_DIR);
            ClassPathResource blockData = new ClassPathResource(dataDir);
            String codeField = propertiesConfiguration.getString(INIT_BLOCK_CODE_FIELD);
            String nameField = propertiesConfiguration.getString(INIT_BLOCK_NAME_FIELD);
            ObjectMapper objectMapper = new ObjectMapper();
            Iterator<JsonNode> jsonNodeIterator = objectMapper.readTree(blockData.getFile()).elements();
            while (jsonNodeIterator.hasNext()) {
                JsonNode jsonNode = jsonNodeIterator.next();
                String code = jsonNode.get(codeField).textValue();
                String name = jsonNode.get(nameField).textValue();
                CreateBlockResponse response = createBlockData(code, name);
                initBlockDataResponse.ids.add(response.id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initBlockDataResponse;
    }

    @RequestMapping(value = "/iblog/stock/init", method = RequestMethod.POST)
    InitStockDataResponse initStockData() {
        InitStockDataResponse initBlockDataResponse = new InitStockDataResponse();
        initBlockDataResponse.ids = new ArrayList<>();
        try {
            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(INIT_PROPERTIES);
            String dataDir = propertiesConfiguration.getString(INIT_STOCK_DIR);
            ClassPathResource blockData = new ClassPathResource(dataDir);
            String codeField = propertiesConfiguration.getString(INIT_STOCK_CODE_FIELD);
            String marketField = propertiesConfiguration.getString(INIT_STOCK_MARKET_FIELD);
            String nameField = propertiesConfiguration.getString(INIT_BLOCK_NAME_FIELD);
            ObjectMapper objectMapper = new ObjectMapper();
            Iterator<JsonNode> jsonNodeIterator = objectMapper.readTree(blockData.getFile()).elements();
            while (jsonNodeIterator.hasNext()) {
                JsonNode jsonNode = jsonNodeIterator.next();
                String code = jsonNode.get(codeField).textValue();
                long market = jsonNode.get(marketField).longValue();
                String name = jsonNode.get(nameField).textValue();
                Long stockId = createStockData(code, market, name);
                initBlockDataResponse.ids.add(stockId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initBlockDataResponse;
    }

    private CreateBlockResponse createBlockData(String code, String name) throws Exception {
        CreateBlockRequest blockRequest = new CreateBlockRequest();
        blockRequest.code = code;
        blockRequest.name = name;
        blockRequest.createdBy = REQUESTED_BY;
        return blockWebService.create(blockRequest);
    }

    private Long createStockData(String code, Long marketId, String name) throws Exception {
        AddStockRequest stockRequest = new AddStockRequest();
        stockRequest.code = code;
        stockRequest.name = name;
        stockRequest.marketId = marketId;
        stockRequest.createdBy = REQUESTED_BY;
        return stockWebService.addStock(stockRequest);
    }
}
