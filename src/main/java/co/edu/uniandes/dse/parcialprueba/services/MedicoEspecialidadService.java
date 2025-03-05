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
public class MedicoEspecialidadService {

    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public void addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        Optional<MedicoEntity> medico = medicoRepository.findById(medicoId);
        Optional<EspecialidadEntity> especialidad = especialidadRepository.findById(especialidadId);
        if (medico.isEmpty() || especialidad.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el medico o la especialidad");
        }
        medico.get().getEspecialidad().add(especialidad.get());
        medicoRepository.save(medico.get());
    }
}
