package test.java.br.edu.ifpe.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;

public class ProfessorServiceTest {

    private ProfessorRepositorio professorRepositorio;
    private ProfessorService professorService;

    @BeforeEach
    public void setUp() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    public void testAdicionarProfessorValido() {
        Professor professor = new Professor("P1", "Dr. Vilmar");
        professorService.adicionar(professor);
        assertEquals(professor, professorRepositorio.buscarPorId("P1"));
    }

    @Test
    public void testAdicionarProfessorComIdEmBranco() {
        Professor professor = new Professor("", "Dr. Vilmar");
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(professor));
    }

    @Test
    public void testAdicionarProfessorComNomeEmBranco() {
        Professor professor = new Professor("P1", "");
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(professor));
    }

    @Test
    public void testAtribuirDisciplina() {
        Professor professor = new Professor("P1", "Dr. Vilmar");
        professorService.adicionar(professor);
        professorService.atribuirDisciplina("P1", "Matemática");
        assertEquals("Matemática", professor.getDisciplina());
    }

    @Test
    public void testAtribuirDisciplinaJaExistente() {
        Professor professor = new Professor("P1", "Dr. Vilmar");
        professorService.adicionar(professor);
        professorService.atribuirDisciplina("P1", "Matemática");
        assertThrows(IllegalStateException.class, () -> professorService.atribuirDisciplina("P1", "Física"));
    }

    @Test
    public void testAtribuirDisciplinaProfessorNaoExistente() {
        assertThrows(NoSuchElementException.class, () -> professorService.atribuirDisciplina("P1", "Matemática"));
    }
}
