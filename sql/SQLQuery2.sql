create database proyecto_integrador;
use proyecto_integrador;
create table rubros(
	id int primary key identity(1,1),
	rubro varchar(50) not null,
	created_at date not null default getdate(),
	update_at date not null default getdate()
);
create table categorias(
	id int primary key identity(1,1),
	categoria varchar(50) not null,
	created_at date not null default getdate(),
	update_at date not null default getdate()
);
create table paises(
	id int primary key identity(1,1),
	pais varchar(50) not null
);
create table provincias(
	id int primary key identity(1,1),
	provincia varchar(50) not null,
	pais_id int not null,
	foreign key (pais_id) references paises (id)
);
create table localidades(
	id int primary key identity(1,1),
	localidad varchar(50) not null,
	provincia_id int not null,
	foreign key (provincia_id) references provincias (id)
);
create table iva(
	id int primary key identity(1,1),
	condicion_iva varchar(100) not null
);
create table proveedores(
	id int primary key identity(1,1),
	codigo varchar(50) not null,
	razon_social varchar(50) not null,
	rubro_id int not null,
	web varchar(50) not null,
	email varchar(50) not null,
	telefono varchar(50) not null,
	calle varchar(50) not null,
	altura varchar(50) not null,
	codigo_postal varchar(50) not null,
	localidad_id int not null,
	cuit varchar(50) not null,
	iva_id int not null,
	persona_nombre varchar(50) not null,
	persona_apellido varchar(50) not null,
	persona_telefono varchar(50) not null,
	persona_email varchar(50) not null,
	persona_rol varchar(50) not null,
	estado varchar(50) not null,
	created_at date not null default getdate(),
	updated_at date not null default getdate(),
	foreign key (iva_id) references iva (id),
	foreign key (localidad_id) references localidades (id),
	foreign key (rubro_id) references rubros (id)
);
create table productos(
	id int primary key identity(1,1),
	proveedor_id int not null,
	codigo varchar(50) not null,
	categoria_id int not null,
	nombre varchar(50) not null,
	descripcion varchar(255) not null,
	precio float not null,
	imagen_url varchar(600) null,
	estado varchar(50) not null,
	created_at date not null default getdate(),
	updated_at date not null default getdate(),
	foreign key (categoria_id) references categorias (id),
	foreign key (proveedor_id) references proveedores (id)
);
create table ordenes_compra(
	id int primary key identity(1,1),
	fecha_emision date not null default getdate(),
	fecha_entrega date not null,
	info_recepcion varchar(255) not null,
	proveedor_id int not null,
	estado varchar(50) not null,
	created_at date not null default getdate(),
	updated_at date not null default getdate(),
	foreign key (proveedor_id) references proveedores (id)
);
create table detalles(
	id int primary key identity(1,1),
	orden_id int not null,
	producto_id int not null,
	cantidad int not null,
	precio_unitario float not null,
	created_at date not null default getdate(),
	updated_at date not null default getdate(),
	foreign key (producto_id) references productos (id),
	foreign key (orden_id) references ordenes_compra (id)
);