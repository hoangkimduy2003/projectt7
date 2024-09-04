package com.duyhk.apiwebbh.repository;

import com.duyhk.apiwebbh.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    Optional<TaiKhoan> findBySoDienThoai(String username);
}
