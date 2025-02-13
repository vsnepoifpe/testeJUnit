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
    void MatricularAlunoEmDisciplinaSucesso() {
        Aluno aluno = new Aluno("1", "Raul");
        alunoRepositorio.salvar(aluno);

        alunoService.matricularAlunoEmDisciplina("1", "LPOO");
        assertTrue(aluno.getDisciplinas().contains("LPOO"));
    }

    @Test
    void MatriculaDisciplinaAlunoNaoEncontrado() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            alunoService.matricularAlunoEmDisciplina("nonexistent", "Orientados a aspectos");
        });
        assertEquals("Aluno não encontrado.", exception.getMessage());
    }

    @Test
    void AdicionarAlunoValido() {
        Aluno aluno = new Aluno("2", "Guga");
        alunoService.adicionar(aluno);

        Aluno alunoRetornado = alunoRepositorio.buscarPorId("2");
        assertNotNull(alunoRetornado);
        assertEquals("Guga", alunoRetornado.getNome());
    }

    @Test
    void AdicionarAlunoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(null);
        });
        assertEquals("Aluno Inválido", exception.getMessage());
    }

    @Test
    void AdicionarAlunoNomeVazio() {
        Aluno aluno = new Aluno("3", "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.adicionar(aluno);
        });
        assertEquals("Aluno Inválido", exception.getMessage());
    }

}
