package com.uca.pncsegundoparcialcoworking.service;

import com.uca.pncsegundoparcialcoworking.dto.request.SpaceRequestDTO;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponseDTO;
import com.uca.pncsegundoparcialcoworking.exception.BusinessRuleException;
import com.uca.pncsegundoparcialcoworking.exception.ResourceNotFoundException;
import com.uca.pncsegundoparcialcoworking.model.entity.Space;
import com.uca.pncsegundoparcialcoworking.model.enums.SpaceType;
import com.uca.pncsegundoparcialcoworking.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public SpaceResponseDTO createSpace(SpaceRequestDTO dto) {
        if (spaceRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new BusinessRuleException("Ya existe un espacio con el nombre: " + dto.getName());
        }
        Space space = mapToEntity(dto);
        space.setAvailable(true);
        return mapToResponse(spaceRepository.save(space));
    }

    public List<SpaceResponseDTO> getAllSpaces(SpaceType type, Boolean available) {
        List<Space> spaces;
        if (type != null && available != null) {
            spaces = spaceRepository.findByTypeAndAvailable(type, available);
        } else if (type != null) {
            spaces = spaceRepository.findByType(type);
        } else if (available != null) {
            spaces = spaceRepository.findByAvailable(available);
        } else {
            spaces = spaceRepository.findAll();
        }
        return spaces.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public SpaceResponseDTO getSpaceById(Long id) {
        return mapToResponse(findById(id));
    }

    public SpaceResponseDTO updateSpace(Long id, SpaceRequestDTO dto) {
        Space space = findById(id);

        if (spaceRepository.existsByNameIgnoreCaseAndIdNot(dto.getName(), id)) {
            throw new BusinessRuleException("Ya existe otro espacio con el nombre: " + dto.getName());
        }
        if (dto.getPricePerHour().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("El precio debe ser mayor a 0");
        }

        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setType(dto.getType());
        space.setCapacity(dto.getCapacity());
        space.setPricePerHour(dto.getPricePerHour());
        space.setFloor(dto.getFloor());
        space.setAmenities(dto.getAmenities());

        return mapToResponse(spaceRepository.save(space));
    }

    public void deleteSpace(Long id) {
        Space space = findById(id);
        if (!space.getAvailable()) {
            throw new BusinessRuleException("No se puede eliminar un espacio bloqueado o en uso (available = false)");
        }
        spaceRepository.delete(space);
    }

    private Space findById(Long id) {
        return spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado con ID: " + id));
    }

    private Space mapToEntity(SpaceRequestDTO dto) {
        Space space = new Space();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setType(dto.getType());
        space.setCapacity(dto.getCapacity());
        space.setPricePerHour(dto.getPricePerHour());
        space.setFloor(dto.getFloor());
        space.setAmenities(dto.getAmenities());
        return space;
    }

    private SpaceResponseDTO mapToResponse(Space space) {
        SpaceResponseDTO dto = new SpaceResponseDTO();
        dto.setId(space.getId());
        dto.setName(space.getName());
        dto.setDescription(space.getDescription());
        dto.setType(space.getType());
        dto.setCapacity(space.getCapacity());
        dto.setPricePerHour(space.getPricePerHour());
        dto.setAvailable(space.getAvailable());
        dto.setFloor(space.getFloor());
        dto.setAmenities(space.getAmenities());
        return dto;
    }
}