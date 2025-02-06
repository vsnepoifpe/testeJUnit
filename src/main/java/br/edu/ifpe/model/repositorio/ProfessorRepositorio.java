package br.edu.ifpe.model.repositorio;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifpe.model.entidades.Professor;

public class ProfessorRepositorio {
    private Map<String, Professor> professores = new HashMap<>();

    public void salvar(Professor professor) {
        professores.put(professor.getId(), professor);
    }

    public Professor buscarPorId(String id) {
        return professores.get(id);
    }
}

