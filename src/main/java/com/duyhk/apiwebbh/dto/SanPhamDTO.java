package com.duyhk.apiwebbh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private Long id;
    private String ma; // sp001
    private String ten; // nuoc coca
    private Long gia; // giá của sp chi tiết nhỏ nhất
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai; // 0 là không hoạt động 1: hoat động
    private LoaiSanPhamDTO loaiSanPham;
    private List<String> images;

    @JsonIgnore
    private List<MultipartFile> files;


}
