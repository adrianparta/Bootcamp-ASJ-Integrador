
    
    const proveedores = (localStorage.getItem("proveedores") === null) ? []: JSON.parse(localStorage.getItem("proveedores"));
    console.log(proveedores);
    const tabla = document.getElementById("contenido-tabla");
    console.log(tabla.innerHTML)
for(let i=0; i<proveedores.length;i++){
    tabla.innerHTML += `
    <tr>
    <td>${proveedores[i].codigo}</td>
    <td>${proveedores[i].nombre}</td>
    <td>${proveedores[i].apellido}</td>
    <td>${proveedores[i].rubro}</td>
    <td>${proveedores[i].email}</td>
        <td><button id="button${i}">Eliminar</button></td>
        </tr>
        `;
    }