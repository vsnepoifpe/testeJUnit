package test.java.br.edu.ifpe.model.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;

class AlunoRepositorioTest {

    private AlunoRepositorio alunoRepositorio;

    @BeforeEach
    void setUp() {
        alunoRepositorio = new AlunoRepositorio();
    }

    @Test
    void deveSalvarAlunoNoRepositorio() {
        Aluno aluno = new Aluno("123", "João Silva");
        alunoRepositorio.salvar(aluno);

        Aluno alunoSalvo = alunoRepositorio.buscarPorId("123");
        assertNotNull(alunoSalvo);
        assertEquals("João Silva", alunoSalvo.getNome());
    }

    @Test
    void deveRetornarNuloSeAlunoNaoExistir() {
        Aluno aluno = alunoRepositorio.buscarPorId("999");
        assertNull(aluno);
    }
}
