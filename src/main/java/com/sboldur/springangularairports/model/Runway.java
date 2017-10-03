package com.sboldur.springangularairports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Runway implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "AIRPORT_REF", insertable = false, updatable = false)
    private Long airportRef;

    @Column
    private String airportIdent;

    @Column
    private String surface;
    @Column
    private String leIdent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AIRPORT_REF", referencedColumnName = "ID")
    private Airport airport;

    public Runway() {
    }

    public Long getId() {
        return id;
    }

    public Long getAirportRef() {
        return airportRef;
    }

    public String getAirportIdent() {
        return airportIdent;
    }

    public String getSurface() {
        return surface;
    }

    public String getLeIdent() {
        return leIdent;
    }

    public void setAirportRef(Long airportRef) {
        this.airportRef = airportRef;
    }

    public void setAirportIdent(String airportIdent) {
        this.airportIdent = airportIdent;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public void setLeIdent(String leIdent) {
        this.leIdent = leIdent;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "id=" + id +
                ", airportRef=" + airportRef +
                ", airportIdent='" + airportIdent + '\'' +
                ", surface='" + surface + '\'' +
                ", leIdent='" + leIdent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Runway runway = (Runway) o;

        if (id != null ? !id.equals(runway.id) : runway.id != null) return false;
        if (airportRef != null ? !airportRef.equals(runway.airportRef) : runway.airportRef != null) return false;
        if (airportIdent != null ? !airportIdent.equals(runway.airportIdent) : runway.airportIdent != null)
            return false;
        if (surface != null ? !surface.equals(runway.surface) : runway.surface != null) return false;
        return leIdent != null ? leIdent.equals(runway.leIdent) : runway.leIdent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (airportRef != null ? airportRef.hashCode() : 0);
        result = 31 * result + (airportIdent != null ? airportIdent.hashCode() : 0);
        result = 31 * result + (surface != null ? surface.hashCode() : 0);
        result = 31 * result + (leIdent != null ? leIdent.hashCode() : 0);
        return result;
    }
}
