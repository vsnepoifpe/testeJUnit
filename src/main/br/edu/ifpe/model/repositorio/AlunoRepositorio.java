package main.br.edu.ifpe.model.repositorio;

import java.util.HashMap;
import java.util.Map;

import main.br.edu.ifpe.model.entidades.Aluno;

public class AlunoRepositorio {
    private Map<String, Aluno> alunos = new HashMap<>();

    public void salvar(Aluno aluno) {
        alunos.put(aluno.getId(), aluno);
    }

    public Aluno buscarPorId(String id) {
        return alunos.get(id);
    }
}
