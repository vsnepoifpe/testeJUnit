package test.java.br.edu.ifpe;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoRepositorioTest {
    private AlunoRepositorio alunoRepositorio;

    @BeforeEach
    void setUp() {
        alunoRepositorio = new AlunoRepositorio();
    }

    @Test
    void salvarAlunoComSucesso() {
        Aluno aluno = new Aluno("1", "João");
        alunoRepositorio.salvar(aluno);
        assertEquals(aluno, alunoRepositorio.buscarPorId("1"));
    }

    @Test
    void buscarAlunoInexistente() {
        assertNull(alunoRepositorio.buscarPorId("999"));
    }

    @Test
    void sobrescreverAlunoComMesmoId() {
        Aluno aluno1 = new Aluno("1", "João");
        Aluno aluno2 = new Aluno("1", "Maria");
        alunoRepositorio.salvar(aluno1);
        alunoRepositorio.salvar(aluno2);
        assertEquals(aluno2, alunoRepositorio.buscarPorId("1"));
    }
}

