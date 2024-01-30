package com.bootcamp.proyectointegrador.Models;

import java.sql.Timestamp;

import com.bootcamp.proyectointegrador.DTOs.ProveedorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String codigo;
	
	private String razonSocial;

	private String web;
	
	private String email;
	
	private String telefono;
	
	private String calle;
	
	private String altura;
	
	private String codigoPostal;
	
	@Column(unique = true)
	private String cuit;
	
	private String contactoNombre;
	
	private String contactoApellido;
	
	private String contactoTelefono;
	
	private String contactoEmail;
	
	private String contactoRol;

	private Boolean estado;

	private String localidad;
	
	private String urlImagen;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp updated_at;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Provincia provincia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Rubro rubro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Iva iva;

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getContactoNombre() {
		return contactoNombre;
	}

	public void setContactoNombre(String contactoNombre) {
		this.contactoNombre = contactoNombre;
	}

	public String getContactoApellido() {
		return contactoApellido;
	}

	public void setContactoApellido(String contactoApellido) {
		this.contactoApellido = contactoApellido;
	}

	public String getContactoTelefono() {
		return contactoTelefono;
	}

	public void setContactoTelefono(String contactoTelefono) {
		this.contactoTelefono = contactoTelefono;
	}

	public String getContactoEmail() {
		return contactoEmail;
	}

	public void setContactoEmail(String contactoEmail) {
		this.contactoEmail = contactoEmail;
	}

	public String getContactoRol() {
		return contactoRol;
	}

	public void setContactoRol(String contactoRol) {
		this.contactoRol = contactoRol;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public Integer getId() {
		return id;
	}

	public Proveedor(
			@NotBlank(message = "El codigo no puede estar vacío") @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números") String codigo,
			@NotBlank(message = "La razon social no puede estar vacía") @Pattern(regexp = "^.{4,50}$", message = "Razon social incorrecta, debe ingresar 4-50 caracteres alfanumericos") String razonSocial,
			@Pattern(regexp = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})", message = "web incorrecta") @NotBlank(message = "La web no puede estar vacía") String web,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto") @NotBlank(message = "El email no puede estar vacío") String email,
			@Pattern(regexp = "^(?:(?:00)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}$", message = "telefono incorrecto, debe ingresar entre 6 y 20 digitos") @NotBlank(message = "El telefono no puede estar vacío") String telefono,
			@Pattern(regexp = "^\\w{4,50}$", message = "calle incorrecta, debe ingresar 4-50 caracteres alfanumericos") @NotBlank(message = "La calle no puede estar vacía") String calle,
			@Pattern(regexp = "^[1-9]\\d{0,7}$", message = "altura incorrecta, debe ingresar 1-8 numeros") @NotBlank(message = "La altura de la calle no puede estar vacía") String altura,
			@Pattern(regexp = "^\\w{3,10}$", message = "Codigo postal incorrecto, debe ingresar 3-10 caracteres alfanumericos") @NotBlank(message = "El codigo postal no puede estar vacío") String codigoPostal,
			@Pattern(regexp = "^(20|23|24|25|26|27|30|33|34)-?\\d{8}-?\\d$", message = "Formato de CUIT invalido") @NotBlank(message = "El CUIT no puede estar vacío") String cuit,
			@Pattern(regexp = "^\\w{2,50}$", message = "Nombre invalido, debe ingresar 2-50 caracteres alfanumericos") @NotBlank(message = "El nombre del contacto no puede estar vacío") String contactoNombre,
			@Pattern(regexp = "^\\w{2,50}$", message = "Apellido invalido, debe ingresar 2-50 caracteres alfanumericos") @NotBlank(message = "El apellido del contacto no puede estar vacío") String contactoApellido,
			@Pattern(regexp = "^(?:(?:00)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}$", message = "Telefono invalido, debe ingresar entre 6-20 numeros") @NotBlank(message = "El telefono del contacto no puede estar vacío") String contactoTelefono,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto") @NotBlank(message = "El email del contacto no puede estar vacío") String contactoEmail,
			@Pattern(regexp = "^.{4,50}$", message = "Rol invalido, debe ingresar 4-50 caracteres alfanumericos") @NotBlank(message = "El rol del contacto no puede estar vacío") String contactoRol,
			@Pattern(regexp = "^.{4,50}$", message = "Localidad invalida, debe ingresar 4-50 caracteres alfanumericos") @NotBlank(message = "La localidad no puede estar vacía") String localidad,
			@NotBlank(message = "La url de la imagen no puede estar vacía") String urlImagen, @NotNull(message = "Debe seleccionar una provincia") Provincia provincia,
			@NotNull(message = "Debe seleccionar un rubro") Rubro rubro,
			@NotNull(message = "Debe seleccionar una situación ante el IVA") Iva iva) {
		super();
		this.codigo = codigo;
		this.razonSocial = razonSocial;
		this.web = web;
		this.email = email;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.codigoPostal = codigoPostal;
		this.cuit = cuit;
		this.contactoNombre = contactoNombre;
		this.contactoApellido = contactoApellido;
		this.contactoTelefono = contactoTelefono;
		this.contactoEmail = contactoEmail;
		this.contactoRol = contactoRol;
		this.estado = true;
		this.localidad = localidad;
		this.urlImagen = urlImagen;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
		this.provincia = provincia;
		this.rubro = rubro;
		this.iva = iva;
	}

	public Proveedor() {
		super();
	}

	public Proveedor(ProveedorDTO proveedorDTO, Provincia provincia, Rubro rubro, Iva iva) {
		this.codigo = proveedorDTO.getCodigo();
		this.razonSocial = proveedorDTO.getRazonSocial();
		this.web = proveedorDTO.getWeb();
		this.email = proveedorDTO.getEmail();
		this.telefono = proveedorDTO.getTelefono();
		this.calle = proveedorDTO.getCalle();
		this.altura = proveedorDTO.getAltura();
		this.codigoPostal = proveedorDTO.getCodigoPostal();
		this.cuit = proveedorDTO.getCuit();
		this.contactoNombre = proveedorDTO.getContactoNombre();
		this.contactoApellido = proveedorDTO.getContactoApellido();
		this.contactoTelefono = proveedorDTO.getContactoTelefono();
		this.contactoEmail = proveedorDTO.getContactoEmail();
		this.contactoRol = proveedorDTO.getContactoRol();
		this.estado = proveedorDTO.getEstado();
		this.localidad = proveedorDTO.getLocalidad();
		this.urlImagen = proveedorDTO.getUrlImagen();
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = new Timestamp(System.currentTimeMillis());
		this.provincia = provincia;
		this.rubro = rubro;
		this.iva = iva;
	}

	
}

