package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.ThongTinDatHangDTO;
import com.duyhk.apiwebbh.dto.ThongTinHoaDonDTO;

public interface DatHangOnlineService {
    ThongTinHoaDonDTO getById(Long id);
    String datHang(ThongTinDatHangDTO dto);
    String updateTrangThai(Long id, Integer trangThai);
}
