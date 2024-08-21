package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.GioHangChiTietDTO;
import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gio-hang-chi-tiet")
@RequiredArgsConstructor
public class GioHangChiTietController {
    private final GioHangChiTietService gioHangChiTietService;

    @PostMapping
    public ResponseDTO<String> create(@RequestBody GioHangChiTietDTO dto){
        return ResponseDTO.<String>builder()
                .status(201)
                .message(gioHangChiTietService.themVaoGioHang(dto))
                .build();
    }
}
