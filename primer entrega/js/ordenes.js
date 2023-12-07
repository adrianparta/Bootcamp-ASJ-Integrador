const ordenes = (localStorage.getItem("ordenes") === null) ? []: JSON.parse(localStorage.getItem("ordenes"));
const tabla = document.getElementById("contenido-tabla");

for(let i=0; i<ordenes.length;i++){
    filas.innerHTML += `
    <tr>
        <td>${ordenes[i].nro}</td>
        <td>${ordenes[i].fechaEmision}</td>
        <td>${ordenes[i].fechaEntrega}</td>
        <td>${ordenes[i].proveedor}</td>
        <td>${ordenes[i].total}</td>
        <td><button id="button${i}">Eliminar</button></td>
    </tr>
    `;
}
