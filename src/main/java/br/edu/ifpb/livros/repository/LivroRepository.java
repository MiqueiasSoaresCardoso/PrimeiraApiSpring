package br.edu.ifpb.livros.repository;

import br.edu.ifpb.livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    //Busca livros pelo titulo
    Optional<Livro> findByTitulo(String titulo);
    //Busca Livros pelo ISBN
    Optional<Livro> findByIsbn(String isbn);
}
