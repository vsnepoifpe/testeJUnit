package br.edu.ifpe.model.entidades;

public class Professor {
    private String id;
    private String nome;
    private String disciplina;

    public Professor(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.disciplina = null; 
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDisciplina() { return disciplina; }

    public void atribuirDisciplina(String disciplina) {
        if (this.disciplina != null) {
            throw new IllegalStateException("O professor já ministra uma disciplina.");
        }
        this.disciplina = disciplina;
    }
}