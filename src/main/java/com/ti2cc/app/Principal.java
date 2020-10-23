package com.ti2cc.app;

import static spark.Spark.*;

import com.ti2cc.service.UsuarioService;

public class Principal {
	
	private static UsuarioService usuarioService = new UsuarioService();
	
	public static void main(String[] args) {
		
		port(6789);
		
		get("/usuarios", (request, response) -> {
			return usuarioService.listar(request, response);
		});
		
		post("/usuarios", (request, response) -> {
			
			return usuarioService.inserir(request, response);
			
		});
		
		

		/*
		//Inserir um elemento na tabela
		Usuario usuario = new Usuario(11, "pablo", "pablo",'M');
		if(dao.inserirUsuario(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
		
		//Mostrar usuários do sexo masculino		
		System.out.println("==== Mostrar usuários do sexo masculino === ");
		Usuario[] usuarios = dao.getUsuariosMasculinos();
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}

		//Atualizar usuário
		usuario.setSenha("nova senha");
		dao.atualizarUsuario(usuario);

		//Mostrar usuários do sexo masculino
		System.out.println("==== Mostrar usuários === ");
		usuarios = dao.getUsuarios();
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
		
		//Excluir usuário
		dao.excluirUsuario(usuario.getCodigo());
		
		//Mostrar usuários
		usuarios = dao.getUsuarios();
		System.out.println("==== Mostrar usuários === ");		
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}*/
		
		//dao.close();
	}
}
