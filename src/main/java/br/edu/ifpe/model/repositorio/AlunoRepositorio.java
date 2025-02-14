package main.java.br.edu.ifpe.model.repositorio;

import java.util.HashMap;
import java.util.Map;

import main.java.br.edu.ifpe.model.entitades.Aluno;

public class AlunoRepositorio {
    private Map<String, Aluno> alunos = new HashMap<>();

    public void salvar(Aluno aluno) {
        alunos.put(aluno.getId(), aluno);
    }

    public Aluno buscarPorId(String id) {
        return alunos.get(id);
    }
}
