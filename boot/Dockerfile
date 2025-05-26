FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY boot/target/boot-*.jar /app/app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s CMD wget -q --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", \
            "-XX:+AutoCreateSharedArchive", \
            "-XX:SharedArchiveFile=/app/app.jsa", \
            "-XX:+UseG1GC", \
            "-XX:+UseStringDeduplication", \
            "-jar", "app.jar"]
