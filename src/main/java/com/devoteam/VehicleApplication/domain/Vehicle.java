package com.devoteam.VehicleApplication.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;
    @Column(nullable = false)
    private final Date createdOn;
    @ManyToOne
    @JoinColumn(name = "automaker_ID")
    private final Automaker automaker;
    @Column(nullable = false)
    private final String name;
    @Column(nullable = false)
    private final String color;
    @Column(nullable = false)
    private final int year;
    @ManyToOne
    @JoinColumn(name = "type_ID")
    private final VehicleType vehicleType;

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
