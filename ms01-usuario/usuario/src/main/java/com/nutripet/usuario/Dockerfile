# 1. Usar imágenes base Alpine (considerablemente más ligeras) 
FROM eclipse-temurin:17-jre-alpine

# 2. Copiar el archivo ejecutable JAR generado por Maven al contenedor 
COPY target/*.jar app.jar

# 3. Leer el puerto desde las variables de entorno (Nunca escribir puertos estáticos o secretos aquí) [cite: 87]
ENV PORT=8081
EXPOSE ${PORT}

# 4. Comando de ejecución optimizado para iniciar el microservicio
ENTRYPOINT ["java", "-jar", "/app.jar"]