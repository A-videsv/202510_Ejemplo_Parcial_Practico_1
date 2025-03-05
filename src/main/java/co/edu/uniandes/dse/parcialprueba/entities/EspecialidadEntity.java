package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import co.edu.uniandes.dse.parcialprueba.podam.DateStrategy;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Entity
public class EspecialidadEntity extends BaseEntity {
    private String nombre;
    private String descripcion;
    private Long ID;

    @PodamExclude
    @ManyToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<MedicoEntity> medicos = new ArrayList<>();
}
