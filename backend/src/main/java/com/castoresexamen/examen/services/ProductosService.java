package com.castoresexamen.examen.services;

import com.castoresexamen.examen.models.ProductosModel;
import com.castoresexamen.examen.models.TransaccionesModel;
import com.castoresexamen.examen.models.UsuariosModel;
import com.castoresexamen.examen.repositories.ProductosRepository;
import com.castoresexamen.examen.repositories.TransaccionesRepository;
import com.castoresexamen.examen.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private TransaccionesRepository transaccionesRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;

    // Ver inventario
    public List<ProductosModel> getInventario() {
        return productosRepository.findAll();
    }

    // Ver productos activos/inactivos
    public List<ProductosModel> getByEstatus(Integer estatus) {
        return productosRepository.findByEstatus(estatus);
    }

    // Agregar producto (cantidad inicial 0, estatus 1)
    public ProductosModel agregarProducto(ProductosModel producto) {
        producto.setCantidad(0);
        producto.setEstatus(1);
        return productosRepository.save(producto);
    }

    // Entrada de productos (aumentar cantidad)
    public ProductosModel entradaProducto(Long id, int cantidad, Long idUsuario) throws Exception {
        Optional<ProductosModel> opt = productosRepository.findById(id);
        Optional<UsuariosModel> userOpt = usuariosRepository.findById(idUsuario);
        if (opt.isPresent() && userOpt.isPresent()) {
            ProductosModel prod = opt.get();
            prod.setCantidad(prod.getCantidad() + cantidad);
            productosRepository.save(prod);

            TransaccionesModel trans = new TransaccionesModel();
            trans.setUsuario(userOpt.get());
            trans.setProducto(prod);
            trans.setTipo("entrada");
            trans.setCantidad(cantidad);
            transaccionesRepository.save(trans);

            return prod;
        } else {
            throw new Exception("Producto o usuario no encontrado");
        }
    }

    // Disminuir inventario (solo si hay suficiente cantidad)
    public ProductosModel salidaProducto(Long id, int cantidad, Long idUsuario) throws Exception {
        Optional<ProductosModel> opt = productosRepository.findById(id);
        Optional<UsuariosModel> userOpt = usuariosRepository.findById(idUsuario);
        if (opt.isPresent() && userOpt.isPresent()) {
            ProductosModel prod = opt.get();
            if (prod.getCantidad() < cantidad) {
                throw new Exception("No hay suficiente inventario");
            }
            prod.setCantidad(prod.getCantidad() - cantidad);
            productosRepository.save(prod);

            TransaccionesModel trans = new TransaccionesModel();
            trans.setUsuario(userOpt.get());
            trans.setProducto(prod);
            trans.setTipo("salida");
            trans.setCantidad(cantidad);
            trans.setFecha(new java.util.Date());
            transaccionesRepository.save(trans);

            return prod;
        } else {
            throw new Exception("Producto o usuario no encontrado");
        }
    }

    // Dar de baja producto (estatus = 0)
    public ProductosModel bajaProducto(Long id) throws Exception {
        Optional<ProductosModel> opt = productosRepository.findById(id);
        if (opt.isPresent()) {
            ProductosModel prod = opt.get();
            prod.setEstatus(0);
            return productosRepository.save(prod);
        } else {
            throw new Exception("Producto no encontrado");
        }
    }

    // Activar producto (estatus = 1)
    public ProductosModel activarProducto(Long id) throws Exception {
        Optional<ProductosModel> opt = productosRepository.findById(id);
        if (opt.isPresent()) {
            ProductosModel prod = opt.get();
            prod.setEstatus(1);
            return productosRepository.save(prod);
        } else {
            throw new Exception("Producto no encontrado");
        }
    }
}