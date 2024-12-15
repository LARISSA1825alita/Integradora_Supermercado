package com.integradora.supermercadointegradora.response;

public class ClienteResponseRest extends ResponseRest {

    private ClienteResponse clienteResponse = new ClienteResponse();
    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }
    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }
}
