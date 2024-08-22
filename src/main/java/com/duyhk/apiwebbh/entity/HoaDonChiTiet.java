package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTiet {
    /*
    id bigint (khóa chính), don_gia bigint, so_luong bigint, thanh_tien
bigint, san_pham_chi_tiet_id bigint (khóa phụ), hoa_don_id bigint (khóa phụ)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long donGia;
    private Long soLuong;
    private Long thanhTien;
    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;
}
