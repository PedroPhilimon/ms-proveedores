package com.servicio_proveedores.ms_proveedores.controller;

import com.servicio_proveedores.ms_proveedores.dto.OrdenCompraDTO;
import com.servicio_proveedores.ms_proveedores.model.OrdenCompra;
import com.servicio_proveedores.ms_proveedores.model.Proveedor;
import com.servicio_proveedores.ms_proveedores.service.OrdenCompraService;
import com.servicio_proveedores.ms_proveedores.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService service;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<OrdenCompra> obtenerTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdenCompra> crear(@RequestBody OrdenCompraDTO dto) {
        java.util.Optional<Proveedor> proveedorOpt = proveedorService.buscarPorId(dto.getIdProveedor());
        
        if (proveedorOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        OrdenCompra orden = new OrdenCompra();
        orden.setIdItemInventario(dto.getIdItemInventario());
        orden.setCantidadPedida(dto.getCantidadPedida());
        orden.setFechaPedido(dto.getFechaPedido());
        orden.setMontoEstimado(dto.getMontoEstimado());
        orden.setEstado(dto.getEstado());
        orden.setProveedor(proveedorOpt.get());
        
        return ResponseEntity.ok(service.guardar(orden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}