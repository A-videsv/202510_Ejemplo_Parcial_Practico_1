package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException {
        log.info("Creando una nueva especialidad");
        if (especialidad.getNombre() == null || especialidad.getDescripcion().length() < 10) {
            throw new IllegalOperationException("La descripcion de la especialidad debe tener al menos 10 caracteres");
        }
        return especialidadRepository.save(especialidad);
    }
}
