package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.ThanhToanTaiQuayDTO;

public interface MuaHangTaiQuayService {
    ResponseDTO<String> taoHoaDon();
    ResponseDTO<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO hdcttq);
    ResponseDTO<String> themSanPhamBangQR(String maSpct, Long hoaDonId);
    ResponseDTO<String> thanhToanHoaDon(ThanhToanTaiQuayDTO tttq);
}
