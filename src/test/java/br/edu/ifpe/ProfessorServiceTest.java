package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class ProfessorServiceTest {
    private ProfessorRepositorio professorRepositorio;
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    void atribuirDisciplinaParaProfessorComSucesso() {
        Professor professor = new Professor("2", "Professor Vilmar");
        professorRepositorio.salvar(professor);
        professorService.atribuirDisciplina("2", "Física");
        assertEquals("Física", professor.getDisciplina());
    }

    @Test
    void lancarExcecaoAoAtribuirDisciplinaParaProfessorInexistente() {
        assertThrows(NoSuchElementException.class, () -> professorService.atribuirDisciplina("999", "História"));
    }

    @Test
    void adicionarProfessorComSucesso() {
        Professor professor = new Professor("3", "Professora Renata");
        professorService.adicionar(professor);
        assertEquals(professor, professorRepositorio.buscarPorId("3"));
    }

    @Test
    void lancarExcecaoAoAdicionarProfessorComNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(new Professor("4", "")));
    }

    @Test
    void lancarExcecaoAoAdicionarProfessorComIdVazio() {
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(new Professor("", "Dr. Xavier")));
    }

    @Test
    void lancarExcecaoAoAdicionarProfessorNulo() {
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(null));
    }

    @Test
    void adicionarProfessorComEspacosNoNome() {
        Professor professor = new Professor("5", "   ");
        assertThrows(IllegalArgumentException.class, () -> professorService.adicionar(professor));
    }
}

