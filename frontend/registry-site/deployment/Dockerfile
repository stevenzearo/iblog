FROM ubuntu:latest
RUN groupadd -r registry-site && useradd -r -g registry-site registry-site
ADD registry-site-1.0-SNAPSHOT.tar /app
USER registry-site
CMD ['/app/bin/registry-site']