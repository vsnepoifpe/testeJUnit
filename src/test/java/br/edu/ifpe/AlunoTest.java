package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {
    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("1", "João");
    }

    @Test
    void matricularEmDisciplinaComSucesso() {
        aluno.matricularEmDisciplina("Matemática");
        assertTrue(aluno.getDisciplinas().contains("Matemática"));
    }

    @Test
    void matricularEmVariasDisciplinasComSucesso() {
        aluno.matricularEmDisciplina("Matemática");
        aluno.matricularEmDisciplina("História");
        aluno.matricularEmDisciplina("Física");
        assertEquals(3, aluno.getDisciplinas().size());
    }

    @Test
    void naoPodeMatricularMaisDeCincoDisciplinas() {
        aluno.matricularEmDisciplina("Matemática");
        aluno.matricularEmDisciplina("História");
        aluno.matricularEmDisciplina("Geografia");
        aluno.matricularEmDisciplina("Física");
        aluno.matricularEmDisciplina("Química");
        assertThrows(IllegalStateException.class, () -> aluno.matricularEmDisciplina("Biologia"));
    }

    @Test
    void listaDeDisciplinasInicialmenteVazia() {
        assertEquals(0, aluno.getDisciplinas().size());
    }
}

