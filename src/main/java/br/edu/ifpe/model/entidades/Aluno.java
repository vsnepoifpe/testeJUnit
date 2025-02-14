package main.java.br.edu.ifpe.model.entidades;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String id;
    private String nome;
    private List<String> disciplinas;

    public Aluno(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public List<String> getDisciplinas() { return disciplinas; }

    public void matricularEmDisciplina(String disciplina) {
        if (disciplinas.size() >= 5) {
            throw new IllegalStateException("O aluno já está matriculado no máximo de 5 disciplinas.");
        }
        disciplinas.add(disciplina);
    }
}
