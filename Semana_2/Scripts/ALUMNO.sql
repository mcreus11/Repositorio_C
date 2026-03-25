CREATE TABLE ALUMNO(
    ID NUMBER PRIMARY KEY,
    NOMBRE   NVARCHAR2(60) NOT NULL,
    EDAD     NUMBER,
    CARRERA  NVARCHAR2(40),
    PROMEDIO NUMBER,
    GENERO   CHAR(1)CHECK (GENERO IN ('M','F'))
);

INSERT INTO alumno VALUES (1, 'Gerardo Jimenes', 23, 'Sistemas',       8.9, 'M');
INSERT INTO alumno VALUES (2, 'María López',     21, 'Informatica',    9.3, 'F');
INSERT INTO alumno VALUES (3, 'Carlos Mendoza',  22, 'Derecho',        7.8, 'M');
INSERT INTO alumno VALUES (4, 'Ana Martínez',    24, 'Sistemas',       8.1, 'F');
INSERT INTO alumno VALUES (5, 'Luis Hernández',  20, 'Administración', 6.9, 'M');
INSERT INTO alumno VALUES (6, 'Sofía Morales',   23, 'Informatica',    8.5, 'F');
INSERT INTO alumno VALUES (7, 'Jorge Ramírez',   25, 'Sistemas',       9.7, 'M');
INSERT INTO alumno VALUES (8, 'Elena Muñoz',     22, 'Contabilidad',   8.0, 'F');

COMMIT;

/* 1.- Mostrar todos los alumnos registrados */
SELECT * FROM ALUMNO;

/* 2.- Mostrar el nombre y carrera de todos los alumnos */
SELECT NOMBRE, CARRERA FROM ALUMNO;

/* 3.- Nombres y promedios con promedio > 8.0 */
SELECT NOMBRE, PROMEDIO
FROM ALUMNO
WHERE PROMEDIO > 8.0;

/* 4.- Alumnos de la carrera de Sistemas (sin sensibilidad a mayúsculas) */
SELECT * FROM ALUMNO
WHERE UPPER(CARRERA) = 'SISTEMAS';

/* 5.- Alumnos con edad >= 22 años */
SELECT * FROM ALUMNO
WHERE EDAD >= 22;

/* 6.- Alumnos ordenados por NOMBRE (A → Z)*/
SELECT * FROM ALUMNO
ORDER BY NOMBRE ASC;

/* 7.- Alumnos de género femenino */
SELECT * FROM ALUMNO
WHERE GENERO = 'F';

/* 8.- Alumnos ordenados por promedio (más bajo → más alto) */
SELECT * FROM ALUMNO
ORDER BY PROMEDIO ASC;

/* 9.- Contar cuántos alumnos hay en total */
SELECT COUNT(*) AS TOTAL_ALUM
FROM ALUMNO;

/* 10.- Contar cuántos alumnos hay por carrera */
SELECT CARRERA, COUNT(*) AS TOTAL
FROM ALUMNO
GROUP BY CARRERA
ORDER BY CARRERA;

/* 11.- Promedio general de todos los alumnos */
SELECT ROUND(AVG(PROMEDIO), 2) AS PROMEDIO
FROM ALUMNO;

/* 12.- Alumno(s) con el mejor promedio */
SELECT * FROM ALUMNO
WHERE PROMEDIO = (SELECT MAX(PROMEDIO) FROM ALUMNO);

/* 13.- Alumno(s) con el peor promedio */
SELECT * FROM ALUMNO
WHERE PROMEDIO = (SELECT MIN(PROMEDIO) FROM ALUMNO);

/* 14.- Edad promedio de los alumnos de Informatica */
SELECT ROUND(AVG(EDAD), 1) AS EDAD_PROMEDIO
FROM ALUMNO
WHERE UPPER(CARRERA) = 'INFORMATICA';

/* 15.- Cuántos alumnos son hombres y cuántas son mujeres */
SELECT GENERO, COUNT(*) AS TOTAL
FROM ALUMNO
GROUP BY GENERO;

/* 16.- Alumnos cuyo NOMBREinicia con M */
SELECT * FROM ALUMNO
WHERE NOMBRE LIKE '% M%';

/* 17.- Formato con pipe ||  */
SELECT
  'El alumno ' || NOMBRE ||
  ' de edad ' || EDAD ||
  ', cursa la carrera de ' || CARRERA ||
  ' con un promedio de ' || PROMEDIO AS DESCRIPCION
FROM ALUMNO;

