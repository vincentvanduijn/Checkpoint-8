package com.devoteam.VehicleApplication.domain;

import lombok.*;
import org.hibernate.Hibernate;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Please fill in a vehicle name")
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "type_ID")
    private VehicleType vehicleType;

    @ManyToOne
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
