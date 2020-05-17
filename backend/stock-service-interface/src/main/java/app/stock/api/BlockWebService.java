package app.stock.api;

import app.stock.api.block.CreateBlockRequest;
import app.stock.api.block.CreateBlockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("block-web-service")
public interface BlockWebService {
    @RequestMapping(value = "/block", method = RequestMethod.PUT)
    CreateBlockResponse create(@RequestBody CreateBlockRequest request) throws Exception;
}
