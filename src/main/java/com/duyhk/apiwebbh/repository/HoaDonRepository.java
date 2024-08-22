package com.duyhk.apiwebbh.repository;

import com.duyhk.apiwebbh.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    Optional<HoaDon> findByIdAndTrangThai(Long id, int i);
}
