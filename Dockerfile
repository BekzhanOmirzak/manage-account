FROM alpine:3.19

RUN apk add openjdk17

COPY build/libs/contact.jar /contact.jar

ENTRYPOINT ["java","-jar","contact.jar"]
