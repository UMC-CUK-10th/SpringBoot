package com.example.umc10th.domain.stores.repository;

import com.example.umc10th.domain.stores.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Stores, Long> {
}