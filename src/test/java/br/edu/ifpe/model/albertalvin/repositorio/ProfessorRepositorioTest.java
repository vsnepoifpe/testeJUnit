package br.edu.ifpe.model.albertalvin.repositorio;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfessorRepositorioTest
{
    private ProfessorRepositorio professorRepositorio;

    @BeforeEach
    public void setUp()
    {
        professorRepositorio = new ProfessorRepositorio();

        var a = new Professor("1", "Helmuth Voss");
        a.atribuirDisciplina("Matematica");
        professorRepositorio.salvar(a);

        professorRepositorio.salvar(new Professor("2", "Jakub Farobek"));
        professorRepositorio.salvar(new Professor("3", "Klaus Poppe"));
    }

    @Test
    void salvar()
    {
        Professor prof = new Professor("4", "Franz Bonaparta");
        prof.atribuirDisciplina("Artes I");
        professorRepositorio.salvar(prof);

        Professor newProf = professorRepositorio.buscarPorId(prof.getId());

        assertEquals(prof.getId(), newProf.getId());
        assertEquals(prof.getNome(), newProf.getNome());
        assertEquals(prof.getDisciplina(), newProf.getDisciplina());
    }

    @Test
    void buscarPorId()
    {
        String id = "1";
        Professor prof = professorRepositorio.buscarPorId(id);

        assertEquals(id, prof.getId());
        assertEquals("Helmuth Voss", prof.getNome());
        assertEquals("Matematica", prof.getDisciplina());
    }
}