package test.java.br.edu.ifpe.service;

import main.java.br.edu.ifpe.model.entitades.Aluno;
import main.java.br.edu.ifpe.model.repositorio.AlunoRepositorio;
import main.java.br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class AlunoServiceTest {

    private AlunoRepositorio alunoRepositorio;
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    // Testa se um aluno pode ser matriculado em uma disciplina com sucesso
    @Test
    void testMatricularAlunoEmDisciplina() {
        Aluno aluno = new Aluno("1", "João");
        alunoRepositorio.salvar(aluno);

        alunoService.matricularAlunoEmDisciplina("1", "Matemática");

        assertTrue(aluno.getDisciplinas().contains("Matemática"));
    }

    // Testa se uma exceção é lançada ao tentar matricular um aluno que não existe
    @Test
    void testMatricularAlunoEmDisciplinaAlunoNaoEncontrado() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            alunoService.matricularAlunoEmDisciplina("2", "Matemática");
        });

        assertEquals("Aluno não encontrado.", exception.getMessage());
    }

    // Testa se um aluno válido pode ser adicionado com sucesso
    @Test
    void testAdicionarAlunoValido() {
        Aluno aluno = new Aluno("1", "João");

        alunoService.adicionar(aluno);

        assertEquals(aluno, alunoRepositorio.buscarPorId("1"));
    }

    // Testa se uma exceção é lançada ao tentar adicionar um aluno com ID nulo
    @Test
    void testAdicionarAlunoInvalido() {
        Aluno aluno = new Aluno(null, "João");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });

        assertEquals("Professor Inválido", exception.getMessage());
    }

    // Testa se uma exceção é lançada ao tentar adicionar um aluno com nome em branco
    @Test
    void testAdicionarAlunoNomeEmBranco() {
        Aluno aluno = new Aluno("1", "");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });

        assertEquals("Professor Inválido", exception.getMessage());
    }

    // Testa se uma exceção é lançada ao tentar adicionar um aluno com ID em branco
    @Test
    void testAdicionarAlunoIdEmBranco() {
        Aluno aluno = new Aluno("", "João");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });

        assertEquals("Professor Inválido", exception.getMessage());
    }
}