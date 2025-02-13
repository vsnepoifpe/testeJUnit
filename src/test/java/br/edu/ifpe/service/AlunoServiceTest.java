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
    void configurar() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    @Test
    void testarAdicaoDeAlunoComSucesso() {
        Aluno aluno = new Aluno("2", "Julia");
        alunoService.adicionar(aluno);

        Aluno alunoRetornado = alunoRepositorio.buscarPorId("2");
        assertNotNull(alunoRetornado);
        assertEquals("Julia", alunoRetornado.getNome());
    }

    @Test
    void testarAdicaoDeAlunoComNomeVazio() {
        Aluno aluno = new Aluno("3", "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });
        assertEquals("Aluno Inválido", exception.getMessage());
    }

    @Test
    void testarAdicaoDeAlunoComIdVazio() {
        Aluno aluno = new Aluno("", "Carlos");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });
        assertEquals("Aluno Inválido", exception.getMessage());
    }

    @Test
    void testarMatriculaDeAlunoEmDisciplinaComSucesso() {
        Aluno aluno = new Aluno("1", "Gustavo");
        alunoRepositorio.salvar(aluno);

        alunoService.matricularAlunoEmDisciplina("1", "Testes");
        assertTrue(aluno.getDisciplinas().contains("Testes"));
    }

    @Test
    void testarMatriculaDeAlunoNaoEncontrado() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            alunoService.matricularAlunoEmDisciplina("10152", "Inteligencia Artificial");
        });
        assertEquals("Aluno não encontrado.", exception.getMessage());
    }

    @Test
    void testarAdicaoDeAlunoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(null);
        });
        assertEquals("Aluno Inválido", exception.getMessage());
    }
}