package br.edu.ifpb.livros.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String matriculaAluno;

    @ManyToOne(optional = false)
    /*Muitos emprestimos para um livro, já o optinal define se é obrigatório ou não
    nesse caso, NÃO EXISTE EMPRESTIMO SEM LIVRO*/
    @JoinColumn(name = "livro_id")
    /*Essa classe (Emprestimo) tem uma referência para uma entidade Livro, \n
    e muitos empréstimos podem apontar para o mesmo livro.O name "livro_id s refere a qual coluna em
    emprestimos vai guardar o livro"*/
    private Livro livro;

    @Column(nullable = false)
    public LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    private LocalDate dataDevolucaoFinal;

    @Column(nullable=false)
    private boolean ativo = true;

    public Emprestimo() {}

    public Long getId() { return id; }

    public String getMatriculaAluno() { return matriculaAluno; }
    public void setMatriculaAluno(String matriculaAluno) { this.matriculaAluno = matriculaAluno; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public LocalDate getDataDevolucaoFinal() { return dataDevolucaoFinal; }
    public void SetDataDevolucaoFinal(LocalDate dataDevolucaoFinal) {
        this.dataDevolucaoFinal = dataDevolucaoFinal;
    }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }


}
