FROM openjdk:11-jre-slim
COPY target /opt/target
WORKDIR /opt/target

ADD wait /wait
RUN chmod +x /wait

CMD ["sh", "-c", "/wait && java -jar challenge-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java","-jar","challenge-0.0.1-SNAPSHOT.jar"]]
