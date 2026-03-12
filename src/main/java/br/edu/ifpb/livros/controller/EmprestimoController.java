package br.edu.ifpb.livros.controller;

import br.edu.ifpb.livros.dto.EmprestimoCreateDTO;
import br.edu.ifpb.livros.dto.EmprestimoResponseDTO;
import br.edu.ifpb.livros.model.Emprestimo;
import br.edu.ifpb.livros.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    //Injeção de dependencia
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    //Listando os emprestimos
    @GetMapping
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.listar();
    }

    @GetMapping("/{id}")
    public EmprestimoResponseDTO buscarEmprestimoPorId(@PathVariable Long id) {
        return EmprestimoResponseDTO.fromEmprestimo(emprestimoService.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponseDTO adicionar(@RequestBody EmprestimoCreateDTO emprestimoCreateDTO) {
        return EmprestimoResponseDTO.fromEmprestimo(emprestimoService.emprestar(emprestimoCreateDTO));
    }

    @PostMapping("/{id}/devolucao")
    public EmprestimoResponseDTO devolucao(@PathVariable Long id) {
        return EmprestimoResponseDTO.fromEmprestimo(emprestimoService.devolver(id));
    }


}
