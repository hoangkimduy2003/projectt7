package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
    private Long id;
    private String ma; // spct0011
    private String ten; // nuoc coca
    private Long gia; // 10000
    private Long soLuongTonKho; // 1000
    private Long soLuongDaBan; // 0
    private Integer trangThai;
    private SanPhamDTO sanPhamDTO;
    private MauSacDTO mauSacDTO;
    private KichCoDTO kichCoDTO;
}
