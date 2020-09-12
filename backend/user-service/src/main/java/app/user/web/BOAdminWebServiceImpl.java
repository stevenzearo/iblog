package app.user.web;

import app.user.api.BOAdminWebService;
import app.user.api.admin.BOCreateAdminRequest;
import app.user.api.admin.BOGetAdminByEmailResponse;
import app.user.api.admin.BOGetAdminByIdResponse;
import app.user.service.BOAdminService;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
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
    public EmptyResponse create(BOCreateAdminRequest request) {
        Response<String> response = ResponseHelper.encloseWithException(() -> boAdminService.create(request));
        logger.info("created admin, id = {}", response.data);
        return response;
    }

    @Override
    public Response<BOGetAdminByIdResponse> getById(String id) {
        logger.info("get admin, id = {}", id);
        return ResponseHelper.okOf(boAdminService.getById(id));
    }

    @Override
    public Response<BOGetAdminByEmailResponse> getByEmail(String email) {
        logger.info("get admin by email, email = {}", email);
        return ResponseHelper.okOf(boAdminService.getByEmail(email));
    }
}
