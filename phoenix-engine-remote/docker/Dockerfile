FROM java:8
VOLUME /tmp
ADD phoenix-engine-remote-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.awt.headless=false","-Dqueue.host=143.44.65.120","-jar","/app.jar"]
