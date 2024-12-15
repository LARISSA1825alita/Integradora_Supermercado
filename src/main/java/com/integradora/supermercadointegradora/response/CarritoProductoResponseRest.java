package com.integradora.supermercadointegradora.response;


public class CarritoProductoResponseRest extends ResponseRest{

    private CarritoProductoResponse carritoProductoResponse = new CarritoProductoResponse();
    public CarritoProductoResponse getCarritoProductoResponse() {
        return carritoProductoResponse;
    }
    public void setCarritoProductoResponse(CarritoProductoResponse carritoProductoResponse) {
        this.carritoProductoResponse = carritoProductoResponse;
    }
}