# Proyect Integrador Final

Desarrollo de un *Sistema de Gestión Compras* para manejar información de Proveedores, Productos y Órdenes de compra.




## Ejecutar localmente

Pasos necesarios para correr el proyecto localmente

- Hacer un Maven install, eso creará automaticamente la base de datos en h2
  
- Ejecutar el servidor de Java (*puerto 8080*)

- Ingresar a http://localhost:8080/h2-console

- Conectarse a la base de datos mediante el botón connect

- Ejecutar 1 por 1 los siguientes comandos:

- Insertar **Paises**

insert into paises (pais) values('Argentina'), ('Brasil'), ('Chile'), ('Uruguay'), ('Paraguay');

- Insertar **Provincias**

insert into provincias (provincia, pais_id) values
('Buenos Aires', 1),
('Ciudad Autónoma de Buenos Aires', 1),
('Catamarca', 1),
('Chaco', 1),
('Chubut', 1),
('Córdoba', 1),
('Corrientes', 1),
('Entre Ríos', 1),
('Formosa', 1),
('Jujuy', 1),
('La Pampa', 1),
('La Rioja', 1),
('Mendoza', 1),
('Misiones', 1),
('Neuquén', 1),
('Río Negro', 1),
('Salta', 1),
('San Juan', 1),
('San Luis', 1),
('Santa Cruz', 1),
('Santa Fe', 1),
('Santiago del Estero', 1),
('Tierra del Fuego', 1),
('Tucumán', 1),
('Acre',2),
('Alagoas',2),
('Amapá',2),
('Amazonas',2),
('Bahía',2),
('Ceará',2),
('Distrito Federal',2),
('Espírito Santo',2),
('Goiás',2),
('Maranhão',2),
('Mato Grosso',2),
('Mato Grosso del Sur',2),
('Minas Gerais',2),
('Pará',2),
('Paraíba',2),
('Paraná',2),
('Pernambuco',2),
('Piauí',2),
('Río de Janeiro',2),
('Río Grande del Sur',2),
('Rondonia',2),
('Roraima',2),
('Santa Catarina',2),
('São Paulo',2),
('Sergipe',2),
('Tocantins',2),
('Santiago', 3),
('La Serena', 3),
('Cuyo', 3),
('Chillán', 3),
('Concepción', 3),
('Cañete', 3),
('Angol', 3),
('Imperial', 3),
('Villarrica', 3),
('Valdivia', 3),
('Osorno', 3),
('Chiloé', 3),
('Artigas', 4),
('Canelones', 4),
('Cerro Largo', 4),
('Colonia', 4),
('Durazno', 4),
('Flores', 4),
('Florida', 4),
('Lavalleja', 4),
('Maldonado', 4),
('Montevideo', 4),
('Paysandu', 4),
('Río Negro', 4),
('Rivera', 4),
('Rocha', 4),
('Salto', 4),
('San José', 4),
('Soriano', 4),
('Tacuarembo', 4),
('Treinta y Tres', 4),
('Alto Paraguay',5),
('Alto Paraná',5),
('Amambay',5),
('Boquerón',5),
('Caaguazú',5),
('Caazapá',5),
('Canindeyú',5),
('Central',5),
('Concepción',5),
('Cordillera',5),
('Distrito Capital',5),
('Guairá',5),
('Itapúa',5),
('Misiones',5),
('Ñeembucú',5),
('Paraguari',5),
('Presidente Hayes',5),
('San Pedro',5);


- Insertar **Rubros**

insert into rubros (rubro, estado) values
('Alimentación', true),
('Electrónica', true),
('Ropa y Calzado', true),
('Mobiliario y Decoración', true),
('Construcción y Herramientas', true),
('Salud y Belleza', true),
('Automotriz', true),
('Juguetes y Entretenimiento', true),
('Libros y Material de Oficina', true),
('Deportes y Actividades al Aire Libre', true),
('Polirrubro', true);


- Insertar **Categorías**

insert into categorias (categoria, estado) values
('Perecederos' , true),
('Productos enlatados', true),
('Panadería y Pastelería', true),
('Bebidas (no alcohólicas y alcohólicas)', true),
('Dispositivos móviles (teléfonos, tablets)', true),
('Electrodomésticos', true),
('Accesorios de computadoras', true),
('televisores, altavoces', true),
('Ropa casual', true),
('Ropa deportiva', true),
('Calzado formal', true),
('Accesorios de moda', true),
('Muebles para el hogar', true),
('Decoración de interiores', true),
('Iluminación', true),
('Artículos para el hogar', true),
('Materiales de construcción', true),
('Herramientas eléctricas y manuales', true),
('Ferretería', true),
('Equipamiento para jardín', true),
('Productos de cuidado facial', true),
('Productos para el cabello', true),
('Maquillaje', true),
('Artículos de cuidado personal', true),
('Repuestos y accesorios', true),
('Productos para mantenimiento', true),
('Herramientas para vehículos', true),
('Electrónica automotriz', true),
('Juguetes educativos', true),
('Juegos de mesa', true),
('Juguetes para actividades al aire libre', true),
('Artículos de entretenimiento para niños', true),
('Libros de ficción y no ficción', true),
('Material de escritura', true),
('Papelería y organización', true),
('Equipamiento de oficina', true),
('Equipamiento deportivo', true),
('Ropa deportiva', true),
('Artículos para camping y senderismo', true),
('Artículos para deportes al aire libre', true),
('Alimentos no perecederos', true);


- Insertar **Situaciónes frente al IVA**

insert into iva (iva) values
('IVA Responsable Inscripto'),
('IVA Responsable no Inscripto'),
('IVA no Responsable'),
('IVA Sujeto Exento'),
('Consumidor Final'),
('Responsable Monotributo'),
('Sujeto no Categorizado'),
('Proveedor del Exterior'),
('Cliente del Exterior'),
('IVA Liberado – Ley Nº 19.640'),
('IVA Responsable Inscripto – Agente de Percepcion'),
('Pequeño Contribuyente Eventual'),
('Monotributista Social'),
('Pequeño Contribuyente Eventual Social');


- Insertar **Proveedor**

insert into proveedores (altura, calle, codigo, codigo_postal, contacto_apellido, contacto_email, contacto_nombre, contacto_rol, contacto_telefono, cuit, email, estado, localidad, razon_social, telefono, url_imagen, web, iva_id, provincia_id, rubro_id) values 

('153', 'Santoro', 'AB123', '1865', 'Partarrieu', 'apartarrieu@nike.com', 'Adrián', 'Gerente de ventas', '1122545079', '20-40849665-9', 'contacto@nike.com', true, 'San Vicente', 'Nike', '1122334455', 'https://www.brandemia.org/wp-content/uploads/2011/09/logo_nike_principal.jpg', 'www.nike.com', 1, 1, 3);


- Insertar **Producto**

insert into productos (codigo, descripcion, estado, imagen_url, nombre, precio, categoria_id, proveedor_id) values 
('PO123', 'Dejar en la puerta', true, 'https://www.opensports.com.ar/media/catalog/product/cache/4769e4d9f3516e60f2b4303f8e5014a8/D/M/DM4811-010_0.jpg', 'remera', 15000, 9, 1);

- Ejecutar el servidor de Angular (*puerto 4200*)

```bash
  ng start -o
```


- Insertar algunos **Proveedores** extra desde el FRONT
  
- Insertar algunos **Productos** extra desde el FRONT

- Insertar algunos **Rubros** extra desde el FRONT

- Insertar algunas **Categorías** extra desde el FRONT

- Insertar algunas **Ordenes de Compra** desde el FRONT

- Jugar

## Desarrollado por

Este proyecto fue desarrollado por: **Adrián Partarrieu**
