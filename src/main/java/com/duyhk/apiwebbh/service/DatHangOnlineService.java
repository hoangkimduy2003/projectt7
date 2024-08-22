package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.ThongTinDatHangDTO;

public interface DatHangOnlineService {
    String datHang(ThongTinDatHangDTO dto);
    String updateTrangThai(Long id, Integer trangThai);
}
