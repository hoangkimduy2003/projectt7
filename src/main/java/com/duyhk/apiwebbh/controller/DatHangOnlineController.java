package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.ThongTinDatHangDTO;
import com.duyhk.apiwebbh.dto.ThongTinHoaDonDTO;
import com.duyhk.apiwebbh.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dat-hang-online")
@RequiredArgsConstructor
public class DatHangOnlineController {
    private final DatHangOnlineService datHangOnlineService;

    @GetMapping("/{id}")
    public ResponseDTO<ThongTinHoaDonDTO> getById(@PathVariable Long id){
        return ResponseDTO.<ThongTinHoaDonDTO>builder()
                .status(200)
                .data(datHangOnlineService.getById(id))
                .build();
    }

    @PostMapping
    public ResponseDTO<String> create(@RequestBody ThongTinDatHangDTO dto){
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.datHang(dto))
                .build();
    }

    @PutMapping("{id}")
    public ResponseDTO<String> update(@PathVariable Long id, @RequestParam Integer trangThai){
        // từ trang
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.updateTrangThai(id, trangThai))
                .build();
    }
}
