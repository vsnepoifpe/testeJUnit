package test.java.br.edu.ifpe.service;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorServiceTest {

    private ProfessorRepositorio professorRepositorio;
    private ProfessorService professorService;

    @BeforeEach
    public void config() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    public void atribuirDisciplina() {
        Professor professor = new Professor("1", "Vilmar");
        professorService.adicionar(professor);
        professorService.atribuirDisciplina("1", "Português");

        assertEquals("Português", professor.getDisciplina());
    }

    @Test
    public void atribuirDisciplina_DeveLancarExcecaoCasoProfessorNaoEncontrado() {
        assertThrows(NoSuchElementException.class, () ->
                professorService.atribuirDisciplina("10", "Matemática"));
    }

    @Test
    public void adicionar() {
        Professor professor = new Professor("1", "Vilmar");
        professorService.adicionar(professor);
        Professor alunoAdicionado = professorRepositorio.buscarPorId("1");

        assertNotNull(alunoAdicionado);
        assertEquals("Vilmar", alunoAdicionado.getNome());
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoProfessorNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                professorService.adicionar(null));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoNomeProfessorNulo() {
        Professor professor = new Professor("1", null);
        assertThrows(IllegalArgumentException.class, () ->
                professorService.adicionar(professor));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoNomeProfessorEmBranco() {
        Professor professor = new Professor("1", "");
        assertThrows(IllegalArgumentException.class, () ->
                professorService.adicionar(professor));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoIdProfessorNulo() {
        Professor professor = new Professor(null, "Vilmar");
        assertThrows(IllegalArgumentException.class, () ->
                professorService.adicionar(professor));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoIdProfessorEmBranco() {
        Professor professor = new Professor("", "Vilmar");
        assertThrows(IllegalArgumentException.class, () ->
                professorService.adicionar(professor));
    }
}
