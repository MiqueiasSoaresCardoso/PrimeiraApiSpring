package br.edu.ifpb.livros.service;

import br.edu.ifpb.livros.dto.EmprestimoCreateDTO;
import br.edu.ifpb.livros.model.Emprestimo;
import br.edu.ifpb.livros.model.Livro;
import br.edu.ifpb.livros.repository.EmprestimoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;

    //injeção de dependencia
    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
    }

    //Listar
    public List<Emprestimo> listar(){
        return emprestimoRepository.findAll();
    }
    //Buscar por Id
    public Emprestimo buscar(Long id){
        return emprestimoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Erro ao buscar o emprestimo")
        );
    }
    //Emprestar
    public Emprestimo emprestar(EmprestimoCreateDTO dto){
        Livro livro = livroService.buscarPorId(dto.getLivroId());

        //Verificando se o livro está ou nãõ disponivel
        if(!livro.isDisponivel()){
            throw new RuntimeException("Livro indisponivel para emprestimo");
        }
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setMatriculaAluno(dto.getMatriculaAluno());
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setAtivo(true);
        emprestimo.getDataDevolucaoFinal(LocalDate.now().plusDays(30));
        //Marcando o livro como indisponivel
        livro.setDisponivel(false);
        //// salva empréstimo (JPA gerencia a mudança do livro se estiver no mesmo contexto)
        return emprestimoRepository.save(emprestimo);

    }
    //Devolver
    public Emprestimo devolver (Long id){
        //Verificando se o emprestimo existe
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprestimo não encontrado")
        );
        //Se ele existir, verificando se está ativo
        if(!emprestimo.isAtivo()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este emprestimo já foi encerrado!");
        }
        emprestimo.setAtivo(false);
        //Inserindo a data da devolução (futuro: avisar ao usuário se ele atrasou na entrega)
        emprestimo.setDataDevolucao(LocalDate.now());
        //Liberar o livro
        emprestimo.getLivro().setDisponivel(true);
        return emprestimoRepository.save(emprestimo);

    }
}
