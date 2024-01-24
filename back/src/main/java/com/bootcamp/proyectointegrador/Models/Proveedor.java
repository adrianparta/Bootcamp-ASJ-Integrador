package com.bootcamp.proyectointegrador.Models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "El codigo no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números")
	private String codigo;
	
	@NotBlank(message = "La razon social no puede estar vacía")
	private String razonSocial;

	@Pattern(regexp = "(https?://(?:www\\\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\\\.[^\\\\s]{2,}|www\\\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\\\.[^\\\\s]{2,}|https?://(?:www\\\\.|(?!www))[a-zA-Z0-9]+\\\\.[^\\\\s]{2,}|www\\\\.[a-zA-Z0-9]+\\\\.[^\\\\s]{2,})\"", message= "url invalida")
	@NotBlank(message = "La web no puede estar vacía")	
	private String web;
	
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto")
	@NotBlank(message = "El email no puede estar vacío")
	private String email;
	
	@NotBlank(message = "El telefono no puede estar vacío")
	private String telefono;
	
	@NotBlank(message = "La calle no puede estar vacía")
	private String calle;
	
	@NotBlank(message = "La altura de la calle no puede estar vacía")
	private String altura;
	
	@NotBlank(message = "El codigo postal no puede estar vacío")
	private String codigoPostal;
	
	@Column(unique = true)
	@NotBlank(message = "El CUIT no puede estar vacío")
	private String cuit;
	
	@NotBlank(message = "El nombre del contacto no puede estar vacío")
	private String contactoNombre;
	
	@NotBlank(message = "El apellido del contacto no puede estar vacío")
	private String contactoApellido;
	
	@NotBlank(message = "El telefono del contacto no puede estar vacío")
	private String contactoTelefono;
	
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto")
	@NotBlank(message = "El email del contacto no puede estar vacío")
	private String contactoEmail;
	
	@NotBlank(message = "El rol del contacto no puede estar vacío")
	private String contactoRol;

	@NotBlank(message = "El estado no puede estar vacío")
	private Boolean estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank(message = "created_at vacío")
	private Timestamp created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank(message = "updated_at vacío")
	private Timestamp updated_at;
	
	@NotBlank(message = "Debe seleccionar una provincia")
	private Provincia provinciaId;
	
	@NotBlank(message = "La localidad no puede estar vacía")
	private String localidad;
	
	@NotBlank(message = "Debe seleccionar un rubro")
	private Rubro rubroId;
	
	@NotBlank(message = "Debe seleccionar una situación ante el IVA")
	private Iva ivaId;

	public Proveedor(Integer id,
			@NotBlank(message = "El codigo no puede estar vacío") @Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras seguidas de 3 números") String codigo,
			@NotBlank(message = "La razon social no puede estar vacía") String razonSocial,
			@Pattern(regexp = "(https?://(?:www\\\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\\\.[^\\\\s]{2,}|www\\\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\\\.[^\\\\s]{2,}|https?://(?:www\\\\.|(?!www))[a-zA-Z0-9]+\\\\.[^\\\\s]{2,}|www\\\\.[a-zA-Z0-9]+\\\\.[^\\\\s]{2,})\"", message = "url invalida") @NotBlank(message = "La web no puede estar vacía") String web,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto") @NotBlank(message = "El email no puede estar vacío") String email,
			@NotBlank(message = "El telefono no puede estar vacío") String telefono,
			@NotBlank(message = "La calle no puede estar vacía") String calle,
			@NotBlank(message = "La altura de la calle no puede estar vacía") String altura,
			@NotBlank(message = "El codigo postal no puede estar vacío") String codigoPostal,
			@NotBlank(message = "El CUIT no puede estar vacío") String cuit,
			@NotBlank(message = "El nombre del contacto no puede estar vacío") String contactoNombre,
			@NotBlank(message = "El apellido del contacto no puede estar vacío") String contactoApellido,
			@NotBlank(message = "El telefono del contacto no puede estar vacío") String contactoTelefono,
			@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto") @NotBlank(message = "El email del contacto no puede estar vacío") String contactoEmail,
			@NotBlank(message = "El rol del contacto no puede estar vacío") String contactoRol,
			@NotBlank(message = "El estado no puede estar vacío") Boolean estado,
			@NotBlank(message = "created_at vacío") Timestamp created_at,
			@NotBlank(message = "updated_at vacío") Timestamp updated_at,
			@NotBlank(message = "Debe seleccionar una provincia") Provincia provinciaId,
			@NotBlank(message = "La localidad no puede estar vacía") String localidad,
			@NotBlank(message = "Debe seleccionar un rubro") Rubro rubroId,
			@NotBlank(message = "Debe seleccionar una situación ante el IVA") Iva ivaId) {
		super();
		this.id = id;
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
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.provinciaId = provinciaId;
		this.localidad = localidad;
		this.rubroId = rubroId;
		this.ivaId = ivaId;
	}

	public Proveedor() {
		super();
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

	public Provincia getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Provincia provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Rubro getRubroId() {
		return rubroId;
	}

	public void setRubroId(Rubro rubroId) {
		this.rubroId = rubroId;
	}

	public Iva getIvaId() {
		return ivaId;
	}

	public void setIvaId(Iva ivaId) {
		this.ivaId = ivaId;
	}

	public Integer getId() {
		return id;
	}
	
	

	
}

