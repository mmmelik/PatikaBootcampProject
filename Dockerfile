FROM maven:3.8-openjdk-17

ADD ./target/PatikaBootcampProject-0.0.1-SNAPSHOT.jar /melikBootcampProject.jar

ENTRYPOINT ["java", "-jar", "melikBootcampProject.jar"]

EXPOSE 8080