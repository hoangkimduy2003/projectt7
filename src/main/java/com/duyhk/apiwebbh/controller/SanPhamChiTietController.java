package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.SanPhamChiTietDTO;
import com.duyhk.apiwebbh.service.SanPhamChiTietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham-chi-tiet")
@RequiredArgsConstructor
public class SanPhamChiTietController {
    private final SanPhamChiTietService sanPhamChiTietService;


    //page=?&size=?
    @GetMapping
    public ResponseDTO<List<SanPhamChiTietDTO>> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return sanPhamChiTietService.getAll(page, size);
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid SanPhamChiTietDTO sanPhamChiTietDTO){
        sanPhamChiTietService.create(sanPhamChiTietDTO);
        return ResponseDTO.<Void>builder()
                .message("Them san pham chi tiet thanh cong")
                .status(201)
                .build();
    }
}