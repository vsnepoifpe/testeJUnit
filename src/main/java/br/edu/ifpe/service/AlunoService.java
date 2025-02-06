package br.edu.ifpe.service;

import java.util.NoSuchElementException;

import br.edu.ifpe.model.entidades.Aluno;
import br.edu.ifpe.model.repositorio.AlunoRepositorio;

public class AlunoService
{
    private AlunoRepositorio alunoRepository;

    public AlunoService(AlunoRepositorio alunoRepository)
    {
        this.alunoRepository = alunoRepository;
    }

    public void matricularAlunoEmDisciplina(String alunoId, String disciplina)
    {
        Aluno aluno = alunoRepository.buscarPorId(alunoId);
        if (aluno == null)
        {
            throw new NoSuchElementException("Aluno não encontrado.");
        }
        aluno.matricularEmDisciplina(disciplina);
    }

    public void adicionar(Aluno aluno)
    {
        if (aluno == null || aluno.getNome() == null || aluno.getNome().isBlank() || aluno.getId() == null
                || aluno.getId().isBlank())
        {

            throw new IllegalArgumentException("Professor Inválido");
        }
        alunoRepository.salvar(aluno);
    }
}
