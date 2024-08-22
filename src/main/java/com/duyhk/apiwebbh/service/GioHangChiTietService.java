package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.GioHangChiTietDTO;

import java.util.List;

public interface GioHangChiTietService {
    List<GioHangChiTietDTO> getByGioHangId(Long gioHangId);
    String themVaoGioHang(GioHangChiTietDTO dto);
    String update(Long soLuong, Long id);
    String xoaKhoiGioHang(Long id);
}
