package com.duyhk.apiwebbh.dto;

import com.duyhk.apiwebbh.entity.HoaDon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThongTinHoaDonDTO {
    private HoaDonDTO thongTinHoaDon;
    private List<HoaDonChiTietDTO> hoaDonChiTietList;
}
