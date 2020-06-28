package app.user.web;

import app.user.api.BOAdminWebService;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.service.BOAdminService;
import app.web.error.WebException;
import app.web.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
public class BOAdminWebServiceImpl implements BOAdminWebService {
    private final Logger logger = LoggerFactory.getLogger(BOAdminWebServiceImpl.class);
    @Autowired
    BOAdminService boAdminService;

    @Override
    public Response<Object> create(BOCreateAdminRequest request) throws WebException {
        String id = boAdminService.create(request);
        logger.info("created admin, id = {}", id);
        return Response.ok();
    }

    @Override
    public Response<BOGetAdminByEmailResponse> getByEmail(String email) {
        return Response.okOf(boAdminService.getByEmail(email));
    }
}
