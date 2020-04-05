FROM openjdk:14-jdk-buster
COPY wiremock-standalone-2.26.3.jar /wiremock.jar
COPY mocks /mocks
CMD ["java", "-jar", "/wiremock.jar", "--root-dir=/mocks"]
