Med Aziz Hadj Ali
        package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        Set<Etudiant> etudiants = new HashSet<>();
        Equipe equipe1 = new Equipe(1, "Equipe A", Niveau.MOYEN, etudiants, null);
        Equipe equipe2 = new Equipe(2, "Equipe B", Niveau.HAUT, etudiants, null);

        when(equipeRepository.findAll()).thenReturn(List.of(equipe1, equipe2));

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertEquals(2, result.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        Set<Etudiant> etudiants = new HashSet<>();
        Equipe equipe = new Equipe("Equipe A", Niveau.MOYEN, etudiants, null);

        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);

        assertNotNull(result);
        assertEquals("Equipe A", result.getNomEquipe());
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Integer equipeId = 1;
        Set<Etudiant> etudiants = new HashSet<>();
        Equipe equipe = new Equipe(equipeId, "Equipe A", Niveau.MOYEN, etudiants, null);

        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(equipeId);

        assertNotNull(result);
        assertEquals(equipeId, result.getIdEquipe());
        verify(equipeRepository, times(1)).findById(equipeId);
    }

    @Test
    void testRemoveEquipe() {
        Integer equipeId = 1;
        Set<Etudiant> etudiants = new HashSet<>();
        Equipe equipe = new Equipe(equipeId, "Equipe A", Niveau.MOYEN, etudiants, null);
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        equipeService.removeEquipe(equipeId);

        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testAddEtudiantToEquipe() {
        Integer equipeId = 1;
        Equipe equipe = new Equipe(equipeId, "Equipe A", Niveau.MOYEN, new HashSet<>(), null);
        Etudiant etudiant = new Etudiant(); // Créez une instance d'étudiant

        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        equipeService.addEtudiantToEquipe(equipeId, etudiant);

        assertTrue(equipe.getEtudiants().contains(etudiant));
        verify(equipeRepository, times(1)).save(equipe);
    }
}