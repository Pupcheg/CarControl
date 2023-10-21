FROM openjdk:17
LABEL authors="Supcheg"
COPY ./build/libs/CarControl-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "CarControl-0.0.1-SNAPSHOT.jar", "me.supcheg.carcontrol.CarControlApplication"]