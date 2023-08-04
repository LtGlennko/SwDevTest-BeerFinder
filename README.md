# SwDevTest-BeerFinder

Para probar la solución expuesta debe realizar los siguientes pasos:
1. Descargar el proyecto.
2. Instanciar un servidor local de base de datos **MySql** (en mi caso usé **XAMPP**).
3. Crear un usuario con suficientes privilegios para gestionar bases de datos. Las credenciales (que pueden ser cambiadas desde el archivo **aplication.properties** del proyecto) serán "root" como usuario y sin contraseña.
4. Una vez dentro de la instancia se crea una base de datos con el nombre "ingerencia" (también configurable desde el archivo aplication.properties).
5. Ejecutar el proyecto desde la terminal con el comando **./mvnw.cmd spring-boot:run**.
6. Las direcciones para realizar las consultas son las siguientes (Documentación: https://documenter.getpostman.com/view/9040199/2s9XxwxuY4):
   - Mostrar todas las cercezas disponibles: http://localhost:8080/beer
   - Búsqueda de cervezas por nombre: http://localhost:8080/beer/query?name=Buzz
   - Recomendar una cerveza de forma aleatoria: http://localhost:8080/beer/random
7. Para revisar el **log de búsquedas** puede consultar en la tabla **log** dentro de la base de datos creada.
