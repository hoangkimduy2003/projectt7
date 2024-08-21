package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.SanPhamChiTietDTO;

import java.util.List;

public interface SanPhamChiTietService {
    ResponseDTO<List<SanPhamChiTietDTO>>  getAll(Integer page, Integer size);
    List<SanPhamChiTietDTO> getBySpId(Long sanPhamId);
    SanPhamChiTietDTO getById(Long id);
    void create(SanPhamChiTietDTO dto);
    void update(SanPhamChiTietDTO dto, Long id);
    void delete(Long id);
}
