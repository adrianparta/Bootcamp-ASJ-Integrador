package com.bootcamp.proyectointegrador.DTOs;

import com.bootcamp.proyectointegrador.Models.Producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class ProductoDTO {
	
	private Integer id;
	
	@NotBlank(message = "El codigo no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números")
	private String codigo;
	
	@NotBlank(message = "El codigo no puede estar vacío")
	private String nombre;
	
	@NotBlank(message = "La descripción no puede estar vacía")
	private String descripcion;
	
	@NotNull(message = "El precio no puede estar vacío")
	@Positive(message = "El precio debe ser mayor a 0")
	private Double precio;
	
	@NotBlank(message = "La url de la imagen no puede estar vacía")
	private String imagen_url;
	
	private Boolean estado;
	
	@NotNull(message = "Debe seleccionar una categoría")
	private Integer categoriaId;
	
	private String categoria;
	
	@NotNull(message = "Debe seleccionar un proveedor")
	private Integer proveedorId;
	
	private String proveedor;
	
	private Boolean proveedorEstado;

	public ProductoDTO(Integer id,
			@NotBlank(message = "El codigo no puede estar vacío") @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números") String codigo,
			@NotBlank(message = "El codigo no puede estar vacío") String nombre,
			@NotBlank(message = "La descripción no puede estar vacía") String descripcion,
			@NotNull(message = "El precio no puede estar vacío") @Positive(message = "El precio debe ser mayor a 0") Double precio,
			@NotBlank(message = "La url de la imagen no puede estar vacía") String imagen_url, Boolean estado,
			@NotNull(message = "Debe seleccionar una categoría") Integer categoriaId, String categoria,
			@NotNull(message = "Debe seleccionar un proveedor") Integer proveedorId, String proveedor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen_url = imagen_url;
		this.estado = estado;
		this.categoriaId = categoriaId;
		this.categoria = categoria;
		this.proveedorId = proveedorId;
		this.proveedor = proveedor;
	}

	public ProductoDTO() {
		super();
	}
	
	public ProductoDTO(Producto producto) {
		this.id = producto.getId();
		this.codigo = producto.getCodigo();
		this.nombre = producto.getNombre();
		this.descripcion = producto.getDescripcion();
		this.precio = producto.getPrecio();
		this.imagen_url = producto.getImagen_url();
		this.estado = producto.getEstado();
		this.categoriaId = producto.getCategoria().getId();
		this.categoria = producto.getCategoria().getCategoria();
		this.proveedorId = producto.getProveedor().getId();
		this.proveedor = producto.getProveedor().getRazonSocial();
		this.proveedorEstado = producto.getProveedor().getEstado();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public Boolean getProveedorEstado() {
		return proveedorEstado;
	}

	public void setProveedorEstado(Boolean proveedorEstado) {
		this.proveedorEstado = proveedorEstado;
	}
	
	
}
