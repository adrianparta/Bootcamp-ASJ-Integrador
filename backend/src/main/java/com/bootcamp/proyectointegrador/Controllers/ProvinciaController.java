package com.bootcamp.proyectointegrador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.proyectointegrador.Models.Provincia;
import com.bootcamp.proyectointegrador.Services.ProvinciaService;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping
	public ResponseEntity<List<Provincia>> getProvincias(){
		return new ResponseEntity<List<Provincia>>(provinciaService.obtenerProvincias(), HttpStatus.OK);
	}
}
