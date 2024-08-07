package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma; // sp001
    private String ten; // nuoc coca
    private Long gia; // giá của sp chi tiết nhỏ nhất
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    // 1 loai sp
    @ManyToOne // nhiều to 1
    @JoinColumn(name="loai_san_pham_id")
    private LoaiSanPham loaiSanPham;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "anh_san_pham")
    private List<String> images;
}
