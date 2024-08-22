package com.duyhk.apiwebbh.repository;

import com.duyhk.apiwebbh.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> findByTaiKhoanId(Long taiKhoanId);
}
