package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma; // spct0011
    private String ten; // nuoc coca
    private Long gia; // 10000
    private Long soLuongTonKho; // 1000
    private Long soLuongDaBan; // 0
    private Integer trangThai; // 1 hoạt động, 0 Ngừng hoạt động

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "mau_sac_id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "kich_co_id")
    private KichCo kichCo;
}
