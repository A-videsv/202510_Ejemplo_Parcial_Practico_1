package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
public class MedicoEspecialidadTest {

    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    private PodamFactory factory = new PodamFactoryImpl();

    private MedicoEntity medico;
    private EspecialidadEntity especialidad;

    @BeforeEach
    void setUp() {
        medico = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medico);

        especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        entityManager.persist(especialidad);
    }

    @Test
    void testAddEspecialidad() throws EntityNotFoundException, IllegalOperationException {
        medicoEspecialidadService.addEspecialidad(medico.getId(), especialidad.getId());
        MedicoEntity updatedMedico = medicoRepository.findById(medico.getId()).orElse(null);
        assertNotNull(updatedMedico);
        assertTrue(updatedMedico.getEspecialidad().contains(especialidad));
    }

    @Test
    void testAddEspecialidadMedicoNotFound() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidad(0L, especialidad.getId());
        });
    }

    @Test
    void testAddEspecialidadEspecialidadNotFound() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidad(medico.getId(), 0L);
        });
    }
}
