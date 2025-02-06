package br.edu.ifpe.model.albertalvin.entidades;

import br.edu.ifpe.model.entidades.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfessorTest
{
    private Professor professor;

    @BeforeEach
    void setUp()
    {
        professor = new Professor("1", "Franz Bonaparta");
    }

    @Test
    void atribuirDisciplina()
    {
        final String dis = "Artes";
        professor.atribuirDisciplina(dis);

        assertEquals(dis, professor.getDisciplina());
    }

    @Test
    void atribuirDisciplina_ThrowsIllegalStateException_WhenItAlreadyHasADisciplina()
    {
        professor.atribuirDisciplina("Artes I");
        assertThrows(IllegalStateException.class, () -> professor.atribuirDisciplina("Artes II"));
    }
}