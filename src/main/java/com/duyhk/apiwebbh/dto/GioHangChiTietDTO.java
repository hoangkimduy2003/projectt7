package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDTO {
    private Long id;
    private Long soLuong;
    private SanPhamChiTietDTO sanPhamChiTiet;
    private GioHangDTO gioHang;
}
