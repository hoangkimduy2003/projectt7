package com.duyhk.apiwebbh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maHoaDon;
    private Long tongSoSanPham;
    private Long tongSoTien;
    private String soDienThoai;
    private String diaChi;
    private String hovaTen;
    private String maNhanVien;
    private LocalDate ngayTao; // khi bat dau đặt hanàng sẽ có ngày tạo
    private LocalDate ngayHoanThanh; // ngày hoàn thành
    private String lyDoHuy;
    private Integer trangThai;// 0 đã huy, 1 đang chờ, 2 là chờ lấy hàng, 3 đang giao hàng, 4 : Đã hoàn thaành
    private Integer loaiHoaDon; // 1 la tai quay, 2 la online
    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan; // de biet duoc hoa don cua ai
}
