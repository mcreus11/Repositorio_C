-- ######################################################################
-- TRIGGER UNIFICADO PARA INSERT, UPDATE Y DELETE
-- ######################################################################
CREATE OR REPLACE TRIGGER TR_MULTIPLE_CEL
AFTER INSERT OR UPDATE OR DELETE ON CELULAR
FOR EACH ROW
DECLARE
    LV_ID_BIT NUMBER;
    LV_USUARIO NVARCHAR2(100);
    LV_FECHA DATE;
BEGIN
    -- RECUPERAR ID VÁLIDO USANDO SECUENCIAS, USUARIO Y FECHA
    -- NOTA: ID_BIT_CEL debe ser una secuencia existente (CREATE SEQUENCE ID_BIT_CEL START WITH 1;).
    SELECT ID_BIT_CEL.NEXTVAL, USER, SYSDATE INTO LV_ID_BIT, LV_USUARIO, LV_FECHA FROM DUAL;

    -- LÓGICA INSERT: Solo usa :NEW (Valores viejos son NULL)
    IF INSERTING THEN
        INSERT INTO CELULAR_BIT(ID_BIT, ID_CELULAR, MARCA, MODELO, RAM, PROCESADOR, PRECIO, MARCA_OLD, MODELO_OLD, RAM_OLD,
        PROCESADOR_OLD, PRECIO_OLD, USUARIO, FECHA_MOV, MOVIMIENTO)
        VALUES (LV_ID_BIT, :NEW.ID_CELULAR, :NEW.MARCA, :NEW.MODELO, :NEW.RAM, :NEW.PROCESADOR, :NEW.PRECIO, NULL, NULL, NULL,
        NULL, NULL, LV_USUARIO, LV_FECHA, 'INSERT');

    -- LÓGICA UPDATE: Usa :NEW (Valores nuevos) y :OLD (Valores viejos)
    ELSIF UPDATING THEN
        INSERT INTO CELULAR_BIT(ID_BIT, ID_CELULAR, MARCA, MODELO, RAM, PROCESADOR, PRECIO, MARCA_OLD, MODELO_OLD, RAM_OLD,
        PROCESADOR_OLD, PRECIO_OLD, USUARIO, FECHA_MOV, MOVIMIENTO)
        VALUES (LV_ID_BIT, :NEW.ID_CELULAR, :NEW.MARCA, :NEW.MODELO, :NEW.RAM, :NEW.PROCESADOR, :NEW.PRECIO, 
                :OLD.MARCA, :OLD.MODELO, :OLD.RAM, :OLD.PROCESADOR, :OLD.PRECIO, -- Corrección aquí
                LV_USUARIO, LV_FECHA, 'UPDATE');

    -- LÓGICA DELETE: Solo usa :OLD (Valores nuevos son NULL)
    ELSIF DELETING THEN
        INSERT INTO CELULAR_BIT(ID_BIT, ID_CELULAR, MARCA, MODELO, RAM, PROCESADOR, PRECIO, MARCA_OLD, MODELO_OLD, RAM_OLD,
        PROCESADOR_OLD, PRECIO_OLD, USUARIO, FECHA_MOV, MOVIMIENTO)
        VALUES (LV_ID_BIT, :OLD.ID_CELULAR, NULL, NULL, NULL, NULL, NULL, -- Valores NUEVOS deben ser NULL
                :OLD.MARCA, :OLD.MODELO, :OLD.RAM, :OLD.PROCESADOR, :OLD.PRECIO, 
                LV_USUARIO, LV_FECHA, 'DELETE');
    END IF;

END;
/

-- ######################################################################
-- CORRECCIÓN Y PRUEBA COMPLETA
-- Se asume que las tablas CELULAR y CELULAR_BIT ya existen y están vacías.
-- ######################################################################

-- 1. CREACIÓN DE LA SECUENCIA REQUERIDA POR EL TRIGGER
CREATE SEQUENCE ID_BIT_CEL START WITH 1 INCREMENT BY 1 NOCACHE;
/

-- 2. Limpieza (Asegura que CELULAR_BIT esté vacía para la prueba)
DELETE FROM CELULAR; -- Asegurar que CELULAR también esté vacía para insertar ID=3
DELETE FROM CELULAR_BIT;

-- 3. Inserción (Activa el trigger)
INSERT INTO CELULAR VALUES (3, 'SAMSUNG', 'GALAXI SFE3', '4GB', 'OCTACORE', 2300);
-- El trigger registra un 'INSERT' con ID=3

-- 4. Actualización (Activa el trigger)
-- Corregido: Actualizar el ID que realmente existe (ID=3)
UPDATE CELULAR SET RAM = '8GB', PRECIO = 2500 WHERE ID_CELULAR = 3; 
-- El trigger registra un 'UPDATE' con ID=3 (old RAM: 4GB, new RAM: 8GB)

-- 5. Eliminación (Activa el trigger)
DELETE FROM CELULAR WHERE ID_CELULAR = 3;
-- El trigger registra un 'DELETE' con ID=3

-- 6. Resultado
SELECT 'Contenido de CELULAR:' AS TABLA, C.* FROM CELULAR C;
SELECT 'Contenido de CELULAR_BIT:' AS TABLA, B.* FROM CELULAR_BIT B;

