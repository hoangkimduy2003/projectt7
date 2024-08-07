package com.duyhk.apiwebbh.controller;

import com.duyhk.apiwebbh.dto.KichCoDTO;
import com.duyhk.apiwebbh.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kich-co")
public class KichCoController {

    private final KichCoService kichCoService;

    @GetMapping
    public ResponseEntity<List<KichCoDTO>> getAll(){
        return ResponseEntity.ok().body(kichCoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KichCoDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(kichCoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody KichCoDTO dto){
        kichCoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Tao thanh cong");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody KichCoDTO dto,@PathVariable("id")  Long id){
        kichCoService.update(dto, id);
        return ResponseEntity.ok("Sua thanh cong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        kichCoService.delete(id);
        return ResponseEntity.ok("Xoa thanh cong");
    }
}
