package app.stock.web;

import app.stock.api.BlockWebService;
import app.stock.api.block.CreateBlockRequest;
import app.stock.api.block.CreateBlockResponse;
import app.stock.service.BlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author steve
 */
public class BlockWebServiceImpl implements BlockWebService {
    private static Logger logger = LoggerFactory.getLogger(BlockWebServiceImpl.class);

    @Autowired
    BlockService blockService;

    @Override
    public CreateBlockResponse create(CreateBlockRequest request) {
        CreateBlockResponse response = blockService.create(request);
        logger.info(String.format("created block, id = %d", response.id));
        return response;
    }
}
