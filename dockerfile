# Dockerfile
FROM openjdk:21-ea-24-oracle

WORKDIR /app
#nombre jar
COPY target/tiendita-0.0.1-SNAPSHOT.jar app.jar
#ubicacion del wallet comprimido
COPY Wallet_Fstack /app/wallet_fstack
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]