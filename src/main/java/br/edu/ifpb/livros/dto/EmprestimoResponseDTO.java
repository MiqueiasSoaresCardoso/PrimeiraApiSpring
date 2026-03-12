package br.edu.ifpb.livros.dto;

import br.edu.ifpb.livros.model.Emprestimo;

import java.time.LocalDate;

public class EmprestimoResponseDTO {
    private Long id;
    private String matriculaAluno;
    private Long livroId;
    private String livroTitulo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public static EmprestimoResponseDTO fromEmprestimo(Emprestimo emprestimo) {
        EmprestimoResponseDTO dtoEmprestimo = new EmprestimoResponseDTO();
        dtoEmprestimo.id = emprestimo.getId();
        dtoEmprestimo.livroId = emprestimo.getLivro().getId();
        dtoEmprestimo.livroTitulo = emprestimo.getLivro().getTitulo();
        dtoEmprestimo.dataEmprestimo = emprestimo.getDataEmprestimo();
        dtoEmprestimo.dataDevolucao = emprestimo.getDataDevolucao();
        dtoEmprestimo.ativo = emprestimo.isAtivo();
        return dtoEmprestimo;
    }

    //Metódos acessores apenas de consulta

    public Long getId() {return id;};
    public String getMatriculaAluno() {return matriculaAluno;}
    public Long getLivroId() {return livroId;}
    public String getLivroTitulo() {return livroTitulo;}
    public LocalDate getDataEmprestimo() {return dataEmprestimo;}
    public LocalDate getDataDevolucao() {return dataDevolucao;}
    public boolean isAtivo() {return ativo;}


}
