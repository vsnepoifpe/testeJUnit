/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package test.java.test.java;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author auri
 */

class AlunoServiceTeste {

    private AlunoRepositorio repositorio;
    private AlunoService service;

    @BeforeEach
    void setUp() {
        repositorio = new AlunoRepositorio();
        service = new AlunoService(repositorio);
    }

    @Test
    void MatricularAlunoCerto() {
        Aluno aluno = new Aluno("1", "Celina");
        repositorio.salvar(aluno);

        service.matricularAlunoEmDisciplina("1", "Notas Musicais");

        assertTrue(aluno.getDisciplinas().contains("Notas Musicais"));
    }

  @Test
    void MatricularAlunoNaoEncontrado() {
        assertThrows(NoSuchElementException.class, () ->
                service.matricularAlunoEmDisciplina("2", "Piano"));
    }

    @Test
    void AdicionarAlunoCerto() {
        Aluno aluno = new Aluno("2", "Lúcia");
        service.adicionar(aluno);

        Aluno alunoSalvo = repositorio.buscarPorId("2");
        assertNotNull(alunoSalvo);
        assertEquals("Lúcia", alunoSalvo.getNome());
    }

    @Test
    void AdicionarAlunoNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(null));
    }

    @Test
    void AdicionarAlunoComNomeNulo() {
        Aluno alunoNomeNulo = new Aluno("3", null);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(alunoNomeNulo));

    }
    
    @Test
    void AdicionarAlunoComNomeBranco() {
        Aluno alunoNomeBranco = new Aluno("3", " ");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(alunoNomeBranco));

    }

    @Test
    void AdicionarAlunoComIdNulo() {
        Aluno alunoIdNulo = new Aluno(null, "Lina");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(alunoIdNulo));

    }
    
     @Test
    void AdicionarAlunoComIdBranco() {
      Aluno alunoIdBranco = new Aluno(" ", "Luna");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(alunoIdBranco));

    }
   
}
