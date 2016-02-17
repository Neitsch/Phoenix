FROM java:8
RUN apt-get update && apt-get install -y x11-xserver-utils
VOLUME /tmp
ADD phoenix-engine-remote-0.0.2-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.awt.headless=false","-Dqueue.host=143.44.65.120","-jar","/app.jar"]
