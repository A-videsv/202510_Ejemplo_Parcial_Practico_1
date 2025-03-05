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
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException {
        log.info("Creando un nuevo medico");
        if (medico.getNombre() == null || medico.getRegistro().startsWith("RM")) {
            throw new IllegalOperationException("El registro medico debe iniciar con RM");
        }
        return medicoRepository.save(medico);
    }
}
