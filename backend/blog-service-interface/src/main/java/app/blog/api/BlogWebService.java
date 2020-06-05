package app.blog.api;

import app.blog.api.blog.CreateBlogRequest;
import app.blog.api.blog.GetBlogResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
@FeignClient("blog-web-service")
public interface BlogWebService {

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    GetBlogResponse get(@PathVariable("id") String id);

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    void create(@RequestBody CreateBlogRequest request);


}
