package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.ThongTinDatHangDTO;
import com.duyhk.apiwebbh.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dat-hang-online")
@RequiredArgsConstructor
public class DatHangOnlineController {
    private final DatHangOnlineService datHangOnlineService;

    @PostMapping
    public ResponseDTO<String> create(@RequestBody ThongTinDatHangDTO dto){
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.datHang(dto))
                .build();
    }

    @PutMapping("{id}")
    public ResponseDTO<String> update(@PathVariable Long id, @RequestParam Integer trangThai){
        return ResponseDTO.<String>builder()
                .data(datHangOnlineService.updateTrangThai(id, trangThai))
                .build();
    }
}
