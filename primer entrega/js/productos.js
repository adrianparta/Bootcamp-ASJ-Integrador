let productos = (localStorage.getItem("productos") === null) ? []: JSON.parse(localStorage.getItem("productos"));
const tabla = document.getElementById("contenido-tabla");
for(let i = 0; i<productos.length; i++){
    let tr = document.createElement("tr");
    
    let tdProveedor = document.createElement("td");
    let tdProveedor_texto = document.createTextNode(productos[i].proveedor);
    tdProveedor.appendChild(tdProveedor_texto);
    tr.appendChild(tdProveedor);
    console.log(tr);
    let tdCodigo = document.createElement("td");
    let tdCodigo_texto = document.createTextNode(productos[i].codigo);
    tdCodigo.appendChild(tdCodigo_texto);
    tr.appendChild(tdCodigo);
    
    let tdCategoria = document.createElement("td");
    let tdCategoria_texto = document.createTextNode(productos[i].categoria);
    tdCategoria.appendChild(tdCategoria_texto);
    tr.appendChild(tdCategoria);

    let tdNombreProducto = document.createElement("td");
    let tdNombreProducto_texto = document.createTextNode(productos[i].nombreProducto);
    tdNombreProducto.appendChild(tdNombreProducto_texto);
    tr.appendChild(tdNombreProducto);

    let tdDescripcion = document.createElement("td");
    let tdDescripcion_texto = document.createTextNode(productos[i].descripcion);
    tdDescripcion.appendChild(tdDescripcion_texto);
    tr.appendChild(tdDescripcion);

    let tdPrecio = document.createElement("td");
    let tdPrecio_texto = document.createTextNode(productos[i].precio);
    tdPrecio.appendChild(tdPrecio_texto);
    tr.appendChild(tdPrecio);

    let btnEliminar = document.createElement("button");
    let btnEliminar_texto = document.createTextNode("Eliminar");
    btnEliminar.appendChild(btnEliminar_texto);
    btnEliminar.setAttribute("class","btnEliminar btn btn-outline-danger");
    btnEliminar.addEventListener("click",()=>{
        
        if(confirm("seguro que desea eliminar?")){   
            productos.splice(i,1);
            localStorage.setItem("productos", JSON.stringify(productos));
            alert("Producto/servicio eliminado");
            window.location.href ="productos.html";
        }
    })
    tr.appendChild(btnEliminar);
    tabla.appendChild(tr);
}
