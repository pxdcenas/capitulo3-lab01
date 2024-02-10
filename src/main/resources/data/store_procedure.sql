DELIMITER $$
USE `capitulo3`$$
CREATE PROCEDURE `ObtenerEmpleadoPorId` (IN _id BIGINT)
BEGIN
	SELECT * FROM tbl_empleados WHERE id = _id;
END$$

DELIMITER ;