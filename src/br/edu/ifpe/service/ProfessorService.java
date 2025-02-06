package br.edu.ifpe.service;

import java.util.NoSuchElementException;

import br.edu.ifpe.model.entidades.Professor;
import br.edu.ifpe.model.repositorio.ProfessorRepositorio;

public class ProfessorService {
	private ProfessorRepositorio professorRepository;

	public ProfessorService(ProfessorRepositorio professorRepository) {
		this.professorRepository = professorRepository;
	}

	public void atribuirDisciplina(String professorId, String disciplina) {
		Professor professor = professorRepository.buscarPorId(professorId);
		if (professor == null) {
			throw new NoSuchElementException("Professor não encontrado.");
		}
		professor.atribuirDisciplina(disciplina);
	}

	public void adicionar(Professor professor) {

		if (professor == null || professor.getNome() == null || professor.getNome().isBlank()
				|| professor.getId() == null || professor.getId().isBlank()) {

			throw new IllegalArgumentException("Professor Inválido");
		}

		professorRepository.salvar(professor);
	}
}
