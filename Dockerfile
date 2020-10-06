FROM openjdk:11-jre-slim
COPY rest/target/rest-0.0.1-SNAPSHOT.jar /opt/target/threatfabric-challenge.jar
WORKDIR /opt/target

ADD wait /wait
RUN chmod +x /wait

CMD ["sh", "-c", "/wait && java -jar threatfabric-challenge.jar"]
#ENTRYPOINT ["java","-jar","challenge-0.0.1-SNAPSHOT.jar"]]
