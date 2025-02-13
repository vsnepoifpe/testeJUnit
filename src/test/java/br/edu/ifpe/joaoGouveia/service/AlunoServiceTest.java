package test.java.br.edu.ifpe.joaoGouveia.service;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoServiceTest {

    private AlunoRepositorio alunoRepositorio;
    private AlunoService alunoService;

    @BeforeEach
    public void config() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    @Test
    public void matricularAlunoEmDisciplina() {
        Aluno aluno = new Aluno("1", "João");
        alunoService.adicionar(aluno);
        alunoService.matricularAlunoEmDisciplina("1", "Português");

        assertTrue(aluno.getDisciplinas().contains("Português"));
    }

    @Test
    public void matricularAlunoEmDisciplina_DeveLancarExcecaoCasoAlunoNaoEncontrado() {
        assertThrows(NoSuchElementException.class, () ->
                alunoService.matricularAlunoEmDisciplina("10", "Matemática"));
    }

    @Test
    public void adicionar() {
        Aluno aluno = new Aluno("1", "João");
        alunoService.adicionar(aluno);
        Aluno alunoAdicionado = alunoRepositorio.buscarPorId("1");

        assertNotNull(alunoAdicionado);
        assertEquals("João", alunoAdicionado.getNome());
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoAlunoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                alunoService.adicionar(null));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoNomeAlunoNulo() {
        Aluno aluno = new Aluno("1", null);
        assertThrows(IllegalArgumentException.class, () ->
                alunoService.adicionar(aluno));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoNomeAlunoEmBranco() {
        Aluno aluno = new Aluno("1", "");
        assertThrows(IllegalArgumentException.class, () ->
                alunoService.adicionar(aluno));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoIdAlunoNulo() {
        Aluno aluno = new Aluno(null, "João");
        assertThrows(IllegalArgumentException.class, () ->
                alunoService.adicionar(aluno));
    }

    @Test
    public void adicionar_DeveLancarExcecaoCasoIdAlunoEmBranco() {
        Aluno aluno = new Aluno("", "João");
        assertThrows(IllegalArgumentException.class, () ->
                alunoService.adicionar(aluno));
    }
}
