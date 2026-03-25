CREATE TABLE PACIENTES (
    ID_PACIENTE         NUMBER NOT NULL,
    DNI                 VARCHAR2(15) NOT NULL,
    NOMBRE              VARCHAR2(50),
    FECHA_NACIMIENTO    DATE,
    TELEFONO            VARCHAR2(15),
    TIPO_SANGRE         VARCHAR2(5),
    ALERGIAS            VARCHAR2(300),
    FECHA_REGISTRO      DATE,
    ESTADO              VARCHAR2(20),
    CONSTRAINT PACIENTES_PK PRIMARY KEY (ID_PACIENTE),
    CONSTRAINT PACIENTES_DNI_UN UNIQUE (DNI)
);

CREATE TABLE DOCTORES (
    ID_DOCTOR           NUMBER NOT NULL,
    LICENCIA_MEDICA     VARCHAR2(20) NOT NULL,
    NOMBRE              VARCHAR2(50),
    ESPECIALIDAD        VARCHAR2(60),
    AÑOS_EXPERIENCIA    NUMBER,
    TELEFONO            VARCHAR2(15),
    EMAIL               VARCHAR2(100),
    COSTO_CONSULTA      NUMBER(8, 2),
    ESTADO              VARCHAR2(20),
    CONSTRAINT DOCTORES_PK PRIMARY KEY (ID_DOCTOR),
    CONSTRAINT DOCTORES_LICENCIA_MEDICA_UN UNIQUE (LICENCIA_MEDICA)
);

CREATE TABLE DIAGNOSTICOS (
    ID_DIAGNOSTICO      NUMBER NOT NULL,
    CODIGO_CIE10        VARCHAR2(10),
    NOMBRE_DIAGNOSTICO  VARCHAR2(200),
    CATEGORIA           VARCHAR2(100),
    ES_CRONICO          CHAR(1),
    GRAVEDAD            VARCHAR2(20),
    CONSTRAINT DIAGNOSTICOS_PK PRIMARY KEY (ID_DIAGNOSTICO),
    CONSTRAINT DIAGNOSTICOS_CODIGO_CIE10_UN UNIQUE (CODIGO_CIE10)
);

CREATE TABLE CITAS_MEDICAS (
    ID_CITA             NUMBER,
    ID_PACIENTE         NUMBER,
    ID_DOCTOR           NUMBER,
    FECHA_CITA          DATE,
    DURACION_MINUTOS    NUMBER,
    TIPO_CITA           VARCHAR2(20),
    ESTADO              VARCHAR2(20), 
    SINTOMAS            CLOB,
    COSTO_CONSULTA      NUMBER(8, 2),
    CALIFICACION_DOCTOR NUMBER(1),
    CONSTRAINT CITAS_MEDICAS_PK PRIMARY KEY (ID_CITA),
    CONSTRAINT FK_CITA_PACIENTE FOREIGN KEY (ID_PACIENTE)
        REFERENCES PACIENTES (ID_PACIENTE),
    CONSTRAINT FK_CITA_DOCTOR FOREIGN KEY (ID_DOCTOR)
        REFERENCES DOCTORES (ID_DOCTOR)
);

CREATE TABLE TRATAMIENTOS (
    ID_TRATAMIENTO      NUMBER,
    ID_CITA             NUMBER,
    ID_DIAGNOSTICO      NUMBER,
    ID_PACIENTE         NUMBER,
    MEDICAMENTO         VARCHAR2(200),
    DOSIS               VARCHAR2(100),
    FRECUENCIA          VARCHAR2(100),
    FECHA_INICIO        DATE,
    FECHA_FIN           DATE,
    EFECTIVIDAD         NUMBER(2),
    EFECTOS_SECUNDARIOS VARCHAR2(300),
    COSTO_TRATAMIENTO   NUMBER(8, 2),
    CONSTRAINT TRATAMIENTOS_PK PRIMARY KEY (ID_TRATAMIENTO),
    CONSTRAINT FK_TRAT_PACIENTE FOREIGN KEY (ID_PACIENTE)
        REFERENCES PACIENTES (ID_PACIENTE),
    CONSTRAINT FK_TRAT_DIAGNOSTICO FOREIGN KEY (ID_DIAGNOSTICO)
        REFERENCES DIAGNOSTICOS (ID_DIAGNOSTICO),
    CONSTRAINT FK_TRAT_CITA FOREIGN KEY (ID_CITA)
        REFERENCES CITAS_MEDICAS (ID_CITA)
);

-- PACIENTES 
INSERT INTO pacientes VALUES (1, '12345678A', 'María González López', TO_DATE('1985-03-15', 'YYYY-MM-DD'), '600111222', 'O+', 'Penicilina', TO_DATE('2020-01-10', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (2, '23456789B', 'Carlos Rodríguez Martín', TO_DATE('1978-07-22', 'YYYY-MM-DD'), '600222333', 'A-', 'Ninguna', TO_DATE('2019-05-20', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (3, '34567890C', 'Ana Fernández Díaz', TO_DATE('1992-11-30', 'YYYY-MM-DD'), '600333444', 'B+', 'Aspirina, Mariscos', TO_DATE('2021-03-15', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (4, '45678901D', 'Javier López Pérez', TO_DATE('1965-09-10', 'YYYY-MM-DD'), '600444555', 'AB+', 'Ninguna', TO_DATE('2018-11-05', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (5, '56789012E', 'Laura Martínez Sánchez', TO_DATE('1988-12-25', 'YYYY-MM-DD'), '600555666', 'O-', 'Ibuprofeno', TO_DATE('2022-02-28', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (6, '67890123F', 'David García Torres', TO_DATE('1975-04-18', 'YYYY-MM-DD'), '600666777', 'A+', 'Lactosa', TO_DATE('2020-08-12', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (7, '78901234G', 'Elena Castro Ruiz', TO_DATE('1995-08-05', 'YYYY-MM-DD'), '600777888', 'B-', 'Ninguna', TO_DATE('2023-01-20', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (8, '89012345H', 'Pedro Sánchez Molina', TO_DATE('1958-12-30', 'YYYY-MM-DD'), '600888999', 'O+', 'Codeína', TO_DATE('2017-06-15', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (9, '90123456I', 'Isabel Romero Vega', TO_DATE('1982-06-22', 'YYYY-MM-DD'), '600999000', 'AB-', 'Polen, Ácaros', TO_DATE('2021-09-08', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (10, '01234567J', 'Ricardo Navarro Jiménez', TO_DATE('1970-02-14', 'YYYY-MM-DD'), '601000111', 'A+', 'Ninguna', TO_DATE('2019-11-30', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (11, '11223344K', 'Sofia Díaz Herrera', TO_DATE('1998-09-03', 'YYYY-MM-DD'), '601111222', 'O-', 'Penicilina, Nueces', TO_DATE('2022-07-14', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (12, '22334455L', 'Miguel Ángel Ortiz Castro', TO_DATE('1962-11-11', 'YYYY-MM-DD'), '601222333', 'B+', 'Ninguna', TO_DATE('2018-03-25', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (13, '33445566M', 'Carmen Vargas López', TO_DATE('1987-07-07', 'YYYY-MM-DD'), '601333444', 'A-', 'Mariscos', TO_DATE('2020-12-01', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (14, '44556677N', 'Jorge Mendoza Silva', TO_DATE('1973-01-25', 'YYYY-MM-DD'), '601444555', 'O+', 'Ninguna', TO_DATE('2021-04-18', 'YYYY-MM-DD'), 'ACTIVO');
INSERT INTO pacientes VALUES (15, '55667788O', 'Patricia Cruz Martín', TO_DATE('1990-05-19', 'YYYY-MM-DD'), '601555666', 'AB+', 'Aspirina', TO_DATE('2023-02-22', 'YYYY-MM-DD'), 'ACTIVO');

-- DOCTORES (
INSERT INTO doctores VALUES (1, 'LM12345', 'Dr. Roberto Silva Mendoza', 'CARDIOLOGIA', 15, '611111111', 'r.silva@hospital.com', 120.00, 'ACTIVO');
INSERT INTO doctores VALUES (2, 'LM23456', 'Dra. Elena Vargas Castro', 'PEDIATRIA', 12, '622222222', 'e.vargas@hospital.com', 90.00, 'ACTIVO');
INSERT INTO doctores VALUES (3, 'LM34567', 'Dr. Antonio Morales Ruiz', 'TRAUMATOLOGIA', 20, '633333333', 'a.morales@hospital.com', 150.00, 'ACTIVO');
INSERT INTO doctores VALUES (4, 'LM45678', 'Dra. Sofia Herrera Jiménez', 'DERMATOLOGIA', 8, '644444444', 's.herrera@hospital.com', 100.00, 'ACTIVO');
INSERT INTO doctores VALUES (5, 'LM56789', 'Dr. Miguel Ángel Torres', 'NEUROLOGIA', 18, '655555555', 'm.torres@hospital.com', 180.00, 'ACTIVO');
INSERT INTO doctores VALUES (6, 'LM67890', 'Dra. Carolina Ríos Fernández', 'GINECOLOGIA', 14, '666666666', 'c.rios@hospital.com', 130.00, 'ACTIVO');
INSERT INTO doctores VALUES (7, 'LM78901', 'Dr. Fernando Castro Díaz', 'ONCOLOGIA', 22, '677777777', 'f.castro@hospital.com', 200.00, 'ACTIVO');
INSERT INTO doctores VALUES (8, 'LM89012', 'Dra. Marta López García', 'PSIQUIATRIA', 16, '688888888', 'm.lopez@hospital.com', 160.00, 'ACTIVO');
INSERT INTO doctores VALUES (9, 'LM90123', 'Dr. Javier Romero Santos', 'UROLOGIA', 11, '699999999', 'j.romero@hospital.com', 140.00, 'ACTIVO');
INSERT INTO doctores VALUES (10, 'LM01234', 'Dra. Andrea Navarro Cruz', 'ENDOCRINOLOGIA', 9, '610000000', 'a.navarro@hospital.com', 110.00, 'ACTIVO');

-- DIAGNÓSTICOS 
SELECT * FROM doctores;
INSERT INTO diagnosticos VALUES (1, 'I10', 'Hipertensión esencial', 'CARDIOLOGIA', 'S', 'MODERADO');
INSERT INTO diagnosticos VALUES (2, 'E11', 'Diabetes mellitus tipo 2', 'ENDOCRINOLOGIA', 'S', 'MODERADO');
INSERT INTO diagnosticos VALUES (3, 'J45', 'Asma', 'NEUMOLOGIA', 'S', 'LEVE');
INSERT INTO diagnosticos VALUES (4, 'M54', 'Dorsalgia', 'TRAUMATOLOGIA', 'N', 'LEVE');
INSERT INTO diagnosticos VALUES (5, 'L70', 'Acné', 'DERMATOLOGIA', 'N', 'LEVE');
INSERT INTO diagnosticos VALUES (6, 'I20', 'Angina de pecho', 'CARDIOLOGIA', 'S', 'GRAVE');
INSERT INTO diagnosticos VALUES (7, 'G40', 'Epilepsia', 'NEUROLOGIA', 'S', 'GRAVE');
INSERT INTO diagnosticos VALUES (8, 'C50', 'Cáncer de mama', 'ONCOLOGIA', 'S', 'CRITICO');
INSERT INTO diagnosticos VALUES (9, 'N39', 'Infección urinaria', 'UROLOGIA', 'N', 'LEVE');
INSERT INTO diagnosticos VALUES (10, 'F41', 'Trastorno de ansiedad', 'PSIQUIATRIA', 'S', 'MODERADO');
INSERT INTO diagnosticos VALUES (11, 'J06', 'Infección respiratoria aguda', 'PEDIATRIA', 'N', 'LEVE');
INSERT INTO diagnosticos VALUES (12, 'E66', 'Obesidad', 'ENDOCRINOLOGIA', 'S', 'MODERADO');
INSERT INTO diagnosticos VALUES (13, 'N20', 'Cálculo renal', 'UROLOGIA', 'N', 'GRAVE');
INSERT INTO diagnosticos VALUES (14, 'I48', 'Fibrilación auricular', 'CARDIOLOGIA', 'S', 'GRAVE');
INSERT INTO diagnosticos VALUES (15, 'L20', 'Dermatitis atópica', 'DERMATOLOGIA', 'S', 'LEVE');

-- CITAS MÉDICAS 
INSERT INTO citas_medicas VALUES (1, 1, 1, TO_DATE('2024-01-15 09:00', 'YYYY-MM-DD HH24:MI'), 45, 'CONSULTA', 'COMPLETADA', 'Dolor torácico y palpitaciones', 120.00, 5);
INSERT INTO citas_medicas VALUES (2, 2, 3, TO_DATE('2024-01-16 10:30', 'YYYY-MM-DD HH24:MI'), 30, 'URGENCIA', 'COMPLETADA', 'Dolor lumbar agudo', 150.00, 4);
INSERT INTO citas_medicas VALUES (3, 3, 4, TO_DATE('2024-01-17 11:15', 'YYYY-MM-DD HH24:MI'), 25, 'CONSULTA', 'COMPLETADA', 'Erupción cutánea facial', 100.00, 5);
INSERT INTO citas_medicas VALUES (4, 1, 1, TO_DATE('2024-02-01 08:45', 'YYYY-MM-DD HH24:MI'), 40, 'SEGUIMIENTO', 'COMPLETADA', 'Control presión arterial', 120.00, 4);
INSERT INTO citas_medicas VALUES (5, 4, 5, TO_DATE('2024-02-02 16:20', 'YYYY-MM-DD HH24:MI'), 60, 'CONSULTA', 'COMPLETADA', 'Mareos y pérdida de conciencia', 180.00, 5);
INSERT INTO citas_medicas VALUES (6, 2, 3, TO_DATE('2024-02-10 14:00', 'YYYY-MM-DD HH24:MI'), 30, 'SEGUIMIENTO', 'CANCELADA', 'Control evolución dolor lumbar', 150.00, NULL);
INSERT INTO citas_medicas VALUES (7, 5, 2, TO_DATE('2024-02-15 09:30', 'YYYY-MM-DD HH24:MI'), 35, 'CONSULTA', 'NO_ASISTIO', 'Fiebre y tos en niño', 90.00, NULL);
INSERT INTO citas_medicas VALUES (8, 6, 6, TO_DATE('2024-02-20 10:00', 'YYYY-MM-DD HH24:MI'), 50, 'CONSULTA', 'COMPLETADA', 'Control anual ginecológico', 130.00, 5);
INSERT INTO citas_medicas VALUES (9, 7, 7, TO_DATE('2024-02-25 11:30', 'YYYY-MM-DD HH24:MI'), 60, 'CONSULTA', 'COMPLETADA', 'Seguimiento tratamiento oncológico', 200.00, 4);
INSERT INTO citas_medicas VALUES (10, 8, 8, TO_DATE('2024-03-01 15:15', 'YYYY-MM-DD HH24:MI'), 45, 'CONSULTA', 'COMPLETADA', 'Terapia ansiedad generalizada', 160.00, 5);
INSERT INTO citas_medicas VALUES (11, 9, 9, TO_DATE('2024-03-05 08:30', 'YYYY-MM-DD HH24:MI'), 30, 'URGENCIA', 'COMPLETADA', 'Dolor al orinar', 140.00, 3);
INSERT INTO citas_medicas VALUES (12, 10, 10, TO_DATE('2024-03-10 12:00', 'YYYY-MM-DD HH24:MI'), 40, 'CONSULTA', 'COMPLETADA', 'Control diabetes y peso', 110.00, 4);
INSERT INTO citas_medicas VALUES (13, 11, 4, TO_DATE('2024-03-12 16:45', 'YYYY-MM-DD HH24:MI'), 25, 'CONSULTA', 'COMPLETADA', 'Dermatitis en manos', 100.00, 5);
INSERT INTO citas_medicas VALUES (14, 12, 1, TO_DATE('2024-03-15 09:20', 'YYYY-MM-DD HH24:MI'), 50, 'SEGUIMIENTO', 'COMPLETADA', 'Control arritmia cardiaca', 120.00, 4);
INSERT INTO citas_medicas VALUES (15, 13, 3, TO_DATE('2024-03-18 14:30', 'YYYY-MM-DD HH24:MI'), 35, 'CONSULTA', 'COMPLETADA', 'Dolor cervical persistente', 150.00, 3);
INSERT INTO citas_medicas VALUES (16, 14, 5, TO_DATE('2024-03-20 10:45', 'YYYY-MM-DD HH24:MI'), 55, 'CONSULTA', 'COMPLETADA', 'Evaluación migrañas crónicas', 180.00, 5);
INSERT INTO citas_medicas VALUES (17, 15, 2, TO_DATE('2024-03-22 11:00', 'YYYY-MM-DD HH24:MI'), 30, 'CONSULTA', 'CANCELADA', 'Vacunación infantil', 90.00, NULL);
INSERT INTO citas_medicas VALUES (18, 1, 1, TO_DATE('2024-03-25 08:15', 'YYYY-MM-DD HH24:MI'), 40, 'SEGUIMIENTO', 'COMPLETADA', 'Ajuste medicación hipertensión', 120.00, 5);
INSERT INTO citas_medicas VALUES (19, 3, 4, TO_DATE('2024-03-28 13:20', 'YYYY-MM-DD HH24:MI'), 30, 'SEGUIMIENTO', 'COMPLETADA', 'Control evolución acné', 100.00, 4);
INSERT INTO citas_medicas VALUES (20, 6, 6, TO_DATE('2024-04-02 09:45', 'YYYY-MM-DD HH24:MI'), 50, 'CONSULTA', 'NO_ASISTIO', 'Ecografía ginecológica', 130.00, NULL);
INSERT INTO citas_medicas VALUES (21, 8, 8, TO_DATE('2024-04-05 16:00', 'YYYY-MM-DD HH24:MI'), 45, 'CONSULTA', 'COMPLETADA', 'Sesión terapia cognitiva', 160.00, 5);
INSERT INTO citas_medicas VALUES (22, 10, 10, TO_DATE('2024-04-08 12:30', 'YYYY-MM-DD HH24:MI'), 40, 'SEGUIMIENTO', 'COMPLETADA', 'Control glucemia y dieta', 110.00, 4);
INSERT INTO citas_medicas VALUES (23, 12, 1, TO_DATE('2024-04-12 10:15', 'YYYY-MM-DD HH24:MI'), 50, 'CONSULTA', 'COMPLETADA', 'Holter de 24 horas', 120.00, 5);
INSERT INTO citas_medicas VALUES (24, 14, 7, TO_DATE('2024-04-15 14:45', 'YYYY-MM-DD HH24:MI'), 60, 'CONSULTA', 'COMPLETADA', 'Quimioterapia ciclo 3', 200.00, 4);
INSERT INTO citas_medicas VALUES (25, 15, 9, TO_DATE('2024-04-18 11:30', 'YYYY-MM-DD HH24:MI'), 35, 'URGENCIA', 'COMPLETADA', 'Cólico renal agudo', 140.00, 3);

-- TRATAMIENTOS 
INSERT INTO tratamientos VALUES (1, 1, 1, 1, 'Losartán 50mg', '1 tableta al día', 'Cada 24 horas', TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_DATE('2024-04-15', 'YYYY-MM-DD'), 8, 'Mareo leve ocasional', 45.50);
INSERT INTO tratamientos VALUES (2, 2, 4, 2, 'Ibuprofeno 600mg', '1 tableta cada 8 horas', 'Con comida', TO_DATE('2024-01-16', 'YYYY-MM-DD'), TO_DATE('2024-01-23', 'YYYY-MM-DD'), 9, 'Ninguno', 12.75);
INSERT INTO tratamientos VALUES (3, 3, 5, 3, 'Crema tretinoína 0.05%', 'Aplicar noche', 'Una vez al día', TO_DATE('2024-01-17', 'YYYY-MM-DD'), TO_DATE('2024-03-17', 'YYYY-MM-DD'), 7, 'Sequedad cutánea', 28.30);
INSERT INTO tratamientos VALUES (4, 4, 1, 1, 'Losartán 50mg + Hidroclorotiazida', '1 tableta al día', 'Cada 24 horas', TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_DATE('2024-05-01', 'YYYY-MM-DD'), 9, 'Ninguno', 52.80);
INSERT INTO tratamientos VALUES (5, 5, 7, 4, 'Levetiracetam 500mg', '1 tableta cada 12 horas', 'Con o sin comida', TO_DATE('2024-02-02', 'YYYY-MM-DD'), NULL, 8, 'Somnolencia inicial', 120.45);
INSERT INTO tratamientos VALUES (6, 8, 9, 6, 'Anticonceptivos orales', '1 tableta diaria', 'Misma hora cada día', TO_DATE('2024-02-20', 'YYYY-MM-DD'), TO_DATE('2025-02-20', 'YYYY-MM-DD'), 9, 'Náuseas leves', 35.20);
INSERT INTO tratamientos VALUES (7, 9, 8, 7, 'Paclitaxel + Carboplatino', 'Infusión intravenosa', 'Cada 3 semanas', TO_DATE('2024-02-25', 'YYYY-MM-DD'), NULL, 7, 'Náuseas, caída cabello', 850.00);
INSERT INTO tratamientos VALUES (8, 10, 10, 8, 'Sertralina 50mg', '1 tableta al día', 'Por la mañana', TO_DATE('2024-03-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 8, 'Insomnio inicial', 42.15);
INSERT INTO tratamientos VALUES (9, 11, 9, 9, 'Ciprofloxacino 500mg', '1 tableta cada 12 horas', 'Con agua abundante', TO_DATE('2024-03-05', 'YYYY-MM-DD'), TO_DATE('2024-03-12', 'YYYY-MM-DD'), 9, 'Ninguno', 18.90);
INSERT INTO tratamientos VALUES (10, 12, 2, 10, 'Metformina 850mg', '1 tableta cada 12 horas', 'Con las comidas', TO_DATE('2024-03-10', 'YYYY-MM-DD'), NULL, 8, 'Malestar gastrointestinal', 25.60);
INSERT INTO tratamientos VALUES (11, 13, 15, 11, 'Crema de corticoides', 'Aplicar 2 veces/día', 'Mañana y noche', TO_DATE('2024-03-12', 'YYYY-MM-DD'), TO_DATE('2024-04-12', 'YYYY-MM-DD'), 7, 'Sequedad localizada', 22.40);
INSERT INTO tratamientos VALUES (12, 14, 14, 12, 'Digoxina 0.25mg', '1 tableta al día', 'Control estricto', TO_DATE('2024-03-15', 'YYYY-MM-DD'), NULL, 9, 'Ninguno', 38.75);
INSERT INTO tratamientos VALUES (13, 15, 4, 13, 'Relajante muscular', '1 tableta cada 8 horas', 'Con comida', TO_DATE('2024-03-18', 'YYYY-MM-DD'), TO_DATE('2024-03-25', 'YYYY-MM-DD'), 8, 'Somnolencia', 15.30);
INSERT INTO tratamientos VALUES (14, 16, 7, 14, 'Topiramato 50mg', '1 tableta cada 12 horas', 'Con o sin comida', TO_DATE('2024-03-20', 'YYYY-MM-DD'), NULL, 7, 'Pérdida de peso, parestesias', 65.80);
INSERT INTO tratamientos VALUES (15, 18, 1, 1, 'Losartán 100mg', '1 tableta al día', 'Cada 24 horas', TO_DATE('2024-03-25', 'YYYY-MM-DD'), TO_DATE('2024-06-25', 'YYYY-MM-DD'), 9, 'Ninguno', 48.90);
INSERT INTO tratamientos VALUES (16, 19, 5, 3, 'Crema tretinoína 0.1%', 'Aplicar noche', 'Noche alternas', TO_DATE('2024-03-28', 'YYYY-MM-DD'), TO_DATE('2024-05-28', 'YYYY-MM-DD'), 8, 'Enrojecimiento leve', 32.10);
INSERT INTO tratamientos VALUES (17, 21, 10, 8, 'Sertralina 100mg + Clonazepam', '1 tableta de cada', 'Sertralina mañana, Clonazepam noche', TO_DATE('2024-04-05', 'YYYY-MM-DD'), TO_DATE('2024-10-05', 'YYYY-MM-DD'), 8, 'Somnolencia matutina', 67.25);
INSERT INTO tratamientos VALUES (18, 22, 2, 10, 'Metformina 850mg + Insulina', 'Metformina 2 veces/día, Insulina 1 vez/día', 'Con comidas principales', TO_DATE('2024-04-08', 'YYYY-MM-DD'), NULL, 9, 'Hipoglucemia ocasional', 89.40);
INSERT INTO tratamientos VALUES (19, 23, 6, 12, 'Aspirina 100mg', '1 tableta al día', 'Por la mañana', TO_DATE('2024-04-12', 'YYYY-MM-DD'), NULL, 9, 'Ninguno', 12.30);
INSERT INTO tratamientos VALUES (20, 25, 13, 15, 'Analgésicos + Antiespasmódicos', '1 tableta cada 6 horas', 'Según dolor', TO_DATE('2024-04-18', 'YYYY-MM-DD'), TO_DATE('2024-04-25', 'YYYY-MM-DD'), 8, 'Somnolencia', 28.75);

COMMIT;

//1-Mostrar pacientes con enfermedades crónicas y sus tratamientos vigentes.


SELECT
    P.NOMBRE AS PACIENTE,
    D.NOMBRE_DIAGNOSTICO AS DIAGNOSTICO_CRONICO,
    T.MEDICAMENTO,
    T.FECHA_INICIO
FROM
    PACIENTES P
JOIN
    TRATAMIENTOS T ON P.ID_PACIENTE = T.ID_PACIENTE
JOIN
    DIAGNOSTICOS D ON T.ID_DIAGNOSTICO = D.ID_DIAGNOSTICO
WHERE
    D.ES_CRONICO = 'S' 
    AND (T.FECHA_FIN IS NULL OR T.FECHA_FIN >= SYSDATE);
    
    
//2- Calcular efectividad media agrupada por especialidad del doctor.
SELECT
    DO.ESPECIALIDAD,
    ROUND(AVG(TR.EFECTIVIDAD), 2) AS EFECTIVIDAD_MEDIA
FROM
    TRATAMIENTOS TR
JOIN
    CITAS_MEDICAS CM ON TR.ID_CITA = CM.ID_CITA
JOIN
    DOCTORES DO ON CM.ID_DOCTOR = DO.ID_DOCTOR
GROUP BY
    DO.ESPECIALIDAD
ORDER BY
    EFECTIVIDAD_MEDIA DESC;
    
//3- Ranking de doctores por relación calidad-precio.


WITH DoctorEfectividad AS (
    SELECT
        D.ID_DOCTOR,
        D.NOMBRE AS DOCTOR,
        D.COSTO_CONSULTA,
        AVG(T.EFECTIVIDAD) AS EFECTIVIDAD_MEDIA
    FROM
        DOCTORES D
    JOIN
        CITAS_MEDICAS CM ON D.ID_DOCTOR = CM.ID_DOCTOR
    JOIN
        TRATAMIENTOS T ON CM.ID_CITA = T.ID_CITA 
    GROUP BY
        D.ID_DOCTOR, D.NOMBRE, D.COSTO_CONSULTA
)
SELECT
    DOCTOR,
    COSTO_CONSULTA,
    EFECTIVIDAD_MEDIA,
    ROUND(EFECTIVIDAD_MEDIA / COSTO_CONSULTA, 4) AS RELACION_CALIDAD_PRECIO,
    RANK() OVER (ORDER BY (EFECTIVIDAD_MEDIA / COSTO_CONSULTA) DESC) AS RANKING
FROM
    DoctorEfectividad
WHERE
    COSTO_CONSULTA > 0;
    
//4- Total gastado por paciente en consultas y tratamientos, por especialidad.


WITH GastosPaciente AS (
    SELECT
        C.ID_PACIENTE,
        D.ESPECIALIDAD,
        C.COSTO_CONSULTA AS MONTO
    FROM
        CITAS_MEDICAS C
    JOIN
        DOCTORES D ON C.ID_DOCTOR = D.ID_DOCTOR
    UNION ALL
    SELECT
        T.ID_PACIENTE,
        D.ESPECIALIDAD,
        T.COSTO_TRATAMIENTO AS MONTO
    FROM
        TRATAMIENTOS T
    JOIN
        CITAS_MEDICAS C ON T.ID_CITA = C.ID_CITA
    JOIN
        DOCTORES D ON C.ID_DOCTOR = D.ID_DOCTOR
)
SELECT
    P.NOMBRE AS PACIENTE,
    GP.ESPECIALIDAD,
    SUM(GP.MONTO) AS GASTO_TOTAL_POR_ESPECIALIDAD
FROM
    GastosPaciente GP
JOIN
    PACIENTES P ON GP.ID_PACIENTE = P.ID_PACIENTE
GROUP BY
    P.NOMBRE, GP.ESPECIALIDAD
ORDER BY
    P.NOMBRE, GASTO_TOTAL_POR_ESPECIALIDAD DESC;
    
    
//5- Identificar pacientes con 2+ diagnósticos crónicos diferentes.


SELECT
    P.NOMBRE AS PACIENTE,
    COUNT(DISTINCT D.CODIGO_CIE10) AS NUM_DIAGNOSTICOS_CRONICOS
FROM
    PACIENTES P
JOIN
    TRATAMIENTOS T ON P.ID_PACIENTE = T.ID_PACIENTE
JOIN
    DIAGNOSTICOS D ON T.ID_DIAGNOSTICO = D.ID_DIAGNOSTICO
WHERE
    D.ES_CRONICO = 'S'
GROUP BY
    P.NOMBRE
HAVING
    COUNT(DISTINCT D.CODIGO_CIE10) >= 2
ORDER BY
    NUM_DIAGNOSTICOS_CRONICOS DESC;
    
Análisis mensual de consultas por especialidad y tipo de cita.
SQL

SELECT
    TO_CHAR(FECHA_CITA, 'YYYY-MM') AS MES_AÑO,
    D.ESPECIALIDAD,
    C.TIPO_CITA,
    COUNT(C.ID_CITA) AS TOTAL_CONSULTAS
FROM
    CITAS_MEDICAS C
JOIN
    DOCTORES D ON C.ID_DOCTOR = D.ID_DOCTOR
GROUP BY
    TO_CHAR(FECHA_CITA, 'YYYY-MM'),
    D.ESPECIALIDAD,
    C.TIPO_CITA
ORDER BY
    MES_AÑO, D.ESPECIALIDAD, C.TIPO_CITA;
    

//6- CREAR VIEW con métricas clave del hospital (Eficiencia Hospitalaria).


CREATE OR REPLACE VIEW V_METRICAS_EFICIENCIA AS
SELECT
    (SELECT COUNT(DISTINCT ID_PACIENTE) FROM PACIENTES) AS TOTAL_PACIENTES_REGISTRADOS,
    (SELECT COUNT(ID_CITA) FROM CITAS_MEDICAS) AS TOTAL_CITAS,
    (SELECT COUNT(ID_TRATAMIENTO) FROM TRATAMIENTOS) AS TOTAL_TRATAMIENTOS,
    ROUND((SELECT AVG(EFECTIVIDAD) FROM TRATAMIENTOS), 2) AS EFECTIVIDAD_MEDIA_TRATAMIENTO,
    ROUND((SELECT AVG(CALIFICACION_DOCTOR) FROM CITAS_MEDICAS), 2) AS CALIFICACION_MEDIA_DOCTOR,
    (
        SELECT
            D.ESPECIALIDAD
        FROM
            CITAS_MEDICAS C2
        JOIN
            DOCTORES D ON C2.ID_DOCTOR = D.ID_DOCTOR
        GROUP BY
            D.ESPECIALIDAD
        ORDER BY
            COUNT(C2.ID_CITA) DESC
        FETCH FIRST 1 ROWS ONLY
    ) AS ESPECIALIDAD_MAS_DEMANDADA
FROM
    DUAL;
    
SELECT * FROM V_METRICAS_EFICIENCIA;

    
    
//7- CREAR PROCEDURE que valide disponibilidad al programar cita.

CREATE OR REPLACE PROCEDURE SP_VALIDAR_DISPONIBILIDAD (
    p_id_doctor         IN NUMBER,
    p_fecha_cita        IN DATE,
    p_duracion_minutos  IN NUMBER,
    p_disponible        OUT CHAR
)
IS
    v_fecha_inicio_nueva DATE := p_fecha_cita;
    v_fecha_fin_nueva    DATE := p_fecha_cita + (p_duracion_minutos / 1440);
    v_conteo_solapes     NUMBER;
BEGIN
    SELECT
        COUNT(*) INTO v_conteo_solapes
    FROM
        CITAS_MEDICAS
    WHERE
        ID_DOCTOR = p_id_doctor
        AND (
            FECHA_CITA BETWEEN v_fecha_inicio_nueva AND v_fecha_fin_nueva
            OR (FECHA_CITA + (DURACION_MINUTOS / 1440)) BETWEEN v_fecha_inicio_nueva AND v_fecha_fin_nueva
            OR (FECHA_CITA <= v_fecha_inicio_nueva AND (FECHA_CITA + (DURACION_MINUTOS / 1440)) >= v_fecha_fin_nueva)
        );

    IF v_conteo_solapes = 0 THEN
        p_disponible := 'S'; 
    ELSE
        p_disponible := 'N'; 
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_disponible := 'E'; 
        RAISE;
END SP_VALIDAR_DISPONIBILIDAD;
/

DECLARE
    v_disponible CHAR(1);
    
    
    p_id_doctor NUMBER := 1;
    p_fecha_prueba DATE := TO_DATE('2024-01-15 09:15', 'YYYY-MM-DD HH24:MI');
    p_duracion NUMBER := 30; -- 30 minutos
    
BEGIN
    SP_VALIDAR_DISPONIBILIDAD(
        p_id_doctor         => p_id_doctor,
        p_fecha_cita        => p_fecha_prueba,
        p_duracion_minutos  => p_duracion,
        p_disponible        => v_disponible
    );

    DBMS_OUTPUT.PUT_LINE('Doctor ID: ' || p_id_doctor);
    DBMS_OUTPUT.PUT_LINE('Fecha/Hora a verificar: ' || TO_CHAR(p_fecha_prueba, 'YYYY-MM-DD HH24:MI'));
    DBMS_OUTPUT.PUT_LINE('Disponibilidad: ' || 
        CASE v_disponible
            WHEN 'S' THEN 'SÍ (Disponible)'
            WHEN 'N' THEN 'NO (Solapamiento encontrado)'
            ELSE 'ERROR'
        END
    );
END;
/

//8-CREAR FUNCTION que Calcule costo total del paciente.
SQL

CREATE OR REPLACE FUNCTION FN_COSTO_TOTAL_PACIENTE (p_id_paciente IN NUMBER)
RETURN NUMBER
IS
    v_costo_consultas NUMBER(10, 2) := 0;
    v_costo_tratamientos NUMBER(10, 2) := 0;
BEGIN
    SELECT
        NVL(SUM(COSTO_CONSULTA), 0) INTO v_costo_consultas
    FROM
        CITAS_MEDICAS
    WHERE
        ID_PACIENTE = p_id_paciente;

    SELECT
        NVL(SUM(COSTO_TRATAMIENTO), 0) INTO v_costo_tratamientos
    FROM
        TRATAMIENTOS
    WHERE
        ID_PACIENTE = p_id_paciente;

    RETURN v_costo_consultas + v_costo_tratamientos;
END FN_COSTO_TOTAL_PACIENTE;
/

SELECT
    FN_COSTO_TOTAL_PACIENTE(2) AS COSTO_TOTAL
FROM
    DUAL;
    
//9- Análisis mensual de consultas por especialidad y tipo de cita.


SELECT
    TO_CHAR(FECHA_CITA, 'YYYY-MM') AS MES_AÑO,
    D.ESPECIALIDAD,
    C.TIPO_CITA,
    COUNT(C.ID_CITA) AS TOTAL_CONSULTAS
FROM
    CITAS_MEDICAS C
JOIN
    DOCTORES D ON C.ID_DOCTOR = D.ID_DOCTOR
GROUP BY
    TO_CHAR(FECHA_CITA, 'YYYY-MM'),
    D.ESPECIALIDAD,
    C.TIPO_CITA
ORDER BY
    MES_AÑO, D.ESPECIALIDAD, C.TIPO_CITA;
    
//10-Análisis de correlación entre medicamentos y efectos secundarios.

SELECT
    MEDICAMENTO,
    EFECTOS_SECUNDARIOS,
    COUNT(*) AS FRECUENCIA_COMBINACION
FROM
    TRATAMIENTOS
WHERE
    MEDICAMENTO IS NOT NULL
    AND EFECTOS_SECUNDARIOS IS NOT NULL
GROUP BY
    MEDICAMENTO, EFECTOS_SECUNDARIOS
ORDER BY
    FRECUENCIA_COMBINACION DESC;
    

