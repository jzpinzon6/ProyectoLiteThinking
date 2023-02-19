package com.litethinking.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.litethinking.interfaceService.InterUsuarioService;
import com.litethinking.model.Usuario;

@Repository
public interface InterUsuario extends InterUsuarioService  {
	
	public static final JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    
	@Override
	public default List<Usuario> findAll() {
	     String sql = "SELECT * FROM usuarios";
	     List<Usuario> usuarios = jdbcTemplate.query(sql, new UsuarioRowMapper());
	     return usuarios;
	}
	
	@Override
	public default Usuario findById(long id) {
	     String sql = "SELECT * FROM usuarios WHERE id = ?";
	     Usuario usuario = (Usuario) jdbcTemplate.queryForList(sql, new Object[]{id}, new UsuarioRowMapper());
	     return usuario;
	}
	    
	@Override
	public default Usuario save(Usuario usuario) {
	      String sql = "INSERT INTO usuarios (nombre, apellido, correo_electronico, contrasena) VALUES (?, ?, ?, ?)";
	      jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getContrasena());
	      return usuario;
	}
	    
	@Override
	public default void update(Usuario usuario) {
	     String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, correo_electronico = ?, contrasena = ? WHERE id = ?";
	      jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getContrasena(), usuario.getId());
	}
	    
	@Override
	public default void deleteById(long id) {
	     String sql = "DELETE FROM usuarios WHERE id = ?";
	     jdbcTemplate.update(sql, id);
	}
	    
	static class UsuarioRowMapper implements RowCallbackHandler {
	     public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
	          Usuario usuario = new Usuario(null, null, null, null, null);
	          usuario.setId(rs.getLong("id"));
	          usuario.setNombre(rs.getString("nombre"));
	          usuario.setApellido(rs.getString("apellido"));
	          usuario.setCorreoElectronico(rs.getString("correoElectronico"));
	          usuario.setContrasena(rs.getString("contrasena"));
	          return usuario;
	     }

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			return jdbcTemplate.
			
		}
	}
}
	

