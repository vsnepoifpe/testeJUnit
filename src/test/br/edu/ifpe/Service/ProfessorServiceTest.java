package test.br.edu.ifpe.Service;

import main.br.edu.ifpe.model.entidades.Professor;
import main.br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import main.br.edu.ifpe.service.ProfessorService;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorServiceTest {

    private static ProfessorRepositorio repositorio = new ProfessorRepositorio();
    private static ProfessorService service = new ProfessorService(repositorio);

    @Test
    public void adicionar_professor_success() {
        Professor p1 = new Professor("1", "Carlos");
        service.adicionar(p1);
        var professorBuscado = repositorio.buscarPorId(p1.getId());
        assertEquals(professorBuscado.getId(), p1.getId());
    }

    @Test
    public void adicionar_professor_null() {
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(null));
    }

    @Test
    public void adicionar_professor_nome_null() {
        Professor professor = new Professor("2", null);
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void adicionar_professor_nome_vazio() {
        Professor professor = new Professor("3", "");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void adicionar_professor_nome_branco() {
        Professor professor = new Professor("4", "   ");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void adicionar_professor_id_null() {
        Professor professor = new Professor(null, "Ana");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void adicionar_professor_id_vazio() {
        Professor professor = new Professor("", "Bruno");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void adicionar_professor_id_branco() {
        Professor professor = new Professor("   ", "Mariana");
        assertThrows(IllegalArgumentException.class, () -> service.adicionar(professor));
    }

    @Test
    public void atribuir_disciplina_professor_success() {
        Professor p2 = new Professor("5", "Roberto");
        repositorio.salvar(p2);
        var disciplina = "Matematica";
        service.atribuirDisciplina(p2.getId(), disciplina);
        assertEquals(disciplina, p2.getDisciplina());
    }

    @Test
    public void atribuir_disciplina_professor_fail_professor_null() {
        var idProfessor = "-1";
        var disciplina = "Historia";
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            service.atribuirDisciplina(idProfessor, disciplina);
        });
        assertEquals("Professor não encontrado.", thrown.getMessage());
    }

    @Test
    public void atribuir_disciplina_professor_fail_ja_tem_disciplina() {
        Professor p3 = new Professor("6", "Fernando");
        repositorio.salvar(p3);
        service.atribuirDisciplina(p3.getId(), "fisica");
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            service.atribuirDisciplina(p3.getId(), "Química");
        });
        assertEquals("O professor já ministra uma disciplina.", thrown.getMessage());
    }
}
