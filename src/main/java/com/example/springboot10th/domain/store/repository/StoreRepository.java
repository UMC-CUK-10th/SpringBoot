package com.example.springboot10th.domain.store.repository;

import com.example.springboot10th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
