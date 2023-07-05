package com.brunofelixdev.springdatajpa;

import com.brunofelixdev.springdatajpa.dao.ITefefoneRepository;
import com.brunofelixdev.springdatajpa.dao.IUsuarioRepository;
import com.brunofelixdev.springdatajpa.models.Telefone;
import com.brunofelixdev.springdatajpa.models.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	IUsuarioRepository iUsuarioRepository;

	@Autowired
	ITefefoneRepository iTefefoneRepository;

	@Test
	public void inserirtUsuario() {

		Usuario usuario = new Usuario();

		usuario.setEmail("bruno@gmail.com");
		usuario.setIdade(26);
		usuario.setNome("buma");
		usuario.setLogin("brunof");
		usuario.setSenha("123456");

		iUsuarioRepository.save(usuario);

		System.out.println("Quantidade cadastrado = " + iUsuarioRepository.count());
	}

	@Test
	public void buscarUsusarioPorId() {

		Optional<Usuario> usuario = iUsuarioRepository.findById(1L);

		System.out.println(usuario.get().getNome());
		System.out.println(usuario.get().getEmail());
		System.out.println(usuario.get().getIdade());
		System.out.println(usuario.get().getSenha());
		System.out.println(usuario.get().getLogin());
		System.out.println(usuario.get().getId());

		for (Telefone telefone: usuario.get().getTelefones()) {
			System.out.println(telefone.getNumero());
		}

		System.out.println("-------------------------------------");


	}

	@Test
	public void buscarTodosUsusarios() {

		List<Usuario> usuarios = iUsuarioRepository.findAll();

		for( Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
			for (Telefone telefone: usuario.getTelefones()) {
				System.out.println(telefone.getNumero());
			}
			System.out.println("-------------------------------------");
		}
	}

	@Test
	public void atualizarUsuario() {

		Optional<Usuario> usuario = iUsuarioRepository.findById(302L);

		Usuario novoUsuario = usuario.get();

		System.out.println("antes de atualizar = " + novoUsuario.getNome());

		novoUsuario.setNome("kuka");

		iUsuarioRepository.save(novoUsuario);

		System.out.println("depois de atualizar = " + usuario.get().getNome());
	}

	@Test
	public void deletarUsusario() {

		Optional<Usuario> usuario = iUsuarioRepository.findById(302L);

		Usuario novoUsuario = usuario.get();

		iUsuarioRepository.delete(novoUsuario);
	}

	@Test
	public void deletarUsusarioPorId() {

		iUsuarioRepository.deleteById(252L);
	}

	@Test
	public void buscarUsusarioPorNome() {

		List<Usuario> usuarios = iUsuarioRepository.buscarPorNome("Bruno");

		for( Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getIdade());
		}
	}

	@Test
	public void buscarUsusarioPorLogin() {

		List<Usuario> usuarios = iUsuarioRepository.findByLogin("brunof");

		for( Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getIdade());
		}
	}

	@Test
	public void buscarPorNomeParam() {

		Usuario usuario = iUsuarioRepository.buscarPorNomeParam("brunof");

		System.out.println(usuario.getNome());
		System.out.println(usuario.getId());
		System.out.println(usuario.getLogin());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getIdade());
	}

	@Test
	public void deletarPorNome() {

		iUsuarioRepository.deletePorNome("vedita");
	}

	@Test
	public void atualizarEmailPorNome() {

		iUsuarioRepository.atualizarEmailPorNome("brunofelix@gmail.com", "Bruno");
	}

	@Test
	public void inserirTelefone() {

		Telefone telefone = new Telefone();

		Optional<Usuario> usuario = iUsuarioRepository.findById(1L);
//		Usuario usuario = new Usuario();
//		usuario.setEmail("bruno@gmail.com");
//		usuario.setIdade(26);
//		usuario.setNome("buma");
//		usuario.setLogin("brunof");
//		usuario.setSenha("123456");

//		Usuario usuarioCadastrado = iUsuarioRepository.save(usuario);

		telefone.setNumero("91989305433");
		telefone.setTipo("celular");
		telefone.setUsuario(usuario.get());

		iTefefoneRepository.save(telefone);
	}
}
