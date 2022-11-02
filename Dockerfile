FROM openjdk:11 as build
WORKDIR /workspace/decrypto-pipeline_main

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw compile
RUN ./mvnw test
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=/workspace/decrypto-pipeline_main/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /decrypto-pipeline_main/lib
COPY --from=build ${DEPENDENCY}/META-INF /decrypto-pipeline_main/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /decrypto-pipeline_main

ENTRYPOINT ["java","-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-cp","decrypto-pipeline_main:decrypto-pipeline_main/lib/*","com.maxel.decrypto.DecryptoApplication"]
