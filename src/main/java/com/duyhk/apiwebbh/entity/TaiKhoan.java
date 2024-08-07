package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private String email;
    private String matKhau;
    private String hoVaTen;
    private Role role;
    private Long tongHoaDon; // 1 h
    private Long tongTien; // 10k
    private Integer hangTaiKhoan; // 1 kh thường; 2 kh vip nếu mà tonghd >= 100 và tongTien > 10000000
    private Integer trangThai;

}
