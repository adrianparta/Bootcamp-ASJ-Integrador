let proveedores = (localStorage.getItem("proveedores") === null) ? []: JSON.parse(localStorage.getItem("proveedores"));
const tabla = document.getElementById("contenido-tabla");
for(let i = 0; i<proveedores.length; i++){
    let tr = document.createElement("tr");
    let tdCodigo = document.createElement("td");
    let tdCodigo_texto = document.createTextNode(proveedores[i].codigo);
    tdCodigo.appendChild(tdCodigo_texto);
    tr.appendChild(tdCodigo);

    let tdNombre = document.createElement("td");
    let tdNombre_texto = document.createTextNode(proveedores[i].nombre);
    tdNombre.appendChild(tdNombre_texto);
    tr.appendChild(tdNombre);

    let tdApellido = document.createElement("td");
    let tdApellido_texto = document.createTextNode(proveedores[i].apellido);
    tdApellido.appendChild(tdApellido_texto);
    tr.appendChild(tdApellido);

    let tdRubro = document.createElement("td");
    let tdRubro_texto = document.createTextNode(proveedores[i].rubro);
    tdRubro.appendChild(tdRubro_texto);
    tr.appendChild(tdRubro);

    let tdEmail = document.createElement("td");
    let tdEmail_texto = document.createTextNode(proveedores[i].email);
    tdEmail.appendChild(tdEmail_texto);
    tr.appendChild(tdEmail);

    let btnEliminar = document.createElement("button");
    let btnEliminar_texto = document.createTextNode("Eliminar");
    btnEliminar.appendChild(btnEliminar_texto);
    btnEliminar.setAttribute("class","btnEliminar btn btn-outline-danger");
    btnEliminar.addEventListener("click",()=>{
        
        if(confirm("seguro que desea eliminar?")){   
            proveedores.splice(i,1);
            localStorage.setItem("proveedores", JSON.stringify(proveedores));
            alert("proveedor eliminado");
            window.location.href ="proveedores.html";
        }
    })
    tr.appendChild(btnEliminar);
    tabla.appendChild(tr);
}
