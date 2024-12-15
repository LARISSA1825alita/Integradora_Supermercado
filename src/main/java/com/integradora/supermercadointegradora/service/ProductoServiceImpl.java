package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.entity.Producto;
import com.integradora.supermercadointegradora.entity.dao.IProductoDAO;
import com.integradora.supermercadointegradora.response.ClienteResponseRest;
import com.integradora.supermercadointegradora.response.ProductoResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicelmpl implements com.integradora.supermercadointegradora.service.IProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoServicelmpl.class);

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> crear(Producto producto) {

        log.info("Buscar por ID");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try {
            Producto GuardarProducto = productoDAO.save(producto);
            if (GuardarProducto != null) {

                list.add(GuardarProducto);
                response.getProductoResponse().setProductos(list);
                response.setMetada("Respuesta OK", "00", "Creacion Exitosa");

            }else{
                log.info("No se encontro la moto");
                response.setMetada("Respuesta no encontrada", "-1", "Producto no creada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al crear la agencia");
            log.error("Error al guardar la agencia", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductoResponseRest> buscarproducto() {
        log.info("Inicio metodo buscarclientes");
        ProductoResponseRest response = new ProductoResponseRest();
        try {
            List<Producto> productos = (List<Producto>) productoDAO.findAll();
            response.getProductoResponse().setProductos(productos);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar la lista de productos");
            log.error("Error al consultar productos: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

}
