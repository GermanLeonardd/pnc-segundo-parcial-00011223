package com.uca.pncsegundoparcialcoworking.repository;

import com.uca.pncsegundoparcialcoworking.model.entity.Space;
import com.uca.pncsegundoparcialcoworking.model.enums.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    List<Space> findByType(SpaceType type);

    List<Space> findByAvailable(Boolean available);

    List<Space> findByTypeAndAvailable(SpaceType type, Boolean available);
}