package br.edu.ifpe.albertalvin.service;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorServiceTest
{
    private static ProfessorRepositorio repositorio;
    private ProfessorService service;

    @BeforeAll
    public static void setUpClass()
    {
        repositorio = new ProfessorRepositorio();
        repositorio.salvar(new Professor("1", "Helmuth Voss"));
        repositorio.salvar(new Professor("2", "Franz Bonaparta"));
        repositorio.salvar(new Professor("3", "Jakub Farobek"));
        repositorio.salvar(new Professor("4", "Emil Sebe"));
    }

    @BeforeEach
    void setUp()
    {
        service = new ProfessorService(repositorio);
    }

    @Test
    void adicionar()
    {
        Professor p = new Professor("5", "Johan");
        service.adicionar(p);

        var newProf = repositorio.buscarPorId(p.getId());
        assertEquals(p.getId(), newProf.getId());
        assertEquals(p.getNome(), newProf.getNome());
        assertNull(p.getDisciplina());
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenProfessorIsNull()
    {
        Professor p = null;
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(p));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenNomeIsNull()
    {
        Professor p = new Professor("99", null);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(p));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenNomeIsBlank()
    {
        Professor p = new Professor("99", "");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(p));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenIdIsNull()
    {
        Professor p = new Professor(null, "Frankfurt");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(p));
    }

    @Test
    void adicionar_ThrowsIllegalArgumentException_WhenIdIsBlank()
    {
        Professor p = new Professor("", "Frankfurt");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(p));
    }

    @Test
    void atribuirDisciplina()
    {
        String id = "1";
        String dis = "Artes I";
        service.atribuirDisciplina(id, dis);

        assertEquals(dis, repositorio.buscarPorId(id).getDisciplina());
    }

    @Test
    void atribuirDisciplina_ThrowsNoSuchElementException_WhenProfessorDoesntExist()
    {
        String id = "X";
        String dis = "Artes I";
        assertThrows(NoSuchElementException.class, () -> service.atribuirDisciplina(id, dis));
    }
}
