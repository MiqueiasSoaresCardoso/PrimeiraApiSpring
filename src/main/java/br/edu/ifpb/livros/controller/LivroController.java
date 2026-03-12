package br.edu.ifpb.livros.controller;

import br.edu.ifpb.livros.dto.LivroCreateDTO;
import br.edu.ifpb.livros.dto.LivroResponseDTO;
import br.edu.ifpb.livros.dto.LivroUpdateDTO;
import br.edu.ifpb.livros.model.Livro;
import br.edu.ifpb.livros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    //Instancia global do LivroService
    private final LivroService livroService;

    //Injeção de dependência
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    //Listagem
    @GetMapping
    public List<Livro> listar(){
        return livroService.listar();
    }
    //Listar um livro especifico
    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id){
        Livro livro = livroService.buscarPorId(id);
        return livro;
    }

    //Criar um livro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO cadastrar( @Valid @RequestBody LivroCreateDTO livroDto){
        return LivroResponseDTO.from(livroService.cadastrar(livroDto));
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id, @RequestBody LivroUpdateDTO livroDto){
        return LivroResponseDTO.from(livroService.atualizar(id, livroDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivro(@PathVariable Long id){
        livroService.remover(id);
    }



}
