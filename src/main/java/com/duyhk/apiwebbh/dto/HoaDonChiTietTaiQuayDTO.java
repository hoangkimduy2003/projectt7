package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonChiTietTaiQuayDTO {
    private Long hoaDonId;
    private Long sanPhamChiTietId;
    private Long soLuong;
}
