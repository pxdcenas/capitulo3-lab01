-- Insertar en la tabla estacionamientos
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 1');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 2');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 3');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 4');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 5');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 6');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 7');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 8');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 9');
INSERT INTO tbl_estacionamientos (ubicacion) VALUES ('Estacionamiento 10');

-- Insertar en la tabla departamentos
INSERT INTO tbl_departamentos (descripcion) VALUES ('Ventas');
INSERT INTO tbl_departamentos (descripcion) VALUES ('Marketing');
INSERT INTO tbl_departamentos (descripcion) VALUES ('Finanzas');

-- Insertar en la tabla proyectos
INSERT INTO tbl_proyectos (descripcion) VALUES ('Proyecto 1');
INSERT INTO tbl_proyectos (descripcion) VALUES ('Proyecto 2');
INSERT INTO tbl_proyectos (descripcion) VALUES ('Proyecto 3');
INSERT INTO tbl_proyectos (descripcion) VALUES ('Proyecto 4');
INSERT INTO tbl_proyectos (descripcion) VALUES ('Proyecto 5');

-- Insertar en la tabla tareas
-- Nota: Los valores para empleado_id se llenarán después de insertar los empleados
INSERT INTO tbl_tareas (descripcion, empleado_id) VALUES ('Tarea 1', NULL);
INSERT INTO tbl_tareas (descripcion, empleado_id) VALUES ('Tarea 2', NULL);
INSERT INTO tbl_tareas (descripcion, empleado_id) VALUES ('Tarea 3', NULL);
INSERT INTO tbl_tareas (descripcion, empleado_id) VALUES ('Tarea 4', NULL);
INSERT INTO tbl_tareas (descripcion, empleado_id) VALUES ('Tarea 5', NULL);

-- Insertar en la tabla empleados
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Perez', 'Gomez', 'Juan', 2, 2000.00, '1980-01-01', 1, 1);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Lopez', 'Martinez', 'Ana', 1, 2500.00, '1982-02-02', 2, 2);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Sanchez', 'Rodriguez', 'Carlos', 0, 3000.00, '1985-03-03', 3, 3);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Gonzalez', 'Fernandez', 'Maria', 3, 3500.00, '1987-04-04', 4, 1);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Ramirez', 'Morales', 'Jose', 2, 4000.00, '1990-05-05', 5, 2);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Torres', 'Gutierrez', 'Laura', 1, 4500.00, '1992-06-06', 6, 3);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Ruiz', 'Guerrero', 'David', 0, 5000.00, '1995-07-07', 7, 1);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Herrera', 'Medina', 'Sofia', 3, 5500.00, '1998-08-08', 8, 2);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Aguilar', 'Castillo', 'Luis', 2, 6000.00, '2000-09-09', 9, 3);
INSERT INTO tbl_empleados (ape_pat, ape_mat, nombres, nro_hijos, sueldo, fecha_nacimiento, estacionamiento_id, departamento_id) VALUES ('Ortiz', 'Ortega', 'Patricia', 1, 6500.00, '2002-10-10', 10, 1);