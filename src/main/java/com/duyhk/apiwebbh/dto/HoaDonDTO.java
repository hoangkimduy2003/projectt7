package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
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
    private Integer loaiHoaDon;
    private TaiKhoanDTO taiKhoan;
}
