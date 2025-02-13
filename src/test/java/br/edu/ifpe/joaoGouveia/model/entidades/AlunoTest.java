package test.java.br.edu.ifpe.joaoGouveia.model.entidades;

import br.edu.ifpe.model.entidades.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    public void config() {
        aluno = new Aluno("1", "João");
    }

    @Test
    public void matricularEmDisciplina() {
        String disciplina = "Biologia";
        aluno.matricularEmDisciplina(disciplina);

        assertTrue(aluno.getDisciplinas().contains(disciplina));
    }

    @Test
    public void matricularEmDisciplina_deveLancarExcecaoAoUltrapassarLimiteDeCincoDisciplinas() {
        aluno.matricularEmDisciplina("Matemática");
        aluno.matricularEmDisciplina("Português");
        aluno.matricularEmDisciplina("Química");
        aluno.matricularEmDisciplina("História");
        aluno.matricularEmDisciplina("Geografia");

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                aluno.matricularEmDisciplina("Filosofia")
        );

        assertEquals("O aluno já está matriculado no máximo de 5 disciplinas.", thrown.getMessage());
    }
}
