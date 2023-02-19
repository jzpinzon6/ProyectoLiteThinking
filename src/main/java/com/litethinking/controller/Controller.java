package com.litethinking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litethinking.interfaces.InterUsuario;
import com.litethinking.model.Usuario;

@RestController
@RequestMapping("/api_web/usuarios")
public class Controller {
	
     
	 private final InterUsuario usuarioService;

	    public Controller(InterUsuario usuarioService) {
	        this.usuarioService = usuarioService;
	    }

	    @PostMapping
	    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
	        Usuario usuarioCreado = usuarioService.save(usuario);
	        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<List<Usuario>> listarUsuarios() {
	        List<Usuario> usuarios = usuarioService.findAll();
	        usuarios.forEach(Usuario::enmascararContrasena);
	        return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    }

	    @GetMapping("/{campo}/{valor}")
	    public ResponseEntity<List<Usuario>> filtrarUsuarios(@PathVariable String campo, @PathVariable String valor) {
	        List<Usuario> usuariosFiltrados = usuarioService.findById(Long id);
	        usuariosFiltrados.forEach(Usuario::enmascararContrasena);
	        return new ResponseEntity<>(usuariosFiltrados, HttpStatus.OK);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
	        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id);
	        usuarioActualizado.enmascararContrasena();
	        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
	        usuarioService.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}


