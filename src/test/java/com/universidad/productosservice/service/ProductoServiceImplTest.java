package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void listarTodos_retornaListaDelRepositorio() {
        List<Producto> productos = List.of(
                new Producto(1L, "Laptop", 1500.0, 10),
                new Producto(2L, "Mouse", 50.0, 100)
        );
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.listarTodos();

        assertSame(productos, resultado);
        verify(productoRepository).findAll();
    }

    @Test
    void buscarPorId_existente_retornaProducto() {
        Producto producto = new Producto(1L, "Teclado", 80.0, 50);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.buscarPorId(1L);

        assertSame(producto, resultado);
        verify(productoRepository).findById(1L);
    }

    @Test
    void buscarPorId_inexistente_lanzaExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productoService.buscarPorId(99L));

        assertEquals("Producto no encontrado: 99", exception.getMessage());
        verify(productoRepository).findById(99L);
    }

    @Test
    void crear_guardarYRetornarProducto() {
        Producto productoGuardado = new Producto(1L, "Tablet", 800.0, 5);
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = productoService.crear("Tablet", 800.0, 5);

        assertSame(productoGuardado, resultado);
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void actualizar_modificaCamposYGuardaProducto() {
        Producto existente = new Producto(1L, "Viejo", 100.0, 1);
        Producto actualizado = new Producto(1L, "Nuevo", 200.0, 2);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(productoRepository.save(existente)).thenReturn(actualizado);

        Producto resultado = productoService.actualizar(1L, "Nuevo", 200.0, 2);

        assertSame(actualizado, resultado);
        assertEquals("Nuevo", existente.getNombre());
        assertEquals(200.0, existente.getPrecio());
        assertEquals(2, existente.getStock());
        verify(productoRepository).findById(1L);
        verify(productoRepository).save(existente);
    }

    @Test
    void eliminar_delegaEnRepositorio() {
        productoService.eliminar(3L);

        verify(productoRepository).deleteById(3L);
    }
}