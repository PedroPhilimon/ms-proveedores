package com.servicio_proveedores.ms_proveedores.dto;

import lombok.Data;

@Data
public class ProveedorDTO {
    private String rut;
    private String nombre;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
}