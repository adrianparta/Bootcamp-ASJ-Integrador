CREATE DATABASE proyecto_integrador;

go

USE proyecto_integrador;

go

CREATE TABLE rubros
  (
     id    INT PRIMARY KEY IDENTITY(1, 1),
     rubro VARCHAR(50) NOT NULL
  );

go

CREATE TABLE categorias
  (
     id        INT PRIMARY KEY IDENTITY(1, 1),
     categoria VARCHAR(50) NOT NULL
  );

go

CREATE TABLE paises
  (
     id   INT PRIMARY KEY IDENTITY(1, 1),
     pais VARCHAR(50) NOT NULL
  );

go

CREATE TABLE provincias
  (
     id        INT PRIMARY KEY IDENTITY(1, 1),
     provincia VARCHAR(50) NOT NULL,
     pais_id   INT NOT NULL,
     FOREIGN KEY (pais_id) REFERENCES paises (id)
  );

go

CREATE TABLE localidades
  (
     id           INT PRIMARY KEY IDENTITY(1, 1),
     localidad    VARCHAR(50) NOT NULL,
     provincia_id INT NOT NULL,
     FOREIGN KEY (provincia_id) REFERENCES provincias (id)
  );

go

CREATE TABLE iva
  (
     id            INT PRIMARY KEY IDENTITY(1, 1),
     condicion_iva VARCHAR(100) NOT NULL
  );

go

CREATE TABLE proveedores
  (
     id               INT PRIMARY KEY IDENTITY(1, 1),
     codigo           VARCHAR(50) NOT NULL,
     razon_social     VARCHAR(50) NOT NULL,
     web              VARCHAR(50) NOT NULL,
     email            VARCHAR(50) NOT NULL,
     telefono         VARCHAR(50) NOT NULL,
     calle            VARCHAR(50) NOT NULL,
     altura           VARCHAR(50) NOT NULL,
     codigo_postal    VARCHAR(50) NOT NULL,
     cuit             VARCHAR(50) NOT NULL,
     persona_nombre   VARCHAR(50) NOT NULL,
     persona_apellido VARCHAR(50) NOT NULL,
     persona_telefono VARCHAR(50) NOT NULL,
     persona_email    VARCHAR(50) NOT NULL,
     persona_rol      VARCHAR(50) NOT NULL,
     estado           VARCHAR(50) NOT NULL,
     created_at       DATE NOT NULL DEFAULT Getdate(),
     updated_at       DATE NOT NULL DEFAULT Getdate(),
     localidad_id     INT NOT NULL,
     rubro_id         INT NOT NULL,
     iva_id           INT NOT NULL,
     FOREIGN KEY (localidad_id) REFERENCES localidades (id),
     FOREIGN KEY (rubro_id) REFERENCES rubros (id),
     FOREIGN KEY (iva_id) REFERENCES iva (id)
  );

go

CREATE TABLE productos
  (
     id           INT PRIMARY KEY IDENTITY(1, 1),
     codigo       VARCHAR(50) NOT NULL,
     nombre       VARCHAR(50) NOT NULL,
     descripcion  VARCHAR(255) NOT NULL,
     precio       FLOAT NOT NULL,
     imagen_url   VARCHAR(600) NULL,
     estado       VARCHAR(50) NOT NULL,
     created_at   DATE NOT NULL DEFAULT Getdate(),
     updated_at   DATE NOT NULL DEFAULT Getdate(),
     categoria_id INT NOT NULL,
     proveedor_id INT NOT NULL,
     FOREIGN KEY (categoria_id) REFERENCES categorias (id),
     FOREIGN KEY (proveedor_id) REFERENCES proveedores (id)
  );

go

CREATE TABLE ordenes_compra
  (
     id             INT PRIMARY KEY IDENTITY(1, 1),
     fecha_emision  DATE NOT NULL DEFAULT Getdate(),
     fecha_entrega  DATE NOT NULL,
     info_recepcion VARCHAR(255) NOT NULL,
     estado         VARCHAR(50) NOT NULL,
     created_at     DATE NOT NULL DEFAULT Getdate(),
     updated_at     DATE NOT NULL DEFAULT Getdate(),
     proveedor_id   INT NOT NULL,
     FOREIGN KEY (proveedor_id) REFERENCES proveedores (id)
  );

go

CREATE TABLE detalles
  (
     id              INT PRIMARY KEY IDENTITY(1, 1),
     cantidad        INT NOT NULL,
     precio_unitario FLOAT NOT NULL,
     orden_id        INT NOT NULL,
     producto_id     INT NOT NULL,
     FOREIGN KEY (orden_id) REFERENCES ordenes_compra (id),
     FOREIGN KEY (producto_id) REFERENCES productos (id),
  ); 


  --inserts

insert into paises (pais) values('Argentina'), ('Brasil'), ('Chile'), ('Uruguay'), ('Paraguay');
go
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
('San Pedro',5)
go
insert into rubros (rubro) values
('Alimentación'),
('Electrónica'),
('Ropa y Calzado'),
('Mobiliario y Decoración'),
('Construcción y Herramientas'),
('Salud y Belleza'),
('Automotriz'),
('Juguetes y Entretenimiento'),
('Libros y Material de Oficina'),
('Deportes y Actividades al Aire Libre'),
('Polirrubro');
go
insert into categorias (categoria) values
('Perecederos'),
('Productos enlatados'),
('Panadería y Pastelería'),
('Bebidas (no alcohólicas y alcohólicas)'),
('Dispositivos móviles (teléfonos, tablets)'),
('Electrodomésticos'),
('Accesorios de computadoras'),
('televisores, altavoces'),
('Ropa casual'),
('Ropa deportiva'),
('Calzado formal'),
('Accesorios de moda'),
('Muebles para el hogar'),
('Decoración de interiores'),
('Iluminación'),
('Artículos para el hogar'),
('Materiales de construcción'),
('Herramientas eléctricas y manuales'),
('Ferretería'),
('Equipamiento para jardín'),
('Productos de cuidado facial'),
('Productos para el cabello'),
('Maquillaje'),
('Artículos de cuidado personal'),
('Repuestos y accesorios'),
('Productos para mantenimiento'),
('Herramientas para vehículos'),
('Electrónica automotriz'),
('Juguetes educativos'),
('Juegos de mesa'),
('Juguetes para actividades al aire libre'),
('Artículos de entretenimiento para niños'),
('Libros de ficción y no ficción'),
('Material de escritura'),
('Papelería y organización'),
('Equipamiento de oficina'),
('Equipamiento deportivo'),
('Ropa deportiva'),
('Artículos para camping y senderismo'),
('Artículos para deportes al aire libre'),
('Alimentos no perecederos');
go
insert into localidades (localidad, provincia_id) values
('San Vicente',1),
('Quilmes',1),
('Tandil',1),
('La Plata',1),
('Belgrano',2),
('Constitución',2),
('Palermo',2),
('Villa Urquiza',2),
('San Fernando del Valle de Catamarca',3),
('Resistencia',4),
('Carlos Paz',6),
('Corrientes',7),
('Paraná',8),
('Formosa',9),
('San Salvador de Jujuy',10),
('Santa Rosa',11),
('La Rioja',12),
('Mendoza',13),
('Posadas',14),
('Neuquén',15),
('Viedma',16),
('Salta',17),
('San Juan',18),
('San Luis',19),
('Rio Gallegos',20),
('Santa Fe',21),
('Santiago del Estero',22),
('Ushuaia',23),
('Tucumán',24),
('São Paulo',48),
('Río de Janeiro',43),
('Salvador',29),
('Brasilia',31),
('Recife',41),
('Belo Horizonte',37),
('Santiago',51),
('Montevideo',72),
('Salto',77),
('Asunción',92),
('Ciudad del Este',83);
go
insert into iva (condicion_iva) values
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
go

alter table proveedores add imagen_url varchar(600) null;

go
insert into proveedores (codigo, razon_social, rubro_id, web, email, telefono, calle, altura,
codigo_postal, localidad_id, cuit, iva_id, persona_nombre, persona_apellido, persona_telefono, persona_email, persona_rol,
estado, imagen_url) values

('ABC123', 'Arcor',1,'www.arcor.com', 'arcor@contacto.com.ar', '35144778556','Catamarca', '23', '1847',
2,'30-50279317-5',1,'Alvaro','Ramirez','0222485547441','aramirez@arcor.com.ar','Gerente de ventas','activo', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Grupo_arcor_logo.svg/1200px-Grupo_arcor_logo.svg.png'),

('DEF789', 'Sodimac',1,'www.sodimac.com', 'sodimac@contacto.com.ar', '1144566842','Bolivar', '744', '1554',
33,'30-65572582-9',8,'Ricardo','Gonzalez','1177454499','rgonzalez@sodimac.com.ar','Gerente de Recursos Humanos','activo', 'https://grandesmarcas.cl/wp-content/uploads/2020/09/Logo-Sodimac.jpg'),

('HTR411', 'Samsung',2,'www.samsung.com', 'samsung@contacto.com.ar', '1177412566','Rivadavia', '7411', '1864',
6,'30-40849665-8',5,'Adrián','Perez','114450011','aperez@samsung.com.ar','CEO','activo', 'https://1000marcas.net/wp-content/uploads/2019/12/logo-Samsung.png'),

('QWE888', 'Nokia',2,'www.nokia.com', 'nokia@contacto.com.ar', '1155748821','Santoro', '999', '1865',
1,'23-40849665-9',12,'Juan Carlos','Espinoza','1122545079','jespinoza@nokia.com.ar','Gerente de ventas','activo', 'https://1000marcas.net/wp-content/uploads/2020/01/Nokia-Logo.jpg'),

('ABC456', 'Nike',1,'www.nike.com', 'nike@contacto.com.ar', '35144999556','Buenos Aires', '1554', '1860',
39,'30-50279973-5',1,'Matías','Suarez','0222485547442','msuarez@nokia.com.ar','Gerente de marketing','activo', 'https://www.brandemia.org/wp-content/uploads/2011/09/logo_nike_principal.jpg'),

('DEF321', 'Ford',1,'www.ford.com', 'ford@contacto.com.ar', '11996699','Saavedra', '456', '1863',
19,'30-47415555-9',11,'Cristian','Pasteur','1177456687','cpasteur@ford.com.ar','Gerente de ventas','eliminado', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Ford_logo_flat.svg/2560px-Ford_logo_flat.svg.png'),

('RRR422', 'adidas',1,'www.adidas.com', 'adidas@contacto.com.ar', '35144888556','Brasil', '1577', '1863',
19,'30-12279973-5',1,'Hernan','Pereyra','11447774441','hpereyra@adidas.com.ar','Gerente de marketing','activo', null),

('hhh422', 'fila',1,'www.fila.com', 'fila@contacto.com.ar', '1144335588','Uruguay', '45', '1850',
6,'30-40849663-5',1,'Ana','Juarez','1199774455','ajuarez@fila.com.ar','Gerente de marketing','activo', null),


('jjj422', 'puma',1,'www.puma.com', 'puma@contacto.com.ar', '1144221144','Paraguay', '202', '1861',
19,'30-44279973-5',1,'Javier','Ibarra','1144996655','jibarra@puma.com.ar','Gerente de marketing','activo', null);

go
insert into productos (proveedor_id, codigo, categoria_id, nombre, descripcion, precio, imagen_url, estado) values
(1, 'PO111', 2, 'choclo en lata', 'choclo amarillo en granos, peso total 350gr, peso escurrido 270 gr', 998.99, 'https://www.arcor.com/ar/uploads/product_images/1009800.jpg', 'disponible'),
(3, 'PO123', 5, 'S23 Ultra', 'el teléfono tope de gama de Samsung para el 2023, la mejor cámara del mercado', 1699999, 'https://images.start.com.ar/SM-S918BZKVARO-2.jpg', 'disponible'),
(3, 'PO124', 5, 'S23 FE', 'para los que tienen un presupuesto mas comedido, el s23 fan edition es la mejor opcion, no tiene nada que envidiarle a los grandes', 899999, 'https://riiing.com.ar/wp-content/uploads/2023/10/Samsung-S23-FE-1024x1024.png', 'disponible'),
(2, 'PO874', 15, 'Lampara LED', 'un foco de 12W, ilumina hasta 20 metros a la redonda', 1999, 'https://electroruta27.com.ar/wp-content/uploads/2020/06/12W-Calida.png', 'disponible'),
(2, 'PO880', 13, 'futón 3 cuerpos', 'lo mas comodo del mercado, colores a elegir: azul, marron y blanco', 172909, 'https://lafabricamuebles.com.ar/wp-content/uploads/2020/10/11614.jpg', 'disponible'),
(5, 'PO1080', 38, 'Nike Sportswear club', 'camiseta basica con el logo de nike, azul o rojo', 29299, 'https://nikearprod.vtexassets.com/arquivos/ids/439001-1000-1000?v=638145696114630000&width=1000&height=1000&aspect=true', 'disponible'),
(5, 'PO1080', 38, 'Nike SB', 'remera unisex', 47999, 'https://nikearprod.vtexassets.com/arquivos/ids/722752-1000-1000?v=638301453208130000&width=1000&height=1000&aspect=true', 'disponible'),
(1, 'PO127', 41, 'aceite', 'aceite 1.5 litros', 2276, 'https://acdn.mitiendanube.com/stores/093/780/products/arcor1-579fa07de3b7cf5bc515713294691558-640-0.png', 'disponible'),
(1, 'PO135', 41, 'polenta', 'polenta, harina de maiz', 749, 'https://arcorencasa.com/wp-content/uploads/2023/07/20230707-6600.jpg', 'disponible'),
(1, 'PO135', 41, 'fideos tirabuzon', 'fideos tirabuzon, los mas ricos', 549, null, 'disponible');

go
insert into ordenes_compra (fecha_emision, fecha_entrega, info_recepcion, proveedor_id, estado, created_at, updated_at) values

('2022-04-30', '2022-05-05', 'porton negro, dejarlo en la puerta', 1, 'finalizado', '2022-04-30', '2022-05-05'),
('2022-08-12', '2022-08-14', 'tocar timbre', 3, 'finalizado', '2022-08-12', '2022-08-15'),
('2023-02-24', '2023-02-27', 'dejar en recepcion', 5, 'cancelada', '2023-02-24', '2023-02-27'),
('2023-12-01', '2023-12-10', 'tratar con cuidado, tiene cosas de vidrio', 2, 'finalizado', '2023-12-01', '2023-12-09'),
(getdate(), DATEADD(DAY, 3, GETDATE()), 'dejar en porton', 1, 'pendiente', getdate(), getdate());

go
insert into detalles(orden_id, producto_id, cantidad, precio_unitario) values
(1, 8, 10, 2276),
(1, 9, 8, 749),
(2, 2, 2, 1699999),
(2, 3, 3, 899999),
(3, 6, 5, 29299),
(4, 4, 100, 1999),
(4, 5, 1, 172909),
(5, 8, 15, 2276),
(5, 9, 20, 749),
(5, 10, 20, 549),
(5, 1, 30, 998.99);
