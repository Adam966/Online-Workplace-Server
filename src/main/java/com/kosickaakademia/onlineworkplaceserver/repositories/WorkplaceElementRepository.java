package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface WorkplaceElementRepository<T extends WorkplaceElementEntity> extends JpaRepository<T, Long> {
}
