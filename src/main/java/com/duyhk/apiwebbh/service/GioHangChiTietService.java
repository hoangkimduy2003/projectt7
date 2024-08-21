package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.GioHangChiTietDTO;

public interface GioHangChiTietService {
    String themVaoGioHang(GioHangChiTietDTO dto);
    String update(Long soLuong, Long id);
    String xoaKhoiGioHang(Long id);
}
