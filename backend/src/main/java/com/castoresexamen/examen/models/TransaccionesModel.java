// This file is part of the examen project
// It defines the TransaccionesModel class which represents a transaction in the system.
package com.castoresexamen.examen.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transacciones")
public class TransaccionesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long idTransaccion;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private UsuariosModel idUsuario; 
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")
    private ProductosModel idProducto;
    @Column(name = "cantidad", columnDefinition = "INT(5)")
    private Integer cantidad;
    @Column(name = "fecha_transaccion")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;
    @Column(name = "tipo", length = 25)
    private String tipo; // "entrada" o "salida"

    // Getters y Setters 


    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public UsuariosModel getIdUsuario() {
        return idUsuario;
    }

    public void setUsuario(UsuariosModel usuario) {
        this.idUsuario = usuario;
    }

    public ProductosModel getIdProducto() {
        return idProducto;
    }

    public void setProducto(ProductosModel producto) {
        this.idProducto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha(Date fecha) {
        this.fechaTransaccion = fecha;
    }
}
