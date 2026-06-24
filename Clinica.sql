CREATE DATABASE clinica;

CREATE TABLE pacientes (
    idpaciente      VARCHAR(10)     NOT NULL PRIMARY KEY,
    nombre          VARCHAR(100)    NOT NULL,
    direccion       VARCHAR(150),
    telefono        VARCHAR(15),
    email           VARCHAR(100),
    fecha_nacimiento DATE,
    tipo_paciente   VARCHAR(20)     NOT NULL  -- 'PARTICULAR', 'ASEGURADO', 'CONVENIO'
);

CREATE TABLE medicos (
    idmedico        VARCHAR(10)     NOT NULL PRIMARY KEY,
    nombre          VARCHAR(100)    NOT NULL,
    direccion       VARCHAR(150),
    telefono        VARCHAR(15),
    email           VARCHAR(100),
    cmp             VARCHAR(20)     NOT NULL,  -- Código del Colegio Médico del Perú
    idespecialidad  VARCHAR(10)     NOT NULL,
    CONSTRAINT fk_medico_especialidad FOREIGN KEY (idespecialidad)
        REFERENCES especialidades(idespecialidad)
);

CREATE TABLE sedes (
    idsede      VARCHAR(10)     NOT NULL PRIMARY KEY,
    nombre      VARCHAR(100)    NOT NULL,
    direccion   VARCHAR(150)    NOT NULL,
    telefono    VARCHAR(15),
    email       VARCHAR(100),
    distrito    VARCHAR(80),
    ciudad      VARCHAR(80)
);

CREATE TABLE especialidades (
    idespecialidad  VARCHAR(10)     NOT NULL PRIMARY KEY,
    nombre          VARCHAR(100)    NOT NULL,
    descripcion     VARCHAR(250),
    area            VARCHAR(80)     -- 'CLINICA', 'QUIRURGICA', 'DIAGNOSTICO'
);

-- 1. Crear el login a nivel de servidor
CREATE LOGIN sa1 WITH PASSWORD = '12345678';

-- 3. Usar la base de datos
USE clinica;

-- 4. Crear el usuario dentro de la base de datos
CREATE USER sa1 FOR LOGIN sa1;

-- 5. Darle permisos completos
ALTER ROLE db_owner ADD MEMBER sa1;

-- 1. INSERCIÓN EN ESPECIALIDADES (Tabla maestra)
INSERT INTO especialidades (idespecialidad, nombre, descripcion, area) VALUES
('ESP001', 'Cardiología', 'Prevención, diagnóstico y tratamiento de enfermedades del corazón.', 'CLINICA'),
('ESP002', 'Cirugía General', 'Tratamiento quirúrgico de enfermedades del aparato digestivo y pared abdominal.', 'QUIRURGICA'),
('ESP003', 'Pediatría', 'Atención médica integral desde el nacimiento hasta la adolescencia.', 'CLINICA'),
('ESP004', 'Radiología', 'Diagnóstico por imágenes médicas (Rayos X, Ecografías, Resonancias).', 'DIAGNOSTICO'),
('ESP005', 'Ginecología y Obstetricia', 'Cuidado del sistema reproductor femenino y control del embarazo.', 'QUIRURGICA');

-- 2. INSERCIÓN EN SEDES (Tabla maestra)
INSERT INTO sedes (idsede, nombre, direccion, telefono, email, distrito, ciudad) VALUES
('SED001', 'Sede Principal San Isidro', 'Av. Javier Prado Este 1230', '01-411-9000', 'informes.si@clinica.pe', 'San Isidro', 'Lima'),
('SED002', 'Sede Norte Los Olivos', 'Av. Carlos Izaguirre 455', '01-411-9001', 'informes.no@clinica.pe', 'Los Olivos', 'Lima'),
('SED003', 'Sede Sur Miraflores', 'Av. Larco 875', '01-411-9002', 'informes.sur@clinica.pe', 'Miraflores', 'Lima');

-- 3. INSERCIÓN EN PACIENTES (Tabla maestra)
INSERT INTO pacientes (idpaciente, nombre, direccion, telefono, email, fecha_nacimiento, tipo_paciente) VALUES
('PAC0000001', 'Carlos Alberto Mendoza Ruiz', 'Av. Arequipa 2450, Dpto 402', '998765432', 'carlos.mendoza@email.com', '1985-04-12', 'PARTICULAR'),
('PAC0000002', 'María Elena Torres Castro', 'Calle Los Jazmines 118', '987654321', 'maria.torres@email.com', '1992-09-25', 'ASEGURADO'),
('PAC0000003', 'Juan Diego Quispe Mamani', 'Jr. Puno 456', '954123789', 'juan.quispe@email.com', '1978-11-02', 'CONVENIO'),
('PAC0000004', 'Ana Sofía Benavides Prado', 'Av. Benavides 1530', '963852741', 'ana.benavides@email.com', '2015-07-19', 'ASEGURADO'),
('PAC0000005', 'Luis Fernando Gómez Vega', 'Av. Universitaria 3410', '912345678', 'luis.gomez@email.com', '1960-01-30', 'PARTICULAR');

-- 4. INSERCIÓN EN MEDICOS (Depende de Especialidades)
INSERT INTO medicos (idmedico, nombre, direccion, telefono, email, cmp, idespecialidad) VALUES
('MED0000001', 'Dr. Alejandro Prado Vargas', 'Av. Salaverry 2840', '945612378', 'aprado@medicos.clinica.pe', 'CMP-045213', 'ESP001'), -- Cardiología
('MED0000002', 'Dra. Beatriz Zegarra Montes', 'Calle Las Magnolias 320', '951753468', 'bzegarra@medicos.clinica.pe', 'CMP-058964', 'ESP003'), -- Pediatría
('MED0000003', 'Dr. Christian Villanueva Ruiz', 'Av. El Polo 745', '984512637', 'cvillanueva@medicos.clinica.pe', 'CMP-032145', 'ESP002'), -- Cirugía General
('MED0000004', 'Dra. Diana Flores Falconí', 'Jr. Batallón Callao 150', '976123485', 'dflores@medicos.clinica.pe', 'CMP-067412', 'ESP005'), -- Ginecología
('MED0000005', 'Dr. Edgardo Merino Santos', 'Av. Las Palmeras 512', '932145698', 'emerino@medicos.clinica.pe', 'CMP-029856', 'ESP004'); -- Radiología