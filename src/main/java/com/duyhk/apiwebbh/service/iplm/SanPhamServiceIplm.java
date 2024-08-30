package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.LoaiSanPhamDTO;
import com.duyhk.apiwebbh.dto.SanPhamDTO;
import com.duyhk.apiwebbh.entity.LoaiSanPham;
import com.duyhk.apiwebbh.entity.SanPham;
import com.duyhk.apiwebbh.repository.LoaiSanPhamRepository;
import com.duyhk.apiwebbh.repository.SanPhamRepository;
import com.duyhk.apiwebbh.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamServiceIplm implements SanPhamService {
    public final SanPhamRepository sanPhamRepo;
    public final LoaiSanPhamRepository loaiSanPhamRepo;

    @Override
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        List<SanPham> listEntities = sanPhamRepo.findAll();
        List<SanPhamDTO> listDto = new ArrayList<>();
        mapToListDto(listEntities, listDto);
        return ResponseEntity.ok(listDto);
    }

    @Override
    public ResponseEntity<List<SanPhamDTO>> filter(String ten, Long loaiSanPhamId) {
        List<SanPham> entities = sanPhamRepo.filter(ten, loaiSanPhamId);
        List<SanPhamDTO> listDto = new ArrayList<>();
        mapToListDto(entities, listDto);
        return ResponseEntity.ok(listDto);
    }

    public void mapToListDto(List<SanPham> listEntities, List<SanPhamDTO> listDto) {
        for (SanPham entity : listEntities) {
            SanPhamDTO dto = new SanPhamDTO();
            dto.setId(entity.getId());
            dto.setTen(entity.getTen());
            dto.setMa(entity.getMa());
            dto.setGia(entity.getGia());
            dto.setSoLuongTonKho(entity.getSoLuongTonKho());
            dto.setSoLuongDaBan(entity.getSoLuongDaBan());
            dto.setMoTa(entity.getMoTa());
            dto.setTrangThai(entity.getTrangThai());
            // map lspEntity -> lspDto
            LoaiSanPhamDTO lsp = new LoaiSanPhamDTO();
            lsp.setId(entity.getLoaiSanPham().getId());
            lsp.setTen(entity.getLoaiSanPham().getTen());
            dto.setLoaiSanPham(lsp);
            dto.setImages(entity.getImages());
            listDto.add(dto);
        }
    }

    @Override
    public ResponseEntity<SanPhamDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> create(SanPhamDTO dto) throws IOException {
        List<String> images;
        if (dto.getFiles() != null) {
            images = processFiles(dto.getFiles());

        } else {
            throw new RuntimeException("Vui long chon file");
        }
        SanPham sanPham = new SanPham();
        sanPham.setImages(images);
        sanPham.setSoLuongDaBan(0l);
        mapToEntitySave(sanPham, dto);
        sanPhamRepo.save(sanPham);
        return ResponseEntity.ok("Them thanh cong");
    }


    @Override
    public ResponseEntity<String> update(SanPhamDTO dto, Long id) throws IOException {
        SanPham sanPham = sanPhamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("San pham not found"));
        List<String> images;
        if (dto.getFiles() != null) {
            images = processFiles(dto.getFiles());
            sanPham.setImages(images);
        }
        mapToEntitySave(sanPham, dto);
        sanPhamRepo.save(sanPham);
        return ResponseEntity.ok("Sua thanh cong");
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        sanPhamRepo.deleteById(id);
        return ResponseEntity.ok("Xoa thanh cong");
    }
    /*
        1 phuong thuc se co kieu du lieu tra ve ten phuong thuc va tham so truyen vao
        neu kieu du lieu tra ve ma khac void thi bat buoc phai co return
        neu ma la void thi khong can return
        <pham vi truy cap> <kieu du lieu tra ve> <ten phuong thuc> (<Cac tham so truyen vao>){


        }

     */

    private List<String> processFiles(List<MultipartFile> files) throws IOException {
        List<String> images = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            // vi du tai len file ten ok.png
            String name = multipartFile.getOriginalFilename();
            images.add(name);
            String path = "D:/workspace/std/images";
            File folder = new File(path);
            if (!folder.exists()) {
                // tao folder neu khong ton tai
                folder.mkdirs();
            }
            File file = new File(path + "/" + name);
            multipartFile.transferTo(file);
        }
        return images;
    }

    private void mapToEntitySave(SanPham entity, SanPhamDTO dto) {
        entity.setMa(dto.getMa());
        entity.setTen(dto.getTen());
        entity.setGia(dto.getGia());
        entity.setSoLuongTonKho(dto.getSoLuongTonKho());
        entity.setMoTa(dto.getMoTa());
        entity.setTrangThai(dto.getTrangThai());

        LoaiSanPham lsp = loaiSanPhamRepo.findById(dto.getLoaiSanPham().getId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay loai san pham"));
        entity.setLoaiSanPham(lsp);
    }


}
