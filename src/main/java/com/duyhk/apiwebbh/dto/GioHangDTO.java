package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangDTO {
    private Long id;
    private Long tongSoSanPham;
    private Long tongSoTien;
    private TaiKhoanDTO taiKhoan;
}
