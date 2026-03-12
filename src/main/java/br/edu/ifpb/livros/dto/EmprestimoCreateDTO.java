package br.edu.ifpb.livros.dto;

import jakarta.validation.constraints.NotBlank;

public class EmprestimoCreateDTO {

    @NotBlank(message = "matriculaAluno é obrigatória")
    private String matriculaAluno;

    @NotBlank(message = "livroId é obrigatório")
    private Long livroId;

    // Metódos acessores apenas de consulta (get)
    public String getMatriculaAluno() {return matriculaAluno;}
    public void setMatriculaAluno(String matriculaAluno) {this.matriculaAluno = matriculaAluno;}
    public Long getLivroId() {return livroId;}
    public void setLivroId(Long livroId) {this.livroId = livroId;}

}
