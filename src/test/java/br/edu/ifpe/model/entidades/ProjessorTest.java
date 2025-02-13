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
        professor = new Professor("1", "Ramide");
    }
    
    @Test
    void AtribuirDisciplinaJaMinistrada() {
        professor.atribuirDisciplina("Redes");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            professor.atribuirDisciplina("Algoritmos");
        });

        assertEquals("O professor já ministra uma disciplina.", exception.getMessage());
    }

    @Test
    void AtribuirDisciplinaSucesso() {
        professor.atribuirDisciplina("Redes");
        assertEquals("Redes", professor.getDisciplina());
    }

}