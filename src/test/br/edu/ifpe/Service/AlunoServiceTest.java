package test.br.edu.ifpe.Service;

import main.br.edu.ifpe.model.entidades.Aluno;
import main.br.edu.ifpe.model.repositorio.AlunoRepositorio;
import main.br.edu.ifpe.service.AlunoService;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoServiceTest {

    private static AlunoRepositorio repositorio = new AlunoRepositorio();
    private static AlunoService service = new AlunoService(repositorio);


    @Test
    public void adicionar_aluno_success(){
        Aluno a2 = new Aluno("2","Victor");
        service.adicionar(a2);
        var alunoBuscado = repositorio.buscarPorId(a2.getId());
        assertEquals(alunoBuscado.getId(), a2.getId());
    }

    @Test
    public void adicionar_aluno_null(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(null);
        });
    }

    @Test
    public void adicionar_aluno_nome_null(){
        Aluno aluno = new Aluno("3", null);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void adicionar_aluno_nome_vazio(){
        Aluno aluno = new Aluno("4", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void adicionar_aluno_nome_branco(){
        Aluno aluno = new Aluno("5", "   ");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void adicionar_aluno_id_null(){
        Aluno aluno = new Aluno(null, "Neymar");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void adicionar_aluno_id_vazio(){
        Aluno aluno = new Aluno("", "Carlos");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void adicionar_aluno_id_branco(){
        Aluno aluno = new Aluno("   ", "Mohamed");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionar(aluno);
        });
    }

    @Test
    public void matricula_aluno_success(){
        Aluno a6 = new Aluno("6","Joao");
        repositorio.salvar(a6);
        var disciplina = "Mortal pra frente";
        service.matricularAlunoEmDisciplina(a6.getId(), disciplina);
        assertTrue(a6.getDisciplinas().contains(disciplina));
    }

    @Test
    public void matricula_aluno_fail_aluno_null(){
        var idAluno = "-1";
        var disciplina = "Mortal pra frente";
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class,()->{
            service.matricularAlunoEmDisciplina(idAluno,disciplina);
        });
        assertEquals(thrown.getMessage(),"Aluno não encontrado.");

        }

    @Test
    public void matricula_aluno_fail_aluno_limite_disciplinas(){
        var a7 = new Aluno("7","Joao");
        repositorio.salvar(a7);
        var id = a7.getId();
        var d1 = "Mortal de lado";
        var d2 = "Mortal de banda";
        var d3 = "Clonagem de cartao visa";
        var d4 = "Bater laje";
        var d5 = "Desbater laje";

        service.matricularAlunoEmDisciplina(id,d1);
        service.matricularAlunoEmDisciplina(id,d2);
        service.matricularAlunoEmDisciplina(id,d3);
        service.matricularAlunoEmDisciplina(id,d4);
        service.matricularAlunoEmDisciplina(id,d5);
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            service.matricularAlunoEmDisciplina(id,"Matematica");

        });

        assertEquals(thrown.getMessage(),"O aluno já está matriculado no máximo de 5 disciplinas.");

    }



}
