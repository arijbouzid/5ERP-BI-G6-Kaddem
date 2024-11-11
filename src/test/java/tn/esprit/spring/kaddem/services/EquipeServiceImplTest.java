package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {
    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private DepartementRepository departementRepository;
    @InjectMocks
    private EquipeServiceImpl equipeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllEquipes() {
        // Préparer les données
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Bouzid", Niveau.EXPERT));
        equipes.add(new Equipe("haythem", Niveau.EXPERT));
        // Simuler la méthode du repository
        when(equipeRepository.findAll()).thenReturn(equipes);
        // Appeler la méthode à tester
        List<Equipe> result = equipeServiceImpl.retrieveAllEquipes();
        // Vérifier le résultat
        assertEquals(2, result.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void addEquipe() {
        // Créer un exemple d'étudiant
        Equipe equipe = new Equipe("TestNom", Niveau.EXPERT);
        // Simuler le comportement du repository
        when(equipeRepository.save(equipe)).thenReturn(equipe);
        // Appeler la méthode à tester
        Equipe result = equipeServiceImpl.addEquipe(equipe);
        // Vérifier le résultat
        assertEquals("TestNom", result.getNomEquipe());
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void deleteEquipe() {
        // Créer un exemple d'étudiant
        Equipe equipe = new Equipe("TestNom", Niveau.EXPERT);
        equipe.setIdEquipe(1);
        // Simuler la méthode du repository

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        // Appeler la méthode à tester
        equipeServiceImpl.deleteEquipe(1);
        // Vérifier les interactions
        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void retrieveEquipe() {
        // Créer un exemple d'étudiant
        Equipe equipe = new Equipe("TestNom", Niveau.EXPERT);
        equipe.setIdEquipe(1);
        // Simuler la méthode du repository

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        // Appeler la méthode à tester
        Equipe result = equipeServiceImpl.retrieveEquipe(1);
        // Vérifier le résultat
        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeRepository, times(1)).findById(1);
    }



    @Test
    void evoluerEquipes() {
    }
}