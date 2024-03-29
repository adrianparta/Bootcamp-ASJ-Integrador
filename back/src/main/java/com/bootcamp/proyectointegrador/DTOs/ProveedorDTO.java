package com.bootcamp.proyectointegrador.DTOs;

import com.bootcamp.proyectointegrador.Models.Proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProveedorDTO {

	private Integer id;
	

	@NotBlank(message = "El codigo no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{3}$", message = "El formato del código debe ser: 2 letras mayusculas seguidas de 3 números")
	private String codigo;
	
	@NotBlank(message = "La razon social no puede estar vacía")
	@Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
	private String razonSocial;

	@Pattern(regexp = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})" , message = "web incorrecta")
	@NotBlank(message = "La web no puede estar vacía")	
	private String web;
	
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto")
	@NotBlank(message = "El email no puede estar vacío")
	private String email;
	
	@Pattern(regexp = "^[0-9]{6,20}$", message = "El campo debe contener solo números y tener entre 6 y 20 dígitos")
    @NotBlank(message = "El telefono no puede estar vacío")
	private String telefono;
	
	@Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
	@NotBlank(message = "La calle no puede estar vacía")
	private String calle;
	
	@Pattern(regexp = "^[1-9]\\d{0,7}$", message = "altura incorrecta, debe ingresar 1-8 numeros")
	@NotBlank(message = "La altura de la calle no puede estar vacía")
	private String altura;
	
	@Size(min = 3, max = 10, message = "El nombre debe tener entre 3 y 10 caracteres")
	@NotBlank(message = "El codigo postal no puede estar vacío")
	private String codigoPostal;
	
	@Pattern(regexp = "^(20|23|24|25|26|27|30|33|34)-?\\d{8}-?\\d$", message = "Formato de CUIT invalido")
	@NotBlank(message = "El CUIT no puede estar vacío")
	private String cuit;
	
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	@NotBlank(message = "El nombre del contacto no puede estar vacío")
	private String contactoNombre;
	
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")@NotBlank(message = "El apellido del contacto no puede estar vacío")
	private String contactoApellido;
	
	@Pattern(regexp = "^[0-9]{6,20}$", message = "El campo debe contener solo números y tener entre 6 y 20 dígitos")
    @NotBlank(message = "El telefono del contacto no puede estar vacío")
	private String contactoTelefono;
	
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "email incorrecto")
	@NotBlank(message = "El email del contacto no puede estar vacío")
	private String contactoEmail;
	
	@Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
	@NotBlank(message = "El rol del contacto no puede estar vacío")
	private String contactoRol;

	private Boolean estado;

	@Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
	@NotBlank(message = "La localidad no puede estar vacía")
	private String localidad;
	
	@NotBlank(message = "La url de la imagen no puede estar vacía")
	private String urlImagen;
	
	@NotNull(message = "Debe seleccionar una provincia")
	private Integer provinciaId;
	
	private String provincia;
	
	private String pais;
	
	@NotNull(message = "Debe seleccionar un rubro")
	private Integer rubroId;
	
	@NotNull(message = "Debe seleccionar una situación ante el IVA")
	private Integer ivaId;


	public ProveedorDTO() {
		super();
	}

	public ProveedorDTO(
			Integer id,
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
			Boolean estado,
			@Pattern(regexp = "^.{4,50}$", message = "Localidad invalida, debe ingresar 4-50 caracteres alfanumericos") @NotBlank(message = "La localidad no puede estar vacía") String localidad,
			@NotBlank(message = "La url de la imagen no puede estar vacía") String urlImagen,
			@NotNull(message = "Debe seleccionar una provincia") Integer provinciaId,
			@NotNull(message = "Debe seleccionar un rubro") Integer rubroId,
			@NotNull(message = "Debe seleccionar una situación ante el IVA") Integer ivaId) {
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
		this.localidad = localidad;
		this.urlImagen = urlImagen;
		this.provinciaId = provinciaId;
		this.rubroId = rubroId;
		this.ivaId = ivaId;
	}

	public ProveedorDTO(Proveedor proveedor) {
		this.codigo = proveedor.getCodigo();
		this.razonSocial = proveedor.getRazonSocial();
		this.web = proveedor.getWeb();
		this.email = proveedor.getEmail();
		this.telefono = proveedor.getTelefono();
		this.calle = proveedor.getCalle();
		this.altura = proveedor.getAltura();
		this.codigoPostal = proveedor.getCodigoPostal();
		this.cuit = proveedor.getCuit();
		this.contactoNombre = proveedor.getContactoNombre();
		this.contactoApellido = proveedor.getContactoApellido();
		this.contactoTelefono = proveedor.getContactoTelefono();
		this.contactoEmail = proveedor.getContactoEmail();
		this.contactoRol = proveedor.getContactoRol();
		this.localidad = proveedor.getLocalidad();
		this.urlImagen = proveedor.getUrlImagen();
		this.estado = proveedor.getEstado();
		this.id = proveedor.getId();
		this.ivaId = proveedor.getIva().getId();
		this.provinciaId = proveedor.getProvincia().getId();
		this.provincia = proveedor.getProvincia().getProvincia();
		this.pais = proveedor.getProvincia().getPais().getPais();
		this.rubroId = proveedor.getRubro().getId();
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

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public Integer getRubroId() {
		return rubroId;
	}

	public void setRubroId(Integer rubroId) {
		this.rubroId = rubroId;
	}

	public Integer getIvaId() {
		return ivaId;
	}

	public void setIvaId(Integer ivaId) {
		this.ivaId = ivaId;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
