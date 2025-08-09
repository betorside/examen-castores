# Sistema de Inventario Castores - Examen Técnico

Sistema web completo para gestión de inventario desarrollado como parte del examen técnico de Castores, que incluye gestión de usuarios, productos y transacciones con roles diferenciados (Administrador y Almacenista).

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java** 17+
- **Spring Boot** 3.5.4
- **Spring Security** 6.x
- **Spring Data JPA** (Hibernate)
- **Maven** 3.9.x
- **MySQL** 8.0.43

### Frontend
- **Vue.js** 3.x
- **JavaScript** ES6+
- **HTML5 & CSS3**
- **Node.js** 18+ / **npm** 9+

### Base de Datos
- **MySQL** 8.0.43 (Dockerizado)

### Herramientas de Desarrollo
- **IDE:** Visual Studio Code
- **Control de Versiones:** Git
- **Containerización:** Docker
- **SO Desarrollo:** Linux


## 🚀 Instalación Rápida

### 1. Prerrequisitos
- Java 17+
- Node.js 18+
- Docker
- MySQL Client (opcional)

### 2. Clonar el repositorio
```bash
git clone <url-repositorio>
cd examen
```

### 3. Configurar Base de Datos
```bash
# Crear contenedor MySQL
sudo docker run -d \
  --name mysql-castores \
  -e MYSQL_ROOT_PASSWORD=prueba_castores \
  -e MYSQL_DATABASE=castores_examen \
  -p 3306:3306 \
  -v mysql_castores_data:/var/lib/mysql \
  mysql:8.0

# Ejecutar script de datos inicial
sudo docker exec -i mysql-castores mysql -uroot -p prueba_castores castores_examen < backend/datos_prueba.sql
```

### 4. Ejecutar Backend
```bash
cd backend
./mvnw spring-boot:run
```

### 5. Ejecutar Frontend
```bash
cd frontend
npm install
npm run dev
```

### 6. Acceder a la Aplicación
- **Frontend:** http://localhost:5173
- **Backend API:** http://localhost:8080


## 🔗 Enlaces Adicionales

- **Diagrama Relacional:** [Ver en Google Drive](https://drive.google.com/file/d/1FInUz8KzlmaWQMx2lccefiKS2r3thN2I/view?usp=drive_link)
- **Documentación API:** https://documenter.getpostman.com/view/47431134/2sB3BDLXVr

## 👨‍💻 Desarrollador

**Roberto Padilla Álvarez**  
Examen Técnico - Castores 2025

---
*Sistema desarrollado como parte del proceso de selección de Castores*