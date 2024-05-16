FROM alpine:3.19

RUN apk add openjdk17

COPY build/libs/sms-push.jar /manage.jar

ENTRYPOINT ["java","-jar","manage.jar"]
