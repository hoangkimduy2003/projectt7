package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTietDTO {
    private Long id;
    private Long donGia;
    private Long soLuong;
    private Long thanhTien;
    private SanPhamChiTietDTO sanPhamChiTiet;
}
