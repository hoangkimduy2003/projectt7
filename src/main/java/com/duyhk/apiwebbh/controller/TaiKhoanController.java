package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.TaiKhoanDTO;
import com.duyhk.apiwebbh.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @PostMapping("/login")
    public ResponseDTO<String> dangNhap(@RequestBody TaiKhoanDTO dto){
        return ResponseDTO.<String>builder()
                .message(taiKhoanService.dangNhap(dto))
                .status(200)
                .build();
    }
    @PostMapping("/register")
    public ResponseDTO<String> dangKy(@RequestBody TaiKhoanDTO dto){
        return ResponseDTO.<String>builder()
                .message(taiKhoanService.dangKy(dto))
                .status(200)
                .build();
    }
}
