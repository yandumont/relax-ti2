package com.ti2cc.service;

import java.util.HashMap;

import org.json.JSONArray;

import com.ti2cc.dao.UsuarioDAO;
import com.ti2cc.model.Usuario;
import com.ti2cc.util.BodyParser;

import spark.Request;
import spark.Response;

public class UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioService() {
		
		usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.conectar();
		
	}
	
	public JSONArray listar(Request request, Response response) {
		response.header("content-type", "application/json");
		
		Usuario[] usuarios = usuarioDAO.getUsuarios();
		
		JSONArray array = usuarios == null ? new JSONArray() : new JSONArray(usuarios);
		
		return array;
	}
	
	public Object inserir(Request request, Response response) {
		HashMap<String, String> usuarioObj = BodyParser.parseBody(request.body());
		
		response.header("Access-Control-Allow-Origin", "*");
		
		Usuario usuario = new Usuario(	
				0,
				usuarioObj.get("usuario"),
				usuarioObj.get("nomeCompleto"),
				usuarioObj.get("email"),
				usuarioObj.get("senha")
				);
		
		return usuarioDAO.inserirUsuario(usuario);
		
		
		
	}

}
