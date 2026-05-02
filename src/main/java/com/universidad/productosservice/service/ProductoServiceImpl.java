package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    @Override
    public Producto crear(String nombre, Double precio, Integer stock) {
        Producto producto = new Producto(null, nombre, precio, stock);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, String nombre, Double precio, Integer stock) {
        Producto producto = buscarPorId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
