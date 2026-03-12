package br.edu.ifpb.livros.dto;

import br.edu.ifpb.livros.model.Livro;

import java.time.LocalDateTime;

public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anoPublicacao;
    private boolean disponivel;
    private LocalDateTime dataCadastro;

    public static LivroResponseDTO from(Livro livro) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.id = livro.getId();
        dto.titulo = livro.getTitulo();
        dto.autor = livro.getAutor();
        dto.isbn = livro.getIsbn();
        dto.anoPublicacao = livro.getAnoPublicacao();
        dto.disponivel = livro.isDisponivel();
        dto.dataCadastro = livro.getDataCadastro();
        return dto;
    }

    //Metódos Acessores apenas de consulta (get)
    public Long getId() {return id;}
    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public String getIsbn() {return isbn;}
    public Integer getAnoPublicacao() {return anoPublicacao;}
    public boolean isDisponivel() {return disponivel;}
    public LocalDateTime getDataCadastro() {return dataCadastro;}

}
