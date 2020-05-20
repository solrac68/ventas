package controllers;

import java.time.LocalDate;

import play.data.validation.Constraints;

public class VendedorData {
    public VendedorData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Constraints.Min(0)
    private Integer id;

    @Constraints.Required
    private String nombre;

    //@Constraints.Required
    private String numCelular;

    //@Constraints.Required
    private String estado;
}
