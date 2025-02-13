package test.java.br.edu.ifpe.service;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;

class AlunoServiceTest {

    private AlunoRepositorio alunoRepositorio;
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    @Test
    void deveMatricularAlunoEmDisciplina() {
        Aluno aluno = new Aluno("123", "Maria");
        alunoRepositorio.salvar(aluno);

        alunoService.matricularAlunoEmDisciplina("123", "Biologia");

        assertTrue(aluno.getDisciplinas().contains("Biologia"));
    }

    @Test
    void deveLancarExcecaoSeAlunoNaoExistir() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            alunoService.matricularAlunoEmDisciplina("999", "Matemática");
        });

        assertEquals("Aluno não encontrado.", exception.getMessage());
    }

    @Test
    void deveAdicionarAlunoComSucesso() {
        Aluno aluno = new Aluno("123", "Carlos");

        alunoService.adicionar(aluno);

        Aluno alunoSalvo = alunoRepositorio.buscarPorId("123");
        assertNotNull(alunoSalvo);
        assertEquals("Carlos", alunoSalvo.getNome());
    }

    @Test
    void deveLancarExcecaoAoAdicionarAlunoInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(null);
        });

        assertEquals("Professor Inválido", exception.getMessage());

        Aluno alunoSemNome = new Aluno("123", "");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(alunoSemNome);
        });

        assertEquals("Professor Inválido", exception.getMessage());
    }
}
