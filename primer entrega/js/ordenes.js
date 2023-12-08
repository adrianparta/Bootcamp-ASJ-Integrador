let ordenes = (localStorage.getItem("ordenes") === null) ? []: JSON.parse(localStorage.getItem("ordenes"));
const tabla = document.getElementById("contenido-tabla");
for(let i = 0; i<ordenes.length; i++){
    let tr = document.createElement("tr");
    
    let tdNumeroOrden = document.createElement("td");
    let tdNumeroOrden_texto = document.createTextNode(ordenes[i].numeroOrden);
    tdNumeroOrden.appendChild(tdNumeroOrden_texto);
    tr.appendChild(tdNumeroOrden);
    
    let tdFechaEmision = document.createElement("td");
    let tdFechaEmision_texto = document.createTextNode(ordenes[i].fechaEmision);
    tdFechaEmision.appendChild(tdFechaEmision_texto);
    tr.appendChild(tdFechaEmision);
    
    let tdFechaEntrega = document.createElement("td");
    let tdFechaEntrega_texto = document.createTextNode(ordenes[i].fechaEntrega);
    tdFechaEntrega.appendChild(tdFechaEntrega_texto);
    tr.appendChild(tdFechaEntrega);

    let tdProveedor = document.createElement("td");
    let tdProveedor_texto = document.createTextNode(ordenes[i].proveedor);
    tdProveedor.appendChild(tdProveedor_texto);
    tr.appendChild(tdProveedor);

    let tdTotal = document.createElement("td");
    let tdTotal_texto = document.createTextNode(ordenes[i].total);
    tdTotal.appendChild(tdTotal_texto);
    tr.appendChild(tdTotal);


    let btnEliminar = document.createElement("button");
    let btnEliminar_texto = document.createTextNode("Eliminar");
    btnEliminar.appendChild(btnEliminar_texto);
    btnEliminar.setAttribute("class","btnEliminar btn btn-outline-danger");
    btnEliminar.addEventListener("click",()=>{
        
        if(confirm("seguro que desea eliminar?")){   
            ordenes.splice(i,1);
            localStorage.setItem("ordenes", JSON.stringify(ordenes));
            alert("Número de orden eliminado");
            window.location.href ="ordenes.html";
        }
    })
    tr.appendChild(btnEliminar);
    tabla.appendChild(tr);
}
