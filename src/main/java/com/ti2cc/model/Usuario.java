package com.ti2cc.model;

import org.json.JSONObject;

public class Usuario implements JsonFormatter{
	private int id;
	private String usuario;
	private String nomeCompleto;
	private String email;
	private String senha;
	
	public Usuario() {
		this.id = -1;
		this.usuario = "";
		this.nomeCompleto = "";
		this.email = "";
		this.senha = "";
	}
	
	public Usuario(int id, String usuario, String nomeCompleto, String email, String senha) {
		this.id = id;
		this.usuario = usuario;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("usuario", this.getUsuario());
		obj.put("nomeCompleto", this.getNomeCompleto());
		obj.put("email", this.getEmail());
		obj.put("senha", this.getSenha());
		return obj;
	}
	
}
