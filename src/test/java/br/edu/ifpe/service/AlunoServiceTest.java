package test.java.br.edu.ifpe.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;

public class AlunoServiceTest {

    private AlunoRepositorio alunoRepositorio;
    private AlunoService alunoService;

    @BeforeEach
    public void setUp() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    @Test
    public void testAdicionarAlunoValido() {
        Aluno aluno = new Aluno("A1", "Augusto");
        alunoService.adicionar(aluno);
        assertEquals(aluno, alunoRepositorio.buscarPorId("A1"));
    }

    @Test
    public void testAdicionarAlunoComIdEmBranco() {
        Aluno aluno = new Aluno("", "Augusto");
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(aluno));
    }

    @Test
    public void testAdicionarAlunoComNomeEmBranco() {
        Aluno aluno = new Aluno("A1", "");
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(aluno));
    }

    @Test
    public void testMatricularAlunoEmDisciplina() {
        Aluno aluno = new Aluno("A1", "Augusto");
        alunoService.adicionar(aluno);
        alunoService.matricularAlunoEmDisciplina("A1", "Matemática");
        assertTrue(aluno.getDisciplinas().contains("Matemática"));
    }

    @Test
    public void testMatricularAlunoEmMultiplasDisciplinas() {
        Aluno aluno = new Aluno("A1", "Augusto");
        alunoService.adicionar(aluno);
        alunoService.matricularAlunoEmDisciplina("A1", "Matemática");
        alunoService.matricularAlunoEmDisciplina("A1", "Física");
        assertTrue(aluno.getDisciplinas().contains("Matemática"));
        assertTrue(aluno.getDisciplinas().contains("Física"));
    }

    @Test
    public void testMatricularAlunoEmMaisDeCincoDisciplinas() {
        Aluno aluno = new Aluno("A1", "Augusto");
        alunoService.adicionar(aluno);
        alunoService.matricularAlunoEmDisciplina("A1", "Matemática");
        alunoService.matricularAlunoEmDisciplina("A1", "Física");
        alunoService.matricularAlunoEmDisciplina("A1", "Química");
        alunoService.matricularAlunoEmDisciplina("A1", "Biologia");
        alunoService.matricularAlunoEmDisciplina("A1", "História");
        assertThrows(IllegalStateException.class, () -> alunoService.matricularAlunoEmDisciplina("A1", "Geografia"));
    }

    @Test
    public void testMatricularAlunoNaoExistente() {
        assertThrows(NoSuchElementException.class, () -> alunoService.matricularAlunoEmDisciplina("A1", "Matemática"));
    }
}
