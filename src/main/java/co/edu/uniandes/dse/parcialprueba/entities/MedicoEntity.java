package co.edu.uniandes.dse.parcialprueba.entities;

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
public class MedicoEntity extends BaseEntity {
    private String nombre;
    private String apellido;
    private Long ID;
    private String registro;

    @PodamExclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<EspecialidadEntity> especialidad = new ArrayList<>();
}
