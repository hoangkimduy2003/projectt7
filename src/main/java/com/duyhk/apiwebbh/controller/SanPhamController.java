package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.KichCoDTO;
import com.duyhk.apiwebbh.dto.SanPhamDTO;
import com.duyhk.apiwebbh.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@RequiredArgsConstructor
public class SanPhamController {
    private final SanPhamService sanPhamService;

    @GetMapping
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        return sanPhamService.getAll();
    }

    @GetMapping
    public ResponseEntity<List<SanPhamDTO>> filter(@RequestParam String ten,
                                                   @RequestParam Long loaiSanPhamId) {
        return sanPhamService.filter(ten, loaiSanPhamId);
    }

    @PostMapping
    public ResponseEntity<String> create(@ModelAttribute SanPhamDTO sanPhamDTO) throws IOException {
        return sanPhamService.create(sanPhamDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody SanPhamDTO dto, @PathVariable("id") Long id) throws IOException {
        return sanPhamService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return sanPhamService.delete(id);
    }
}
