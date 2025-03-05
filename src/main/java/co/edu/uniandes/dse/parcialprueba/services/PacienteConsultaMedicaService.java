package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.spi.ErrorMessage;
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
public class PacienteConsultaMedicaService {

    @Autowired
    PacienteRepository PacienteRepository;
    @Autowired
    ConsultaMedicaRepository ConsultaMedicaRepository;

    @Transactional
    public void addConsulta(Long pacienteId, Long consultaId)
            throws EntityNotFoundException, IllegalOperationException {
        Optional<PacienteEntity> paciente = PacienteRepository.findById(pacienteId);
        Optional<ConsultaMedicaEntity> consulta = ConsultaMedicaRepository.findById(consultaId);
        if (paciente.isEmpty() || consulta.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el medico o la especialidad");
        }

        for (ConsultaMedicaEntity consultaMedica : paciente.get().getConsultas()) {
            if (consultaMedica.getFecha().equals(consulta.get().getFecha())) {
                throw new IllegalOperationException("El paciente ya tiene una consulta medica en la fecha");
            }
            PacienteRepository.save(paciente.get());
        }

    }

    public List<ConsultaMedicaEntity> getConsultasProgramadas(Long pacienteId) throws EntityNotFoundException {
        Optional<PacienteEntity> paciente = PacienteRepository.findById(pacienteId);
        if (paciente.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el paciente");
        }

        Calendar today = Calendar.getInstance();
        List<ConsultaMedicaEntity> consultasProgramadas = paciente.get().getConsultas().stream()
                .filter(consulta -> consulta.getFecha().after(today.getTime())).collect(Collectors.toList());
        return consultasProgramadas;
    }
}
