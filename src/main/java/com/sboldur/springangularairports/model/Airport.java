package com.sboldur.springangularairports.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Airport implements Serializable {

    @Id
    private long id;

    @Column
    private String ident;

    @Column
    private String type;

    private String name;

    @Column
    private String continent;
    @Column(name = "ISO_COUNTRY", insertable = false, updatable = false)
    private String isoCountry;
    @Column
    private String isoRegion;
    @Column
    private String municipality;

    @ManyToOne
    @JoinColumn(name = "iso_country", referencedColumnName = "code")
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airport")
    private Set<Runway> runways = new HashSet<Runway>();

    public Airport() {
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setRunways(Set<Runway> runways) {
        this.runways = runways;
    }

    public Set<Runway> getRunways() {
        return runways;
    }

    public long getId() {
        return id;
    }

    public String getIdent() {
        return ident;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getIso_country() {
        return isoCountry;
    }

    public String getIso_region() {
        return isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setIso_country(String iso_country) {
        this.isoCountry = iso_country;
    }

    public void setIso_region(String iso_region) {
        this.isoRegion = iso_region;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", ident='" + ident + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", isoCountry='" + isoCountry + '\'' +
                ", isoRegion='" + isoRegion + '\'' +
                ", municipality='" + municipality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (id != airport.id) return false;
        if (ident != null ? !ident.equals(airport.ident) : airport.ident != null) return false;
        if (type != null ? !type.equals(airport.type) : airport.type != null) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
        if (continent != null ? !continent.equals(airport.continent) : airport.continent != null) return false;
        if (isoCountry != null ? !isoCountry.equals(airport.isoCountry) : airport.isoCountry != null) return false;
        if (isoRegion != null ? !isoRegion.equals(airport.isoRegion) : airport.isoRegion != null) return false;
        return municipality != null ? municipality.equals(airport.municipality) : airport.municipality == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ident != null ? ident.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (isoCountry != null ? isoCountry.hashCode() : 0);
        result = 31 * result + (isoRegion != null ? isoRegion.hashCode() : 0);
        result = 31 * result + (municipality != null ? municipality.hashCode() : 0);
        return result;
    }
}
