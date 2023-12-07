const productos = (localStorage.getItem("productos") === null) ? []: JSON.parse(localStorage.getItem("productos"));
const tabla = document.getElementById("contenido-tabla");

for(let i=0; i<productos.length;i++){
    filas.innerHTML += `
    <tr>
        <td>${productos[i].proveedor}</td>
        <td>${productos[i].codigo}</td>
        <td>${productos[i].categoria}</td>
        <td>${productos[i].nombreProducto}</td>
        <td>${productos[i].descripcion}</td>
        <td>${productos[i].precio}</td>
        <td><button id="button${i}">Eliminar</button></td>
    </tr>
    `;
}
