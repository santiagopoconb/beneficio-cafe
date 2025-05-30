# Beneficio Café 📊☕

Backend y Frontend del sistema de gestión de operaciones para el módulo de beneficio, desarrollado con Java 17 (Spring Boot) y Angular. La base de datos está alojada en AWS RDS (PostgreSQL).

## 📚 Descripción General

Este proyecto permite gestionar operaciones de beneficio de café, incluyendo:

* Registro, consulta y actualización de transportistas, pilotos, transportes y agricultores.
* Control de estados de transporte, piloto y transportista.
* Generación de bitácoras para auditoría de cambios.
* Autenticación segura con JWT y control de sesiones.

## ⚙️ Tecnologías

* **Backend**: Java 17, Spring Boot, Spring Security, PostgreSQL, AWS RDS
* **Frontend**: Angular, Angular Material, CSS3
* **Otros**: Git, Docker (para despliegue futuro), JWT

## 🗂️ Estructura del Proyecto

### 📦 Backend (Spring Boot)

* `beneficio.catalogo_transporte`
* `beneficio.piloto`
* `beneficio.usuario` (gestión de autenticación y roles)
* `beneficio.log_*` (bitácora de auditoría)
* Configuración de seguridad con filtros personalizados y JWT

### 🌐 Frontend (Angular)

* Componentes `standalone` para cada módulo (`inicio-beneficio`, `inicio-pesaje`, `nuevo-piloto`, `nuevo-transporte`)
* Formularios con validaciones (ej: edad mínima, fechas)
* Autenticación integrada con JWT y control de expiración
* Navegación basada en roles (`idRol`)

## 🏗️ Implementaciones Destacadas

* **Bitácora de cambios**: Guarda `INSERT` y `UPDATE` por campos específicos.
* **Generación de números correlativos**: Formato dinámico reiniciado anualmente, ej: `OTM-1-2025`.
* **Autenticación con JWT**: Almacena token seguro en `localStorage` y maneja expiración.
* **Formularios dinámicos**: Con campos desplegables desde APIs y validación completa.
* **Consumo de API AWS RDS**: Se aprovechan servicios REST para gestión de datos en tiempo real.

## 🚀 Despliegue

* Backend empaquetado en **Docker** para despliegue futuro en AWS Lambda.
* Base de datos configurada en AWS RDS con monitorización y control de costos.
* Frontend Angular listo para ser hospedado en AWS Amplify o S3.

## 🧩 Ejecución Local

1. Clonar el repositorio:

```bash
git clone https://github.com/tu_usuario/beneficio-cafe.git
cd beneficio-cafe
```
2. Configurar variables de entorno para el backend (.env o application.properties):
* JWT_SECRET
* DB_URL
* DB_USER
* DB_PASSWORD

3. Compilar y ejecutar Spring Boot:
```bash
./mvnw clean install
java -jar target/beneficio-cafe.jar
```

4. Para el frontend:
```bash
cd frontend
npm install
ng serve --open
```

## 📊 Diagramas y Estructuras
Incluye:
* Esquema relacional de base de datos PostgreSQL
* Diagramas de flujo con Bizagi para flujos críticos
* Esquemas de bitácora y control de cambios

## 📅 Estado Actual
* ✅ Backend funcional (conexión AWS RDS, JWT, bitácora)
* ✅ Frontend Angular listo y probado
* 🔄 Dockerización en progreso
* 🔄 Despliegue AWS Lambda/Amplify pendiente

## 📄 Licencia
Este proyecto está bajo la licencia y autorización de Santiago Pocón spoconb@miumg.edu.gt
