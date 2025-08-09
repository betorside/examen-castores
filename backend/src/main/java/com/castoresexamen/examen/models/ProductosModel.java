package com.castoresexamen.examen.models;
import jakarta.persistence.*;

@Entity
@Table(name = "productos")

public class ProductosModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "cantidad", columnDefinition = "INT(5)")
    private Integer cantidad;

    @Column(name = "estatus", columnDefinition = "INT(1)")
    private Integer estatus;

    // Getters and Setters
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getEstatus() {
        return estatus;
    }
    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
}