package br.feevale.environmentalthreats;

import androidx.annotation.NonNull;

public class EnvironmentalThreat {
    private Long id;
    private String date;
    private String description;
    private String address;

    public EnvironmentalThreat() {
    }

    public EnvironmentalThreat(String date, String description, String address) {
        this.date = date;
        this.description = description;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString(){
        return this.id + "," + this.address + "," + this.date + "," + this.description;
    }
}
