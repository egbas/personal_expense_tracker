FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
COPY target/personal-expense-tracker-0.0.1-SNAPSHOT.jar expense-tracker.jar
ENTRYPOINT ["java", "-jar", "/expense-tracker.jar"]