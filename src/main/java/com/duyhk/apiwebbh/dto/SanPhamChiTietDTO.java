package com.duyhk.apiwebbh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Min(value = 1, message = "gia phai lon hon 1")
    private Long gia; // 10000
    private Long soLuongTonKho; // 1000
    private Long soLuongDaBan; // 0
    private Integer trangThai;
    private SanPhamDTO sanPham;
    private MauSacDTO mauSac;
    private KichCoDTO kichCo;
}
