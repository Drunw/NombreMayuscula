# Usar una imagen base que contenga Java y Maven
FROM maven:3.6.3-jdk-11 AS builder

# Directorio de trabajo en la imagen
WORKDIR /app

# Copiar el pom.xml para descargar dependencias
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn package

# Imagen base para ejecutar la aplicación
FROM openjdk:11-jre-slim

# Directorio de trabajo en la imagen
WORKDIR /app

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto por la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]