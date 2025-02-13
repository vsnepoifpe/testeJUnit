package test.br.edu.ifpe.sevice;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;

public class AlunoServiceTest {

        private AlunoService alunoService;
        private AlunoRepositorio alunoRepositorio;

        @BeforeEach
        public void setUp() {
            alunoRepositorio = new AlunoRepositorio();
            alunoService = new AlunoService(alunoRepositorio);
        }

        @Test
        public void testMatricularAlunoEmDisciplinaAlunoNaoEncontrado() {
            assertThrows(NoSuchElementException.class, () -> {
                alunoService.matricularAlunoEmDisciplina("1", "Matematica");
            });
        }

        @Test
        public void testAdicionarAlunoInvalido() {
            Aluno aluno = new Aluno(null, null);
            assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
        }

        @Test
        public void testAdicionarAlunoValido() {
            Aluno aluno = new Aluno("1", "João");
            alunoService.adicionar(aluno);
            assertTrue(alunoRepositorio.buscarPorId("1") != null);
        }

        @Test
        public void testMatricularAlunoEmDisciplina() {
            Aluno aluno = new Aluno("1", "João");
            alunoService.adicionar(aluno);
            alunoService.matricularAlunoEmDisciplina("1", "Matematica");
            assertTrue(aluno.getDisciplinas().contains("Matematica"));
        }
}

