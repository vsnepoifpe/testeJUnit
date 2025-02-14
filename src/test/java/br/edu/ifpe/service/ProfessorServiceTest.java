package test.java.br.edu.ifpe.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.br.edu.ifpe.model.entitades.Professor;
import main.java.br.edu.ifpe.model.repositorio.ProfessorRepositorio;
import main.java.br.edu.ifpe.service.ProfessorService;

public class ProfessorServiceTest {

	private ProfessorRepositorio professorRepositorio;
	private ProfessorService professorService;

	@BeforeEach
	public void setUp() {
		professorRepositorio = new ProfessorRepositorio() {
			// Implementação simples para testes
			private Map<String, Professor> data = new HashMap<>();

			@Override
			public Professor buscarPorId(String id) {
				return data.get(id);
			}

			@Override
			public void salvar(Professor professor) {
				data.put(professor.getId(), professor);
			}
		};
		professorService = new ProfessorService(professorRepositorio);
	}

	// Testa se uma exceção NoSuchElementException é lançada quando tenta atribuir uma disciplina a um professor não encontrado
	@Test
	public void testAtribuirDisciplinaProfessorNaoEncontrado() {
		assertThrows(NoSuchElementException.class, () -> {
			professorService.atribuirDisciplina("1", "Matemática");
		});
	}

	// Testa se uma disciplina é atribuída corretamente a um professor existente
	@Test
	public void testAtribuirDisciplina() {
		Professor professor = new Professor("1", "João");
		professorRepositorio.salvar(professor);

		professorService.atribuirDisciplina("1", "Matemática");

		assertEquals("Matemática", professor.getDisciplina());
	}

	// Testa se uma exceção IllegalArgumentException é lançada ao tentar adicionar um professor inválido
	@Test
	public void testAdicionarProfessorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			professorService.adicionar(null);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			professorService.adicionar(new Professor(null, "João"));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			professorService.adicionar(new Professor("1", null));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			professorService.adicionar(new Professor("", "João"));
		});

		assertThrows(IllegalArgumentException.class, () -> {
			professorService.adicionar(new Professor("1", ""));
		});
	}

	// Testa se um professor válido é adicionado corretamente ao repositório
	@Test
	public void testAdicionarProfessorValido() {
		Professor professor = new Professor("1", "João");
		professorService.adicionar(professor);

		assertEquals(professor, professorRepositorio.buscarPorId("1"));
	}
}