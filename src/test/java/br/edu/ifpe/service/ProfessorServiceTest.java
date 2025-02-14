package test.java.br.edu.ifpe.service;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.br.edu.ifpe.model.entidades.Professor;
import main.java.br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import main.java.br.edu.ifpe.service.ProfessorService;


public class ProfessorServiceTest {

    private ProfessorService professorService;
    private ProfessorRepositorio professorRepository;

    //Constantes
    private static final String PROFESSOR_ID = "1";
    private static final String PROFESSOR_NAME = "Vilmar";
    private static final String DISCIPLINA = "Testes de Software";
    private static final String ERRO_MESSAGE = "Professor Inválido";
    

    @BeforeEach
    public void setUp() {
        professorRepository = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepository);
    }

    //Cenário de teste: Adicionar um professor
    @Test
    public void testAdicionarSuccess() {
        Professor professor = new Professor(PROFESSOR_ID, PROFESSOR_NAME);
        professorService.adicionar(professor);
        assertTrue(professorRepository.buscarPorId(professor.getId()) != null);
    }

    @Test
    public void testAdicionarFailureProfessorNulo() {
        Professor professor = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals(ERRO_MESSAGE, exception.getMessage());
    }

    @Test
    public void testAdicionarFailureNomeNulo() {
        Professor professor = new Professor(PROFESSOR_ID, null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals(ERRO_MESSAGE, exception.getMessage());
    }

    @Test
    public void testAdicionarFailureNomeVazio() {
        Professor professor = new Professor(PROFESSOR_ID, "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals(ERRO_MESSAGE, exception.getMessage());
    }

    @Test
    public void testAdicionarFailureIdNulo() {
        Professor professor = new Professor(null, PROFESSOR_NAME);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals(ERRO_MESSAGE, exception.getMessage());
    }

    @Test
    public void testAdicionarFailureIdVazio() {
        Professor professor = new Professor("", PROFESSOR_NAME);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            professorService.adicionar(professor);
        });
        assertEquals(ERRO_MESSAGE, exception.getMessage());
    }


    //Cenário de teste: Atribuir disciplina a um professor
    @Test
    public void testAtribuirDisciplinaSuccess() {
        Professor professor = new Professor(PROFESSOR_ID, PROFESSOR_NAME);
        professorService.adicionar(professor);
        professorService.atribuirDisciplina(professor.getId(), DISCIPLINA);
        assertEquals(DISCIPLINA, professor.getDisciplina());
    }

    @Test
    public void testAtribuirDisciplinaFailureProfessorNaoCadastrado() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            professorService.atribuirDisciplina("2", DISCIPLINA);//professor id 2 não existe
        });
        assertEquals("Professor não encontrado.", exception.getMessage());
    }
}
