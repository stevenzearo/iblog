package app.site.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public class AdminAJAXWebService {

    @RequestMapping("/admin/test")
    String test() {
        return "hello, world";
    }}
