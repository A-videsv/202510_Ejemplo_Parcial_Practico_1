package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsultaMedicaService {

    @Autowired
    private ConsultaMedicaRepository ConsultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity createMedico(ConsultaMedicaEntity consultamedica) throws IllegalOperationException {
        log.info("Creando una nueva consulta medica");
        Calendar calendar = Calendar.getInstance();
        if (consultamedica.getFecha().after(calendar.getTime())) {
            throw new IllegalOperationException("La fecha de consulta debe ser mayor a la fecha actual");
        }
        return ConsultaMedicaRepository.save(consultamedica);
    }
}
