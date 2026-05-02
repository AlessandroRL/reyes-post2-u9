package com.universidad.productosservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto es requerido")
    @Column(nullable = false)
    private String nombre;

    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    @Column(nullable = false)
    private Double precio;

    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    @Column(nullable = false)
    private Integer stock;
}
