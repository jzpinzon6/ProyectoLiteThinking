package com.litethinking.interfaceService;

import java.util.List;

import com.litethinking.model.Usuario;

public interface InterUsuarioService {
	
//	 public  List<Usuario> findAll(String nombre);
//	 public  List<Usuario> findById(String apellido);
//	 public  List<Usuario> save(String correoElectronico);
	 
	public List<Usuario> findAll();
	public Usuario findById(long id);
	public Usuario save(Usuario usuario);
	public void update(Usuario usuario);
	public void deleteById(long id);	 

}
