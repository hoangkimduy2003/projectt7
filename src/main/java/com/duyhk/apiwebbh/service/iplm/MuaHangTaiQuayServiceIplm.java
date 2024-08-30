package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.ThanhToanTaiQuayDTO;
import com.duyhk.apiwebbh.entity.HoaDon;
import com.duyhk.apiwebbh.entity.HoaDonChiTiet;
import com.duyhk.apiwebbh.entity.SanPhamChiTiet;
import com.duyhk.apiwebbh.repository.HoaDonChiTietRepo;
import com.duyhk.apiwebbh.repository.HoaDonRepository;
import com.duyhk.apiwebbh.repository.SanPhamChiTietRepository;
import com.duyhk.apiwebbh.repository.TaiKhoanRepository;
import com.duyhk.apiwebbh.service.MuaHangTaiQuayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MuaHangTaiQuayServiceIplm implements MuaHangTaiQuayService {

    private final HoaDonChiTietRepo hoaDonChiTietRepo;
    private final HoaDonRepository hoaDonRepo;
    private final TaiKhoanRepository taiKhoanRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;

    @Override
    public ResponseDTO<String> taoHoaDon() {
        HoaDon hoaDon = HoaDon.builder()
                .loaiHoaDon(1)
                .ngayTao(LocalDate.now())
                .tongSoTien(0l)
                .tongSoSanPham(0l)
                .trangThai(1)
                .build();
        hoaDonRepo.save(hoaDon);
        return ResponseDTO.<String>builder()
                .status(201)
                .message("Tao hoa don thanh cong")
                .build();
    }

    @Override
    public ResponseDTO<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO hdcttq) {
        HoaDon hoaDon = hoaDonRepo.findById(hdcttq.getHoaDonId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay hoa don"));
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(hdcttq.getSanPhamChiTietId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham chi tiet"));
        // số lượng sp > số lươnghj thêm
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSoLuong(hdcttq.getSoLuong());
        hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
        hoaDonChiTiet.setThanhTien(sanPhamChiTiet.getGia() * hdcttq.getSoLuong());
        hoaDon.setTongSoSanPham(hoaDon.getTongSoSanPham() + 1);
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() + hoaDonChiTiet.getThanhTien());

        sanPhamChiTiet.setSoLuongTonKho(sanPhamChiTiet.getSoLuongTonKho() - hdcttq.getSoLuong());
        sanPhamChiTiet.setSoLuongDaBan(sanPhamChiTiet.getSoLuongDaBan() + hdcttq.getSoLuong());

        sanPhamChiTietRepo.save(sanPhamChiTiet);
        hoaDonRepo.save(hoaDon);
        hoaDonChiTietRepo.save(hoaDonChiTiet);

        return ResponseDTO.<String>builder()
                .status(201)
                .message("Them san pham vao gio hang thanh cong")
                .build();
    }

    @Override
    public ResponseDTO<String> themSanPhamBangQR(String maSpct, Long hoaDonId) {
        return null;
    }

    @Override
    public ResponseDTO<String> thanhToanHoaDon(ThanhToanTaiQuayDTO tttq) {
        return null;
    }
}
