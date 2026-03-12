package br.edu.ifpb.livros.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class LivroCreateDTO {

    @NotBlank(message = "titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "autor é obrigatório")
    private String autor;

    @NotBlank(message = "isbn é obrigatório")
    private String isbn;

    @Min(value = 1700, message = "anoPublicacao deve ser >= 1700")
    @Max(value = 2026, message = "anoPublicacao deve ser <= 2026")
    private Integer anoPublicacao;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

}
