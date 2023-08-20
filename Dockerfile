FROM amazoncorretto:17

LABEL Creator="Mititiuc Eduard"

WORKDIR app

COPY /target/restaurant-0.0.1-SNAPSHOT.war /app/restaurant.war

ENTRYPOINT ["java","-jar","restaurant.war"]

