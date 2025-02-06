package br.edu.ifpe;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import br.edu.ifpe.service.AlunoService;
import br.edu.ifpe.service.ProfessorService;

public class Main {
	public static void main(String[] args) {
        ProfessorRepositorio professorRepo = new ProfessorRepositorio();
        AlunoRepositorio alunoRepo = new AlunoRepositorio();
        ProfessorService professorService = new ProfessorService(professorRepo);
        AlunoService alunoService = new AlunoService(alunoRepo);

        // Criando professor e aluno
        Professor professor = new Professor("P1", "Dr. Silva");
        Aluno aluno = new Aluno("A1", "João");

        // Salvando no repositório
        professorService.adicionar(professor);
        alunoService.adicionar(aluno);

        // Atribuindo disciplina ao professor
        professorService.atribuirDisciplina("P1", "Matemática");
        System.out.println(professor.getNome() + " ministra " + professor.getDisciplina());

        // Matriculando aluno em disciplinas
        alunoService.matricularAlunoEmDisciplina("A1", "Matemática");
        System.out.println(aluno.getNome() + " está matriculado em: " + aluno.getDisciplinas());
    }
}
