package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTiet {
    /*
    gio_hang_chi_tiet: id bigint (khóa chính),
    so_luong bigint, san_pham_chi_tiet_id bigint (khóa phụ),
    cart_id bigint (khóa phụ)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long soLuong;
    // 1 giỏ hàng chi tiết sẽ có 1 sản pẩm chi tiết
    // 1 sản phâẩm chi tiết sẽ có the nam trong nhieu gio hang chi tiet
    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;

}
