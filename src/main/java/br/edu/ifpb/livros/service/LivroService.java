package br.edu.ifpb.livros.service;

import br.edu.ifpb.livros.dto.LivroCreateDTO;
import br.edu.ifpb.livros.dto.LivroUpdateDTO;
import br.edu.ifpb.livros.model.Livro;
import br.edu.ifpb.livros.repository.LivroRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    //Listar todos os livros
    public List<Livro> listar(){
        return livroRepository.findAll();
    }
    //Buscar o livro pelo ISBN
    public Livro buscarPorIsbn(String isbn){
        return livroRepository.findByIsbn(isbn).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro com o ISBN:  " + isbn + "Indisponivel")
        );
    }
    //Buscar o livro pelo Id
    public Livro buscarPorId(Long id){
        return livroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro com o ID:" +id + " não encontrado.")
        );
    }
    //Buscar o livro pelo titulo
    public Livro buscarPorTitulo(String titulo){
        return livroRepository.findByTitulo(titulo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro com o titulo: " + titulo + "Indisponivel")
        );
    }
    //Cadastrar o livro
    public Livro cadastrar(LivroCreateDTO dto){
        //Verificando se o livro já existe
        livroRepository.findByIsbn(dto.getIsbn()).ifPresent(livro ->{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um livro cadastrado com esse ISBN: " + dto.getIsbn());
        });
        Livro livro = new Livro(dto.getTitulo(),dto.getAutor(), dto.getIsbn(), dto.getAnoPublicacao());
        //Salvando o livro
        return livroRepository.save(livro);

    }
    //Atualizar o Livro
    public Livro atualizar(Long id, LivroUpdateDTO dto){
        Livro livro = buscarPorId(id);

        if(livro != null){
            livro.setTitulo(dto.getTitulo());
            livro.setAutor(dto.getAutor());
            livro.setAnoPublicacao(dto.getAnoPublicacao());
            return livroRepository.save(livro);
        }else{
            return null;
        }

    }
    //Removendo o livro
    public void remover(Long id){
        Livro livro = buscarPorId(id);
        livroRepository.delete(livro);
    }
}
