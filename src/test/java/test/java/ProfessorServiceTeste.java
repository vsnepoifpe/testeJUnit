/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.java.test.java;


import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.ProfessorService;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author auri
 */
class ProfessorServiceTeste {

    private ProfessorRepositorio repositorio;
    private ProfessorService service;

    @BeforeEach
    void setUp() {
        repositorio = new ProfessorRepositorio();
        service = new ProfessorService(repositorio);
    }

   @Test
    void atribuirDisciplinaCerto() {
    Professor professor = new Professor("1", "Músico");
    repositorio.salvar(professor);

    service.atribuirDisciplina("1", "Canto");

    assertEquals("Canto", professor.getDisciplina());
}

    @Test
    void atribuirDisciplinaProfessorNaoEncontrado() {
        assertThrows(NoSuchElementException.class, () ->
                service.atribuirDisciplina("2", "Piano"));
    }

    @Test
    void adicionarProfessorCerto() {
        Professor professor = new Professor("2", "Pianista");
        service.adicionar(professor);

        Professor professorSalvo = repositorio.buscarPorId("2");
        assertNotNull(professorSalvo);
        assertEquals("Pianista", professorSalvo.getNome());
    }

    @Test
    void adicionarProfessorNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(null));
    }

    @Test
    void adicionarProfessorComNomeNulo() {
        Professor professorNomeNulo = new Professor("3", null);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professorNomeNulo));
    }

    @Test
    void adicionarProfessorComNomeBranco() {
        Professor professorNomeBranco = new Professor("3", " ");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professorNomeBranco));
    }

    @Test
    void adicionarProfessorComIdNulo() {
        Professor professorIdNulo = new Professor(null, "Baixista");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professorIdNulo));
    }

    @Test
    void adicionarProfessorComIdBranco() {
        Professor professorIdBranco = new Professor(" ", "Baixista");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professorIdBranco));
    }
}
