package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.TaiKhoanDTO;

public interface TaiKhoanService {
    String dangNhap(TaiKhoanDTO taiKhoanDTO);
    String dangKy(TaiKhoanDTO taiKhoanDTO);
}
