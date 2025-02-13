package test.java.br.edu.ifpe.joaoGouveia.model.entidades;

import br.edu.ifpe.model.entidades.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfessorTest {

    private Professor professor;

    @BeforeEach
    public void config() {
        professor = new Professor("1", "Vilmar");
    }

    @Test
    public void atribuirDisciplina() {
        professor.atribuirDisciplina("Português");
        assertEquals("Português", professor.getDisciplina());
    }

    @Test
    public void atribuirDisciplina_DeveLancarExcecaoAoAtribuirOutraDisciplina() {
        professor.atribuirDisciplina("Física");
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            professor.atribuirDisciplina("Geografia");
        });
        assertEquals("O professor já ministra uma disciplina.", thrown.getMessage());
    }
}
