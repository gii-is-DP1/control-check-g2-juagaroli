package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedings")
@Getter
@Setter
public class Feeding  extends BaseEntity{
	@NotNull
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate startDate;
    
    @Column(name = "weeks_duration")
    @NotNull
    @Min(1)
    double weeksDuration;
    
    @ManyToOne
    @NotNull
    Pet pet; 

    @ManyToOne
    @JoinColumn(name = "feeding_type_id")
    FeedingType feedingType;
}
