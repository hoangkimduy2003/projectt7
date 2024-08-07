package com.duyhk.apiwebbh.service;

import com.duyhk.apiwebbh.dto.KichCoDTO;

import java.util.List;

public interface KichCoService {
    List<KichCoDTO> getAll();
    KichCoDTO getById(Long id);
    void create(KichCoDTO dto);
    void update(KichCoDTO dto, Long id);
    void delete(Long id);
}
