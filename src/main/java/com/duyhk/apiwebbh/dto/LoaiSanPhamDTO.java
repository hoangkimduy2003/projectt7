package com.duyhk.apiwebbh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSanPhamDTO {
    private Long id;
    private String ten;

    /*
        {
            "id": 1,
            "ten": "duy"
        }
     */
}
