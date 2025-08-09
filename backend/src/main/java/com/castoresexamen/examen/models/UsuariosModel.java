package com.castoresexamen.examen.models;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class UsuariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena", length = 25)
    private String contrasena;

    @Column(name = "estatus", columnDefinition = "INT DEFAULT 1")
    private Integer estatus = 1;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolesModel idRol;

    public UsuariosModel() {}

    public long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEstatus() {
        return estatus;
    }
    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public RolesModel getIdRol() {
        return idRol;
    }
    public void setIdRol(RolesModel idRol) {
        this.idRol = idRol;
    }

}