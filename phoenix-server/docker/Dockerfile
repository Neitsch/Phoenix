FROM java:8
VOLUME /tmp
ADD phoenix-server-0.0.2.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dqueue.host=143.44.65.120","-jar","/app.jar"]
