package test.java.br.edu.ifpe.model.repositorio;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfessorRepositorioTest {
    private ProfessorRepositorio professorRepositorio;

    @BeforeEach
    public void config() {
        professorRepositorio = new ProfessorRepositorio();
        professorRepositorio.salvar(new Professor("1", "Vilmar"));
    }

    @Test
    public void salvarProfessor() {
        Professor professor = new Professor("2", "Marcos");
        professorRepositorio.salvar(professor);
        Professor professorSalvo = professorRepositorio.buscarPorId("2");

        assertNotNull(professorSalvo);
        assertEquals("2", professorSalvo.getId());
        assertEquals("Marcos", professorSalvo.getNome());
    }

    @Test
    public void buscarPorId() {
        Professor professorSalvo = professorRepositorio.buscarPorId("1");

        assertNotNull(professorSalvo);
        assertEquals("1", professorSalvo.getId());
        assertEquals("Vilmar", professorSalvo.getNome());
    }
}
