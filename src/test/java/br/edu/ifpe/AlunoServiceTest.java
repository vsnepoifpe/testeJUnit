package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class AlunoServiceTest {
    private AlunoRepositorio alunoRepositorio;
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        alunoRepositorio = new AlunoRepositorio();
        alunoService = new AlunoService(alunoRepositorio);
    }

    @Test
    void matricularAlunoComSucesso() {
        Aluno aluno = new Aluno("1", "João");
        alunoRepositorio.salvar(aluno);
        alunoService.matricularAlunoEmDisciplina("1", "Matemática");
        assertTrue(aluno.getDisciplinas().contains("Matemática"));
    }

    @Test
    void lancarExcecaoAoMatricularAlunoInexistente() {
        assertThrows(NoSuchElementException.class, () -> alunoService.matricularAlunoEmDisciplina("999", "Matemática"));
    }

    @Test
    void adicionarAlunoComSucesso() {
        Aluno aluno = new Aluno("2", "Maria");
        alunoService.adicionar(aluno);
        assertEquals(aluno, alunoRepositorio.buscarPorId("2"));
    }

    @Test
    void lancarExcecaoAoAdicionarAlunoComNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(new Aluno("3", "")));
    }

    @Test
    void lancarExcecaoAoAdicionarAlunoComIdVazio() {
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(new Aluno("", "Carlos")));
    }

    @Test
    void lancarExcecaoAoAdicionarAlunoNulo() {
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(null));
    }

    @Test
    void adicionarAlunoComEspacosNoNome() {
        Aluno aluno = new Aluno("4", "   ");
        assertThrows(IllegalArgumentException.class, () -> alunoService.adicionar(aluno));
    }
}

