package com.sboldur.springangularairports.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Country implements Serializable {
    @Id
    @NotNull
    private long id;

    private String code;

    @Column
    private String name;

    @Column
    private String continent;

    @OneToMany(mappedBy = "country")
    private Set<Airport> airports = new HashSet<Airport>();

    public Country() {
    }

    public Country(String code, String name, String continent) {
        this.code = code;
        this.name = name;
        this.continent = continent;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        if (code != null ? !code.equals(country.code) : country.code != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return continent != null ? continent.equals(country.continent) : country.continent == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        return result;
    }
}
