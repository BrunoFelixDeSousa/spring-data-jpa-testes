package com.brunofelixdev.springdatajpa.dao;

import com.brunofelixdev.springdatajpa.models.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select p from Usuario p where p.nome like %?1%")
    public List<Usuario> buscarPorNome(String nome);

    public List<Usuario> findByLogin(String login);

    @Query(value = "select p from Usuario p where p.nome =:nome")
    public Usuario buscarPorNomeParam(String nome);

    @Modifying
    @Transactional
    @Query(value = "delete from Usuario u where u.nome = ?1")
    public void deletePorNome(String nome);

    @Modifying
    @Transactional
    @Query(value = "update Usuario u set u.email = ?1 where u.nome = ?2")
    public void atualizarEmailPorNome(String email, String nome);
}
