package test.java.br.edu.ifpe.service;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;

class ProfessorServiceTest {

    private ProfessorRepositorio professorRepositorio;
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    void deveAtribuirDisciplinaAProfessor() {
        Professor professor = new Professor("456", "Dra. Maria");
        professorRepositorio.salvar(professor);

        professorService.atribuirDisciplina("456", "Física Quântica");

        assertTrue(professor.getDisciplina().contains("Física Quântica"));
    }

    @Test
    void deveLancarExcecaoSeProfessorNaoExistir() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            professorService.atribuirDisciplina("999", "História");
        });

        assertEquals("Professor não encontrado.", exception.getMessage());
    }

    @Test
    void deveAdicionarProfessorComSucesso() {
        Professor professor = new Professor("456", "Carlos");

        professorService.adicionar(professor);

        Professor professorSalvo = professorRepositorio.buscarPorId("456");
        assertNotNull(professorSalvo);
        assertEquals("Carlos", professorSalvo.getNome());
    }

    @Test
    void deveLancarExcecaoAoAdicionarProfessorInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(null);
        });

        assertEquals("Professor Inválido", exception.getMessage());

        Professor professorSemNome = new Professor("456", "");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professorSemNome);
        });

        assertEquals("Professor Inválido", exception.getMessage());
    }
}
