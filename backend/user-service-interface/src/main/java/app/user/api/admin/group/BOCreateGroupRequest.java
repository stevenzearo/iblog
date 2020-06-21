package app.user.api.admin.group;

import org.springframework.lang.NonNull;

/**
 * @author steve
 */
public class BOCreateGroupRequest {
    @NonNull
    public String name;

    @NonNull
    public String requestedBy;
}
