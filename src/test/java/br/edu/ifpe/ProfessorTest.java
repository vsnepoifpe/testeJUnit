package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {
    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor("1", "Dr. Silva");
    }

    @Test
    void atribuirDisciplinaComSucesso() {
        professor.atribuirDisciplina("Matemática");
        assertEquals("Matemática", professor.getDisciplina());
    }

    @Test
    void naoPodeAtribuirSegundaDisciplina() {
        professor.atribuirDisciplina("Matemática");
        assertThrows(IllegalStateException.class, () -> professor.atribuirDisciplina("Física"));
    }

    @Test
    void disciplinaInicialmenteNula() {
        assertNull(professor.getDisciplina());
    }
}
