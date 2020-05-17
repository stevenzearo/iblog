package app.site.api;

import app.site.api.init.InitBlockDataResponse;
import app.stock.api.BlockWebService;
import app.stock.api.block.CreateBlockRequest;
import app.stock.api.block.CreateBlockResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
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
    @Autowired(required = false)
    BlockWebService blockWebService;

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

    private CreateBlockResponse createBlockData(String code, String name) throws Exception {
        CreateBlockRequest blockRequest = new CreateBlockRequest();
        blockRequest.code = code;
        blockRequest.name = name;
        blockRequest.createdBy = REQUESTED_BY;
        return blockWebService.create(blockRequest);
    }
}
