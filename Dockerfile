FROM maven:3-openjdk-17@sha256:62e6a9e10fb57f3019adeea481339c999930e7363f2468d1f51a7c0be4bca26d as builder
COPY . .
RUN mvn package -DskipTests

FROM alpine:3.16.1@sha256:9b2a28eb47540823042a2ba401386845089bb7b62a9637d55816132c4c3c36eb
COPY --from=builder /target/renovate-demo-dummy-0.0.1-SNAPSHOT.jar app.jar

ENV TZ Europe/Berlin
RUN apk update \
    && apk upgrade \
    && apk add tomcat-native dumb-init tzdata --no-cache \
    && apk add openjdk17-jre --repository=https://dl-cdn.alpinelinux.org/alpine/edge/community/ --no-cache \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone \
    && rm -rf /var/cache/apk/* \
    && adduser -D dev

USER dev

ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD java -jar /app.jar
EXPOSE 8080
