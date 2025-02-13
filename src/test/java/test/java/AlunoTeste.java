/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.java.test.java;
import br.edu.ifpe.model.entidades.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author auri
 */

class AlunoTeste {

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("1", "Melina");
    }

    @Test
    void matricularEmUmaDisciplina() {
        aluno.matricularEmDisciplina("Sons altos");
        assertTrue(aluno.getDisciplinas().contains("Sons altos"));
    }

    @Test
    void matricularEmDuasDisciplinas() {
        aluno.matricularEmDisciplina("Sons altos");
        aluno.matricularEmDisciplina("Música clássica");
        
        assertTrue(aluno.getDisciplinas().contains("Sons altos"));
        assertTrue(aluno.getDisciplinas().contains("Música clássica"));
    }

    @Test
    void matricularEmTresDisciplinas() {
        aluno.matricularEmDisciplina("Sons altos");
        aluno.matricularEmDisciplina("Música clássica");
        aluno.matricularEmDisciplina("Saxofone");

        assertTrue(aluno.getDisciplinas().contains("Sons altos"));
        assertTrue(aluno.getDisciplinas().contains("Música clássica"));
        assertTrue(aluno.getDisciplinas().contains("Saxofone"));
    }

    @Test
    void matricularEmQuatroDisciplinas() {
        aluno.matricularEmDisciplina("Sons altos");
        aluno.matricularEmDisciplina("Música clássica");
        aluno.matricularEmDisciplina("Saxofone");
        aluno.matricularEmDisciplina("Piano");

        assertEquals(4, aluno.getDisciplinas().size());
        assertTrue(aluno.getDisciplinas().contains("Sons altos"));
        assertTrue(aluno.getDisciplinas().contains("Música clássica"));
        assertTrue(aluno.getDisciplinas().contains("Saxofone"));
        assertTrue(aluno.getDisciplinas().contains("Piano"));
    }

    @Test
    void matricularEmCincoDisciplinas() {
        aluno.matricularEmDisciplina("Sons altos");
        aluno.matricularEmDisciplina("Música clássica");
        aluno.matricularEmDisciplina("Saxofone");
        aluno.matricularEmDisciplina("Piano");
        aluno.matricularEmDisciplina("Violino");

        assertEquals(5, aluno.getDisciplinas().size());
        assertTrue(aluno.getDisciplinas().contains("Sons altos"));
        assertTrue(aluno.getDisciplinas().contains("Música clássica"));
        assertTrue(aluno.getDisciplinas().contains("Saxofone"));
        assertTrue(aluno.getDisciplinas().contains("Piano"));
        assertTrue(aluno.getDisciplinas().contains("Violino"));
    }

    @Test
    void matricularEmMaisDeCincoDisciplinas() {
        aluno.matricularEmDisciplina("Sons altos");
        aluno.matricularEmDisciplina("Música clássica");
        aluno.matricularEmDisciplina("Saxofone");
        aluno.matricularEmDisciplina("Piano");
        aluno.matricularEmDisciplina("Violino");

        assertThrows(IllegalStateException.class, () -> aluno.matricularEmDisciplina("Canto"));
    }
}
