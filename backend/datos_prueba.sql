-- Script completo para el sistema de Castores
-- Base de datos: castores_examen

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS castores_examen;
USE castores_examen;

CREATE TABLE IF NOT EXISTS roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(255),
    contrasena VARCHAR(25),
    estatus INT DEFAULT 1,
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

CREATE TABLE IF NOT EXISTS productos (
    idProducto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25),
    precio DOUBLE,
    descripcion VARCHAR(200),
    cantidad INT,
    estatus INT
);

CREATE TABLE IF NOT EXISTS transacciones (
    id_transaccion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT,
    id_producto BIGINT,
    cantidad INT(5),
    fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(25),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(idProducto)
);


INSERT IGNORE INTO roles (nombre) VALUES 
('Administrador'),
('Almacenista');

INSERT IGNORE INTO usuarios (nombre, correo, contrasena, estatus, id_rol) VALUES 
('Administrador Sistema', 'admin@castores.com', 'admin123', 1, 1),
('Juan Almacenista', 'almacenista@castores.com', 'almacen123', 1, 2);

SELECT 'ROLES:' as tabla;
SELECT * FROM roles;

SELECT 'USUARIOS:' as tabla;
SELECT u.id_usuario, u.nombre, u.correo, u.contrasena, u.estatus, r.nombre as rol 
FROM usuarios u 
INNER JOIN roles r ON u.id_rol = r.id_rol;
