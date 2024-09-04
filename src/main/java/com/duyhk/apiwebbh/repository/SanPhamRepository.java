package com.duyhk.apiwebbh.repository;

import com.duyhk.apiwebbh.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("""
        select s from SanPham s
        where s.ten like concat('%',:ten,'%')  
        and (s.loaiSanPham.id = :loaiSanPhamId or :loaiSanPhamId is null)
    """)
    List<SanPham> filter(
            @Param("ten") String ten,
            Long loaiSanPhamId
    );
}
