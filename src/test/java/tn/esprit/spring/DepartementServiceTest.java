package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartement() {
        Departement departement = new Departement("Informatique");

        when(departementRepository.save(departement)).thenReturn(departement);

        Departement savedDepartement = departementService.addDepartement(departement);

        assertNotNull(savedDepartement);
        assertEquals("Informatique", savedDepartement.getNomDepart());
        verify(departementRepository).save(departement);
    }


    @Test
    public void testDeleteDepartement() {
        Departement departement = new Departement(1, "Informatique");

        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(1);

        verify(departementRepository).findById(1);
        verify(departementRepository).delete(departement);
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement(1, "Informatique");
        departement.setNomDepart("Engineering");

        when(departementRepository.save(departement)).thenReturn(departement);

        Departement updatedDepartement = departementService.updateDepartement(departement);

        assertNotNull(updatedDepartement);
        assertEquals("Engineering", updatedDepartement.getNomDepart());
        verify(departementRepository).save(departement);
    }
}
