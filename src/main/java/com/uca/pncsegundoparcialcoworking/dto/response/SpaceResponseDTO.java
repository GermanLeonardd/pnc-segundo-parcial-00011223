package com.uca.pncsegundoparcialcoworking.dto.response;

import com.uca.pncsegundoparcialcoworking.model.enums.SpaceType;
import java.math.BigDecimal;

public class SpaceResponseDTO {

    private Long id;
    private String name;
    private String description;
    private SpaceType type;
    private Integer capacity;
    private BigDecimal pricePerHour;
    private Boolean available;
    private Integer floor;
    private String amenities;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public SpaceType getType() { return type; }
    public void setType(SpaceType type) { this.type = type; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public BigDecimal getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(BigDecimal pricePerHour) { this.pricePerHour = pricePerHour; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }

    public Integer getFloor() { return floor; }
    public void setFloor(Integer floor) { this.floor = floor; }

    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
}