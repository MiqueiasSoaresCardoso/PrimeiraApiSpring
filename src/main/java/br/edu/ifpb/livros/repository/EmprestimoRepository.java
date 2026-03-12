package br.edu.ifpb.livros.repository;

import br.edu.ifpb.livros.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    //Lista os emprestimos ativos
    List<Emprestimo> findByAtivoTrue();
    //Lista os emprestimos por aluno de acordo com a matricula fornecida
    List<Emprestimo> findByMatriculaAluno(String matricula);

}
