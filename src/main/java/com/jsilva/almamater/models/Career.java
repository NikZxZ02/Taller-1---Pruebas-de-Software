package com.jsilva.almamater.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

/**
 *
 * @author JSilva
 */
public class Career {
    
    private int id;
    private String name;
    
    Set<University> universities;

    @JsonIgnore
    @JsonProperty(value = "universities")
    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }

    public Career() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Career [id=" + id + ", name=" + name + "]";
    }

    
    
}
