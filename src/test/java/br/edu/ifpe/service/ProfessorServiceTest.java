package test.java.br.edu.ifpe.service;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;

public class ProfessorServiceTest {

    private ProfessorRepositorio professorRepositorio;
    private ProfessorService professorService;

    @BeforeEach
    void inicializar() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    void testarAdicaoDeProfessorComSucesso() {
        Professor professor = new Professor("3", "Anderson");
        professorService.adicionar(professor);

        Professor professorRetornado = professorRepositorio.buscarPorId("3");
        assertNotNull(professorRetornado);
        assertEquals("Anderson", professorRetornado.getNome());
    }

    @Test
    void testarAdicaoDeProfessorComNomeVazio() {
        Professor professor = new Professor("3", "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals("Professor Inválido", exception.getMessage());
    }

    @Test
    void testarAdicaoDeProfessorComIdVazio() {
        Professor professor = new Professor("", "Carlos");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals("Professor Inválido", exception.getMessage());
    }

    @Test
    void testarAtribuicaoDeDisciplinaComSucesso() {
        Professor professor = new Professor("1", "Renata");
        professorRepositorio.salvar(professor);

        professorService.atribuirDisciplina("1", "Logica");
        assertEquals("Logica", professor.getDisciplina());
    }

    @Test
    void testarAtribuicaoDeDisciplinaComProfessorNaoEncontrado() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            professorService.atribuirDisciplina("879542", "Web 4");
        });
        assertEquals("Professor não encontrado.", exception.getMessage());
    }

    @Test
    void testarAtribuicaoDeDisciplinaJaMinistrada() {
        Professor professor = new Professor("2", "Vilmar");
        professorRepositorio.salvar(professor);
        professorService.atribuirDisciplina("2", "Metodologia");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            professorService.atribuirDisciplina("2", "Metodologia");
        });
        assertEquals("O professor já ministra uma disciplina.", exception.getMessage());
    }

    @Test
    void testarAdicaoDeProfessorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(null);
        });
        assertEquals("Professor Inválido", exception.getMessage());
    }
}