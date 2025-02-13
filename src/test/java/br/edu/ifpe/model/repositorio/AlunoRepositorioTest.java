package test.java.br.edu.ifpe.model.repositorio;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlunoRepositorioTest {

    private AlunoRepositorio alunoRepositorio;

    @BeforeEach
    public void config() {
        alunoRepositorio = new AlunoRepositorio();
        alunoRepositorio.salvar(new Aluno("1", "João"));
    }

    @Test
    public void salvarAluno() {
        Aluno aluno = new Aluno("2", "Vilmar");
        alunoRepositorio.salvar(aluno);
        Aluno alunoSalvo = alunoRepositorio.buscarPorId("2");

        assertNotNull(alunoSalvo);
        assertEquals("2", alunoSalvo.getId());
        assertEquals("Vilmar", alunoSalvo.getNome());
    }

    @Test
    public void buscarPorId() {
        Aluno alunoSalvo = alunoRepositorio.buscarPorId("1");

        assertNotNull(alunoSalvo);
        assertEquals("1", alunoSalvo.getId());
        assertEquals("João", alunoSalvo.getNome());
    }
}
