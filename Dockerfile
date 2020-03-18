FROM        sapmachine/jdk11
LABEL       app=user-service
RUN         addgroup --system app && adduser --system --no-create-home --ingroup app app
USER        app
ADD         file/user-service.tar    /opt
CMD         ["/opt/user-service/bin/user-service"]