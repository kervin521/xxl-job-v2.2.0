ARG APP_VERSION=1.0.0
FROM openjdk:8-jre

RUN \
    mkdir -p /opt/deploy

COPY *.tar.gz /opt/deploy

ENV APP_VERSION=${APP_VERSION}
LABEL version=${APP_VERSION}
LABEL description="XXL-Job-Admin"

RUN \
    tar -xzvf /opt/deploy/*.tar.gz -C /opt/deploy && \
    /bin/rm -rf /opt/deploy/*.tar.gz && \
    mkdir -p /opt/deploy/logs  /opt/deploy/data && \
    cp -vr /opt/deploy/*/* /opt/deploy && \
    mkdir -p /opt/deploy/bin && \
    chmod +x /opt/deploy/bin  && \
    mv /opt/deploy/*.jar /opt/deploy/xxl-job-admin.jar && \
    true

WORKDIR /opt/deploy
ENTRYPOINT ["java", "-jar", "xxl-job-admin.jar" ]