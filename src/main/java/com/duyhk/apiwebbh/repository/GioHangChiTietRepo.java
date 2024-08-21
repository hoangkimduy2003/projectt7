package com.duyhk.apiwebbh.repository;

import com.duyhk.apiwebbh.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GioHangChiTietRepo extends JpaRepository<GioHangChiTiet, Long> {
    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(Long id, Long id1);
}
