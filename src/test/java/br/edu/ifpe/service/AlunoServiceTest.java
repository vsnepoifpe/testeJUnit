package test.java.br.edu.ifpe.service;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.br.edu.ifpe.model.entidades.Aluno;
import main.java.br.edu.ifpe.model.repositorio.AlunoRepositorio;
import main.java.br.edu.ifpe.service.AlunoService;

public class AlunoServiceTest {

        private AlunoService alunoService;
        private AlunoRepositorio alunoRepositorio;

        //Constantes
        private static final String ID_ALUNO = "1";
        private static final String NOME_ALUNO = "João";
        private static final String DISCIPLINA = "Testes de Software";
        private static final String ERROR_MESSAGE = "Aluno Inválido";

        @BeforeEach
        public void setUp() {
            alunoRepositorio = new AlunoRepositorio();
            alunoService = new AlunoService(alunoRepositorio);
        }

        //Cenário de teste: Matricular aluno em disciplina
        @Test
        public void testMatricularAlunoEmDisciplinaSuccess() {
            Aluno aluno = new Aluno(ID_ALUNO, NOME_ALUNO);
            alunoService.adicionar(aluno);
            alunoService.matricularAlunoEmDisciplina(ID_ALUNO, DISCIPLINA);
            assertTrue(aluno.getDisciplinas().contains(DISCIPLINA));
        }
        @Test
        public void testMatricularAlunoEmDisciplinaFailureAlunoNaoEncontrado() {
            NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
                alunoService.matricularAlunoEmDisciplina(ID_ALUNO, DISCIPLINA);
            });
            assertTrue(exception.getMessage().contains("Aluno não encontrado."));
        }

        //Cenário de teste: Adicionar aluno
        @Test
        public void testAdicionarAlunoSuccess() {
            Aluno aluno = new Aluno(ID_ALUNO, NOME_ALUNO);
            alunoService.adicionar(aluno);
            assertTrue(alunoRepositorio.buscarPorId(ID_ALUNO) != null);
        }
        @Test
        public void testAdicionarAlunoFailureAlunoNulo() {
            Aluno aluno = new Aluno(null, null);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
            assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
        }
        @Test
        public void testAdicionarAlunoFailureNomeNulo() {
            Aluno aluno = new Aluno(ID_ALUNO, null);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
            assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
        }
        @Test
        public void testAdicionarAlunoFailureNomeVazio() {
            Aluno aluno = new Aluno(ID_ALUNO, "");
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
            assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
        }
        @Test
        public void testAdicionarAlunoFailureIdNulo() {
            Aluno aluno = new Aluno(null, NOME_ALUNO);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
            assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
        }
        @Test
        public void testAdicionarAlunoFailureIdVazio() {
            Aluno aluno = new Aluno("", NOME_ALUNO);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                alunoService.adicionar(aluno);
            });
            assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
        }
}

