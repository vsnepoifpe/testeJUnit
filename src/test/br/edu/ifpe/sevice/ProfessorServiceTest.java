package test.br.edu.ifpe.sevice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;


public class ProfessorServiceTest {

    private ProfessorService professorService;
    private ProfessorRepositorio professorRepository;

    @BeforeEach
    public void setUp() {
        professorRepository = new ProfessorRepositorio();
        professorService = new ProfessorService(professorRepository);
    }

    @Test
    public void testAddProfessorSuccess() {
        Professor professor = new Professor("John Doe", "Mathematics");
        professorService.adicionar(professor);
        assertTrue(professorRepository.buscarPorId(professor.getId()) != null);
    }

    @Test
    public void testAddProfessorFailure() {
        Professor professor = null;
        boolean result = professorService.addProfessor(professor);
        assertFalse(result, "Adding a null professor should fail");
    }

    @Test
    public void testRemoveProfessorSuccess() {
        Professor professor = new Professor("John Doe", "Mathematics");
        professorService.addProfessor(professor);
        boolean result = professorService.removeProfessor(professor);
        assertTrue(result, "Professor should be removed successfully");
    }

    @Test
    public void testRemoveProfessorFailure() {
        Professor professor = new Professor("John Doe", "Mathematics");
        boolean result = professorService.removeProfessor(professor);
        assertFalse(result, "Removing a non-existent professor should fail");
    }

    @Test
    public void testFindProfessorByNameSuccess() {
        Professor professor = new Professor("John Doe", "Mathematics");
        professorService.addProfessor(professor);
        Professor foundProfessor = professorService.findProfessorByName("John Doe");
        assertNotNull(foundProfessor, "Professor should be found");
        assertEquals("John Doe", foundProfessor.getName(), "Professor name should match");
    }

    @Test
    public void testFindProfessorByNameFailure() {
        Professor foundProfessor = professorService.findProfessorByName("Jane Doe");
        assertNull(foundProfessor, "Non-existent professor should not be found");
    }
}
