package com.unibh;

import java.time.LocalDate;

public class Alumn {

    private long ra;
    private String name;
    private LocalDate birthDate;

    public Alumn(long ra, String name, LocalDate birthDate) {
        this.ra = ra;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Alumn {" +
                "ra='" + ra + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public long getRa() {
        return ra;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
