USE proyecto_integrador;

--1-Obtener todos los productos, mostrando nombre del producto, categoría, proveedor (razón social y codigo proveedor), precio.
SELECT prod.nombre,
       c.categoria,
       prov.razon_social AS proveedor,
       prov.codigo       AS 'codigo proveedor',
       prod.precio
FROM   productos prod,
       proveedores prov,
       categorias c
WHERE  prod.proveedor_id = prov.id
       AND prod.categoria_id = c.id

--2-En el listado anterior, además de los datos mostrados, traer el campo imagen aunque el producto NO tenga una. Sino tiene imagen, mostrar "-".
SELECT prod.nombre,
       c.categoria,
       prov.razon_social AS proveedor,
       prov.codigo       AS 'codigo proveedor',
       prod.precio,
       CASE
         WHEN prod.imagen_url IS NULL THEN '-'
         ELSE prod.imagen_url
       END               AS imagen
FROM   productos prod,
       proveedores prov,
       categorias c
WHERE  prod.proveedor_id = prov.id
       AND prod.categoria_id = c.id

--3-Mostrar los datos que se pueden modificar (en el front) del producto con ID = 2.
SELECT p.id,
       p.codigo,
       p.nombre,
       c.categoria,
       p.descripcion,
       p.precio,
       p.imagen_url,
       prov.razon_social AS proveedor,
       p.estado
FROM   productos p,
       proveedores prov,
       categorias c
WHERE  p.id = 2
       AND prov.id = p.proveedor_id
       AND c.id = p.categoria_id

--4-Listar todo los proveedores cuyo teléfono tenga la característica de Córdoba o que la provincia sea igual a alguna de las 3 con más proveedores.
SELECT DISTINCT p.codigo,
                p.razon_social,
                p.web,
                p.email,
                p.telefono
FROM   proveedores p
       INNER JOIN localidades l
               ON l.id = p.localidad_id
       INNER JOIN provincias
               ON l.provincia_id = provincias.id
WHERE  p.telefono LIKE '351%'
        OR provincias.id IN (SELECT TOP 3 WITH ties x.id AS cantidad
                             FROM   proveedores p,
                                    localidades l,
                                    provincias x
                             WHERE  p.localidad_id = l.id
                                    AND l.provincia_id = x.id
                             GROUP  BY x.id
                             ORDER  BY Count(p.id) DESC);

--5-Traer un listado de todos los proveedores que no hayan sido eliminados , y ordenados por razon social, codigo proveedor y fecha en que se dió de alta ASC. De este listado mostrar los datos que correspondan con su tabla del front.
SELECT p.codigo,
       p.razon_social,
       p.web,
       p.email,
       p.telefono,
       p.persona_nombre,
       p.persona_apellido
FROM   proveedores p
WHERE  p.estado <> 'eliminado'
ORDER  BY p.razon_social,
          p.codigo,
          p.created_at;

--6-Obtener razon social, codigo proveedor, imagen, web, email, teléfono y los datos del contacto del proveedor con más ordenes de compra cargadas.
SELECT TOP 1 p.razon_social,
             p.codigo,
             p.imagen_url,
             p.web,
             p.email,
             p.telefono,
             p.persona_nombre,
             p.persona_apellido,
             p.persona_email,
             p.persona_telefono,
             p.persona_rol
FROM   proveedores p
       INNER JOIN ordenes_compra o
               ON o.proveedor_id = p.id
GROUP  BY p.razon_social,
          p.codigo,
          p.imagen_url,
          p.web,
          p.email,
          p.telefono,
          p.persona_nombre,
          p.persona_apellido,
          p.persona_email,
          p.persona_telefono,
          p.persona_rol
ORDER  BY Count(o.id) DESC;

--7-Mostrar la fecha emisión, nº de orden, razon social y codigo de proveedor, y la cantidad de productos de cada orden.
SELECT o.fecha_emision,
       o.id            AS 'n° de orden',
       p.razon_social  AS 'Razón social',
       p.codigo        AS 'codigo proveedor',
       Sum(d.cantidad) AS 'cantidad de productos'
FROM   ordenes_compra o,
       proveedores p,
       productos prod,
       detalles d
WHERE  p.id = o.proveedor_id
       AND d.orden_id = o.id
       AND prod.id = d.producto_id
GROUP  BY o.fecha_emision,
          o.id,
          p.razon_social,
          p.codigo;

--8-En el listado anterior, diferenciar cuando una orden está Cancelada o no, y el total de la misma.
SELECT o.fecha_emision,
       o.id                                AS 'n° de orden',
       p.razon_social                      AS 'Razón social',
       p.codigo                            AS 'codigo proveedor',
       Sum(d.cantidad)                     AS 'cantidad de productos',
       o.estado,
       Sum(d.cantidad * d.precio_unitario) AS total
FROM   ordenes_compra o,
       proveedores p,
       productos prod,
       detalles d
WHERE  p.id = o.proveedor_id
       AND d.orden_id = o.id
       AND prod.id = d.producto_id
GROUP  BY o.fecha_emision,
          o.id,
          p.razon_social,
          p.codigo,
          o.estado;

--9-Mostrar el detalle de una orden de compra del proveedor 3, trayendo: SKU del producto, nombre producto, cantidad y subtotal.
SELECT d.id,
       d.orden_id                     AS 'n° orden',
       p.codigo                       AS SKU,
       p.nombre,
       d.cantidad,
       d.cantidad * d.precio_unitario AS subtotal
FROM   ordenes_compra o,
       detalles d,
       productos p
WHERE  d.orden_id = o.id
       AND d.producto_id = p.id
       AND o.proveedor_id = 3;

--10-Cambiar el estado a Cancelada y la fecha de modificación a la orden de compra con ID = 4.
UPDATE ordenes_compra
SET    estado = 'cancelada',
       updated_at = Getdate()
WHERE  id = 4;

--11-Escribir la sentencia para eliminar el producto con id = 1 (NO EJECUTAR, SÓLO MOSTRAR SENTENCIA)
DELETE FROM detalles
WHERE  producto_id = 1

DELETE FROM productos
WHERE  id = 1 