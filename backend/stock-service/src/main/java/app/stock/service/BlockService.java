package app.stock.service;

import app.stock.api.block.CreateBlockRequest;
import app.stock.api.block.CreateBlockResponse;
import app.stock.dao.jpa.BlockDao;
import app.stock.domain.Block;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Service
public class BlockService {
    @Autowired(required = false)
    BlockDao blockDao;

    public CreateBlockResponse create(CreateBlockRequest request) throws Exception {
        Block blockInDB = blockDao.getFirstByCode(request.code);
        if (blockInDB != null) {
            throw new Exception(String.format("already created block, code = %s", request.code));
        }
        Block block = buildBlock(request);
        Block saveResult = blockDao.save(block);
        return buildCreateBlockResponse(saveResult);
    }

    private Block buildBlock(CreateBlockRequest request) {
        Block block = new Block();
        block.code = request.code;
        block.name = request.name;
        block.createTime = ZonedDateTime.now();
        block.createBy = request.createdBy;
        return block;
    }

    private CreateBlockResponse buildCreateBlockResponse(Block saveResult) {
        CreateBlockResponse response = new CreateBlockResponse();
        response.id = saveResult.id;
        return response;
    }
}
