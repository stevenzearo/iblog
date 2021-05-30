FROM adoptopenjdk/openjdk11:latest
CMD ["groupadd", "-r","user-service", "&&", "useradd", "-r", "-g", "user-service", "user-service"]
ADD user-service-1.0-SNAPSHOT.tar /app
CMD ["USER", "user-service"]
CMD ["/app/user-service-1.0-SNAPSHOT/bin/user-service", "&"]