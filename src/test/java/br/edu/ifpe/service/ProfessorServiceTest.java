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
    void setUp() {
        professorRepositorio = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepositorio);
    }

    @Test
    void AtribuirDisciplinaSucesso() {
        Professor professor = new Professor("1", "Abadie");
        professorRepositorio.salvar(professor);

        professorService.atribuirDisciplina("1", "LPOO");
        assertEquals("LPOO", professor.getDisciplina());
    }

    @Test
    void AtribuirDisciplinaProfessorNaoEncontrado() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            professorService.atribuirDisciplina("nonexistent", "Segurança");
        });
        assertEquals("Professor não encontrado.", exception.getMessage());
    }

    @Test
    void AtribuirDisciplinaJaMinistrada() {
        Professor professor = new Professor("2", "Marco");
        professorRepositorio.salvar(professor);
        professorService.atribuirDisciplina("2", "Distribuidos");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            professorService.atribuirDisciplina("2", "Web");
        });
        assertEquals("O professor já ministra uma disciplina.", exception.getMessage());
    }

    @Test
    void AdicionarProfessorValido() {
        Professor professor = new Professor("3", "Renata");
        professorService.adicionar(professor);

        Professor professorRetornado = professorRepositorio.buscarPorId("3");
        assertNotNull(professorRetornado);
        assertEquals("Renata", professorRetornado.getNome());
    }

    @Test
    void AdicionarProfessorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(null);
        });
        assertEquals("Professor Inválido", exception.getMessage());
    }

    @Test
    void AdicionarProfessorNomeVazio() {
        Professor professor = new Professor("3", "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals("Professor Inválido", exception.getMessage());
    }
}
