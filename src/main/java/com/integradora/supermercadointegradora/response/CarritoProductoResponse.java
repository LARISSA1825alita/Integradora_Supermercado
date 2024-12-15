package com.integradora.supermercadointegradora.response;

import com.integradora.supermercadointegradora.entity.CarritoProducto;
import java.util.List;

public class CarritoProductoResponse {
    private List<CarritoProducto> carritoProductos;
    public List<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }
    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;

    }
    private List<CarritoProducto> productos;
    public List<CarritoProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoProducto> productos) {
        this.productos = productos;
    }
}
