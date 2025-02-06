package br.edu.ifpe.albertalvin.service;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class AlunoServiceTest
{
    private static AlunoRepositorio repositorio;
    private AlunoService service;

    @BeforeAll
    public static void setUpClass()
    {
        repositorio = new AlunoRepositorio();
        repositorio.salvar(new Aluno("1", "Helmuth Voss"));
        repositorio.salvar(new Aluno("2", "Franz Bonaparta"));
        repositorio.salvar(new Aluno("3", "Jakub Farobek"));
        repositorio.salvar(new Aluno("4", "Emil Sebe"));
    }

    @BeforeEach
    void setUp()
    {
        service = new AlunoService(repositorio);
    }

    @Test
    void matricularAlunoEmDisciplina()
    {
        String id = "1";
        String dis = "Artes I";
        service.matricularAlunoEmDisciplina(id, dis);

        assertTrue(repositorio.buscarPorId(id).getDisciplinas().stream().anyMatch((d) -> d.equals(dis)));
    }

    @Test
    void matricularAlunoEmDisciplina_ThrowsNoSuchElementException_WhenAlunoDoesntExist()
    {
        String id = "X";
        String dis = "Artes I";
        assertThrows(NoSuchElementException.class, () -> service.matricularAlunoEmDisciplina(id, dis));
    }

    @Test
    void adicionar()
    {
        Aluno a = new Aluno("5", "Johan");
        service.adicionar(a);

        var newAluno = repositorio.buscarPorId(a.getId());
        assertEquals(a.getId(), newAluno.getId());
        assertEquals(a.getNome(), newAluno.getNome());
        assertTrue(newAluno.getDisciplinas().isEmpty());
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenAlunoIsNull()
    {
        Aluno a = null;
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(a));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenNomeIsNull()
    {
        Aluno a = new Aluno("99", null);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(a));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenNomeIsBlank()
    {
        Aluno a = new Aluno("99", "");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(a));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenIdIsNull()
    {
        Aluno a = new Aluno(null, "Frankfurt");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(a));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenIdIsBlank()
    {
        Aluno a = new Aluno("", "Frankfurt");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(a));
    }
}