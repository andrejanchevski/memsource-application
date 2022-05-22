FROM maven:3.8.5-eclipse-temurin-17 as first_stage
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn clean install -Dmaven.test.skip=true
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17.0-jdk  as second_stage
ARG DEPENDENCY=/app/target/dependency
EXPOSE 8080
# copy over the built artifact from the maven image
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp","app:app/lib/*","com.memsource.memsourceapp.MemsourceBeApplication", "--spring.config.location=classpath:/application.yml"]