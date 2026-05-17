package com.servicio_proveedores.ms_proveedores.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrdenCompraDTO {
    private Long idItemInventario;
    private Integer cantidadPedida;
    private LocalDateTime fechaPedido;
    private Double montoEstimado;
    private String estado;
    private Long idProveedor;
}