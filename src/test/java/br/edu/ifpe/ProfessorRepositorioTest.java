package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorRepositorioTest {
    private ProfessorRepositorio professorRepositorio;

    @BeforeEach
    void setUp() {
        professorRepositorio = new ProfessorRepositorio();
    }

    @Test
    void salvarProfessorComSucesso() {
        Professor professor = new Professor("1", "Professor Leon");
        professorRepositorio.salvar(professor);
        assertEquals(professor, professorRepositorio.buscarPorId("1"));
    }

    @Test
    void buscarProfessorInexistente() {
        assertNull(professorRepositorio.buscarPorId("999"));
    }

    @Test
    void sobrescreverProfessorComMesmoId() {
        Professor professor1 = new Professor("1", "Professor Leon");
        Professor professor2 = new Professor("1", "Professor Vilmar");
        professorRepositorio.salvar(professor1);
        professorRepositorio.salvar(professor2);
        assertEquals(professor2, professorRepositorio.buscarPorId("1"));
    }
}
