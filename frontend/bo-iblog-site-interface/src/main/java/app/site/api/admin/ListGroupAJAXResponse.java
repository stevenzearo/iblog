package app.site.api.admin;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class ListGroupAJAXResponse {
    @NonNull
    public List<Group> groups = new ArrayList<>();

    public static class Group {
        @NonNull
        public String id;

        @NonNull
        public String name;
    }
}
