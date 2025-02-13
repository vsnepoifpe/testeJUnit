package test.java.br.edu.ifpe.model.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Professor;

class ProfessorTest {

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor("1", "Vilmar");
    }

    @Test
    void AtribuirDisciplinaSucesso() {
        professor.atribuirDisciplina("Testes");
        assertEquals("Testes", professor.getDisciplina());
    }

    @Test
    void AtribuirDisciplinaJaMinistrada() {
        professor.atribuirDisciplina("Testes");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            professor.atribuirDisciplina("Métodos Ágeis");
        });

        assertEquals("O professor já ministra uma disciplina.", exception.getMessage());
    }
}
