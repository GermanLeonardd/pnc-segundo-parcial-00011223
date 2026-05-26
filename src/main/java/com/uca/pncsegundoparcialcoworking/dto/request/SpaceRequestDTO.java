package com.uca.pncsegundoparcialcoworking.dto.request;

import com.uca.pncsegundoparcialcoworking.model.enums.SpaceType;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SpaceRequestDTO {

    @NotBlank(message = "El nombre es requerido")
    private String name;

    private String description;

    @NotNull(message = "El tipo de espacio es requerido")
    private SpaceType type;

    @NotNull(message = "La capacidad es requerida")
    @Min(value = 1, message = "La capacidad mínima es 1")
    private Integer capacity;

    @NotNull(message = "El precio por hora es requerido")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal pricePerHour;

    @NotNull(message = "El piso es requerido")
    @Min(value = 0, message = "El piso no puede ser negativo")
    private Integer floor;

    private String amenities;
}