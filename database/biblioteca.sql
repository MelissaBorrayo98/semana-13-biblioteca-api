CREATE DATABASE biblioteca_personal;

CREATE TABLE libro (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    anio_publicacion INT,
    precio NUMERIC(10, 2),
    disponible BOOLEAN DEFAULT TRUE
);