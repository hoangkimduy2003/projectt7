package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.TaiKhoanDTO;
import com.duyhk.apiwebbh.entity.GioHang;
import com.duyhk.apiwebbh.entity.Role;
import com.duyhk.apiwebbh.entity.TaiKhoan;
import com.duyhk.apiwebbh.repository.GioHangRepository;
import com.duyhk.apiwebbh.repository.TaiKhoanRepository;
import com.duyhk.apiwebbh.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaiKhoanServiceIplm implements TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepo;
    private final GioHangRepository gioHangRepo;
    @Override
    public String dangNhap(TaiKhoanDTO taiKhoanDTO) {


        return "Đăng nhập thành công";
    }

    @Override
    public String dangKy(TaiKhoanDTO taiKhoanDTO) {
        TaiKhoan taiKhoan = new ModelMapper().map(taiKhoanDTO,TaiKhoan.class);
        taiKhoan.setRole(Role.KHACHHANG);
        taiKhoan.setTongHoaDon(0l);
        taiKhoan.setTongTien(0l);
        taiKhoan.setTrangThai(1);
        taiKhoan.setHangTaiKhoan(1);
        taiKhoan = taiKhoanRepo.save(taiKhoan);
        GioHang gioHang = new GioHang();
        gioHang.setTongSoSanPham(0l);
        gioHang.setTongSoTien(0l);
        gioHang.setTaiKhoan(taiKhoan);
        gioHangRepo.save(gioHang);
        return "Đăng ký thành công";
    }
}
