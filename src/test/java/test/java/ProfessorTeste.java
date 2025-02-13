/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.java.test.java;

import br.edu.ifpe.model.entidades.Professor;
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
class ProfessorTeste {

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor("1", "Músico");
    }

    @Test
    void atribuirDisciplinaCerto() {
        professor.atribuirDisciplina("Notas Musicais");
        assertEquals("Notas Musicais", professor.getDisciplina());
    }

    @Test
    void atribuirDisciplinaProfessorJaTem() {
        professor.atribuirDisciplina("Notas Musicai");
        assertThrows(IllegalStateException.class, () -> professor.atribuirDisciplina("Canto"));
    }