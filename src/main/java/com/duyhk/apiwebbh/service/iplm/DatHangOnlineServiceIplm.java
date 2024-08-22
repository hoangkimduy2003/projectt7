package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.ThongTinDatHangDTO;
import com.duyhk.apiwebbh.entity.*;
import com.duyhk.apiwebbh.exception.CustomExceptionHandle;
import com.duyhk.apiwebbh.repository.*;
import com.duyhk.apiwebbh.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatHangOnlineServiceIplm implements DatHangOnlineService {
    private final HoaDonRepository hoaDonRepo;
    private final HoaDonChiTietRepo hoaDonChiTietRepo;
    private final TaiKhoanRepository taiKhoanRepo;
    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepo gioHangChiTietRepo;
    private final SanPhamRepository sanPhamRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;


    @Override
    public String datHang(ThongTinDatHangDTO dto) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setSoDienThoai(dto.getSoDienThoai());
        hoaDon.setDiaChi(dto.getDiaChi());
        hoaDon.setHovaTen(dto.getHoVaTen());
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai(1);
        hoaDon.setLoaiHoaDon(2);
        TaiKhoan taiKhoan = taiKhoanRepo.findById(dto.getTaiKhoanId())
                .orElseThrow(() -> new CustomExceptionHandle("User khong ton tai"));
        hoaDon.setTaiKhoan(taiKhoan);

        GioHang gioHang = gioHangRepo.findByTaiKhoanId(dto.getTaiKhoanId()).get();
        hoaDon.setTongSoSanPham(gioHang.getTongSoSanPham());
        hoaDon.setTongSoTien(gioHang.getTongSoTien());
        hoaDon = hoaDonRepo.save(hoaDon);

        List<GioHangChiTiet> lgh = gioHangChiTietRepo.findByGioHangId(gioHang.getId());
        List<HoaDonChiTiet> lhd = new ArrayList<>();
        for (GioHangChiTiet x : lgh) {
            // set lai gia tri sp chi tiet
            SanPhamChiTiet spct = x.getSanPhamChiTiet();
            spct.setSoLuongDaBan(spct.getSoLuongDaBan() + x.getSoLuong());
            spct.setSoLuongTonKho(spct.getSoLuongTonKho() - x.getSoLuong());
            sanPhamChiTietRepo.save(spct);
            // set lai gia tri sap
            SanPham sanPham = spct.getSanPham();
            sanPham.setSoLuongDaBan(sanPham.getSoLuongDaBan() + x.getSoLuong());
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - x.getSoLuong());
            sanPhamRepo.save(sanPham);

            HoaDonChiTiet hd = HoaDonChiTiet.builder()
                    .donGia(x.getSanPhamChiTiet().getGia())
                    .soLuong(x.getSoLuong())
                    .sanPhamChiTiet(x.getSanPhamChiTiet())
                    .thanhTien(x.getSoLuong() * x.getSanPhamChiTiet().getGia())
                    .hoaDon(hoaDon)
                    .build();
            lhd.add(hd);
            gioHangChiTietRepo.deleteById(x.getId());
        }
        gioHang.setTongSoTien(0l);
        gioHang.setTongSoSanPham(0l);
        gioHangRepo.save(gioHang);
        hoaDonChiTietRepo.saveAll(lhd);
        return "Dat hang thanh cong";

    }

    @Override
    public String updateTrangThai(Long id, Integer trangThai) {
        // 0 đã huy, 1 đang chờ, 2 là chờ lấy hàng, 3 đang giao hàng, 4 : Đã hoàn thaành
        checkStatus(id,trangThai);
        return "Cap nhat thanh cong";
    }

    public void checkStatus(Long id, Integer trangThai) {
        if (trangThai != 0) {
            HoaDon hoaDon = hoaDonRepo.findByIdAndTrangThai(id, trangThai - 1)
                    .orElseThrow(() -> new RuntimeException("Trang thai khong hop le"));
            hoaDon.setTrangThai(trangThai);
            if (trangThai == 4) {
                hoaDon.setNgayHoanThanh(LocalDate.now());
            }
            hoaDonRepo.save(hoaDon);
        } else {
            huyHoaDon(id);
        }
    }

    private void huyHoaDon(Long id) {
        HoaDon hoaDon = hoaDonRepo.findById(id).orElseThrow(() -> new RuntimeException("Hoa don khong ton tai"));
        hoaDon.setTrangThai(0);
        hoaDonRepo.save(hoaDon);
        List<HoaDonChiTiet> chiTiets = hoaDonChiTietRepo.findByHoaDonId(id).orElse(new ArrayList<>());
        for (HoaDonChiTiet ct : chiTiets) {
            SanPhamChiTiet spct = ct.getSanPhamChiTiet();
            spct.setSoLuongDaBan(spct.getSoLuongDaBan() - ct.getSoLuong());
            spct.setSoLuongTonKho(spct.getSoLuongTonKho() + ct.getSoLuong());
            sanPhamChiTietRepo.save(spct);
            SanPham sanPham = spct.getSanPham();
            sanPham.setSoLuongDaBan(sanPham.getSoLuongDaBan() - ct.getSoLuong());
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + ct.getSoLuong());
            sanPhamRepo.save(sanPham);
        }
    }
}
