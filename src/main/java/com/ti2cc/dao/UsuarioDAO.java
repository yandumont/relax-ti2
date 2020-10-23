package com.ti2cc.dao;

import java.sql.*;
import com.ti2cc.model.Usuario;

public class UsuarioDAO {
	private Connection conexao;
	
	public UsuarioDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "ti2cc";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "362400";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public int inserirUsuario(Usuario usuario) {
		int id = -1;
		try {  
			Statement st = conexao.createStatement();
			
			String query = String.format(
			    "INSERT INTO usuario (usuario, nome_completo, email, senha) " +
			    "VALUES ('%s', '%s', '%s', '%s') returning id",
			    usuario.getUsuario(),
			    usuario.getNomeCompleto(),
			    usuario.getEmail(),
			    usuario.getSenha()
			);
			
			ResultSet rs= st.executeQuery(query);
			if(rs.next())
			{
				id = rs.getInt("id");
			}
			st.close();
			
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return id;
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String query = String.format(
			    "UPDATE usuario SET usuario = '%s',nome_completo = '%s', email = '%s'"
			    + ",senha = '%s' where id = %d ",
			    usuario.getUsuario(),
			    usuario.getNomeCompleto(),
			    usuario.getEmail(),
			    usuario.getSenha(),
			    usuario.getId()
			);
			st.executeUpdate(query);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirUsuario(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Usuario(rs.getInt("id"),
	                		rs.getString("usuario"), 
	                		rs.getString("nome_completo"),
	                		rs.getString("email"),
	                		rs.getString("senha"));
	                //System.out.printf("'%s'\n", usuarios[i].getUsuario());
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

}