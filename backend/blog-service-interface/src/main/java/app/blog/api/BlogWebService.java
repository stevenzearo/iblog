package app.blog.api;

import app.blog.api.blog.CreateBlogRequest;
import app.blog.api.blog.GetBlogResponse;
import app.blog.api.blog.SearchBlogRequest;
import app.blog.api.blog.SearchBlogResponse;
import app.web.response.Response;
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
    Response<GetBlogResponse> get(@PathVariable("id") String id);

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    Response<String> create(@RequestBody CreateBlogRequest request);


    @RequestMapping(value = "/blog", method = RequestMethod.PUT)
    Response<SearchBlogResponse> search(@RequestBody SearchBlogRequest request);
}
