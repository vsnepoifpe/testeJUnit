package br.edu.ifpe.model.repositorio;

import br.edu.ifpe.model.entidades.Aluno;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AlunoRepositorioTest
{
    private AlunoRepositorio alunoRepositorio;

//    @BeforeAll
    @BeforeEach // Encapsulation for future delete?
    public void setUp()
    {
        alunoRepositorio = new AlunoRepositorio();

        var a = new Aluno("1", "Helmuth Voss");
        a.matricularEmDisciplina("Matematica");
        a.matricularEmDisciplina("Biologia");
        alunoRepositorio.salvar(a);

        alunoRepositorio.salvar(new Aluno("2", "Jakub Farobek"));
        alunoRepositorio.salvar(new Aluno("3", "Klaus Poppe"));
    }

    @Test
    void salvar()
    {
        Aluno aluno = new Aluno("4", "Franz Bonaparta");
        alunoRepositorio.salvar(aluno);
        Aluno newAluno = alunoRepositorio.buscarPorId(aluno.getId());

        assertEquals(aluno.getId(), newAluno.getId());
        assertEquals(aluno.getNome(), newAluno.getNome());
        assertTrue(newAluno.getDisciplinas().isEmpty());
    }

    @Test
    void buscarPorId()
    {
        String id = "1";
        Aluno aluno = alunoRepositorio.buscarPorId(id);

        assertEquals(id, aluno.getId());
        assertEquals("Helmuth Voss", aluno.getNome());
        assertTrue(
                aluno
                        .getDisciplinas()
                        .stream()
                        .allMatch((d) -> d.equals("Matematica") || d.equals("Biologia"))
        );
    }
}