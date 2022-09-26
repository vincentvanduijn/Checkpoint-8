package com.devoteam.VehicleApplication.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    @Id
    @NotNull
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Please fill in a vehicle name")
    @Schema(description = "Please fill in a vehicle name", required = true)
    private String model;

    @Column(nullable = false)
    @Schema(description = "Please state the color of the vehicle", required = true)
    private String color;

    @Column(nullable = false)
    @Schema(description = "Please fill in the year of release of the vehicle", required = true)
    private Integer productionYear;

    @Column(nullable = false)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "type_ID")
    private VehicleType vehicleType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "automaker_ID")
    private Automaker automaker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vehicle vehicle = (Vehicle) o;
        return id != null && Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
