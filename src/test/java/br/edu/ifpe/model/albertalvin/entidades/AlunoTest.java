package br.edu.ifpe.model.albertalvin.entidades;

import br.edu.ifpe.model.entidades.Aluno;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest
{
    private static String[] disciplinas;
    private Aluno aluno;

    @BeforeAll
    public static void setUpClass()
    {
        disciplinas = new String[]{"Biologia", "Matematica", "Estatistica", "Necromancia"};
    }

    @BeforeEach
    void setUp()
    {
        aluno = new Aluno("1", "Franz Bonaparta");
        Arrays.stream(disciplinas).forEach(d -> aluno.matricularEmDisciplina(d));
    }

    @Test
    void matricularEmDisciplina()
    {
        var dis = "Astrologia";
        aluno.matricularEmDisciplina(dis);

        assertTrue(aluno.getDisciplinas().stream().anyMatch((d) -> d.equals(dis)));
    }

    @Test
    void matricularEmDisciplina_ThrowsIllegalStateException_WhenDisciplinasIsGreaterThanFive()
    {
        aluno.matricularEmDisciplina("Astrologia I");
        assertThrows(IllegalStateException.class, () -> aluno.matricularEmDisciplina("Astrologia II"));
    }
}