package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import com.duyhk.apiwebbh.dto.SanPhamChiTietDTO;
import com.duyhk.apiwebbh.entity.SanPham;
import com.duyhk.apiwebbh.entity.SanPhamChiTiet;
import com.duyhk.apiwebbh.exception.CustomExceptionHandle;
import com.duyhk.apiwebbh.repository.SanPhamChiTietRepository;
import com.duyhk.apiwebbh.repository.SanPhamRepository;
import com.duyhk.apiwebbh.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietServiceIplm implements SanPhamChiTietService {

    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final SanPhamRepository sanPhamRepo;

    @Override
    public ResponseDTO<List<SanPhamChiTietDTO>> getAll(Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 5 : size;

        Page<SanPhamChiTiet> pageElement = sanPhamChiTietRepo.findAll(PageRequest.of(page,size));
        List<SanPhamChiTietDTO> dtoList =   mapToListDTO(pageElement.getContent());
        return ResponseDTO
                .<List<SanPhamChiTietDTO>>builder()
                .data(dtoList)
                .totalPages(pageElement.getTotalPages())
                .totalElements(pageElement.getTotalElements())
                .message("Get all product details successfully")
                .status(200)
                .build();
    }

    @Override
    public List<SanPhamChiTietDTO> getBySpId(Long sanPhamId) {
        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findBySanPhamId(sanPhamId);
        return mapToListDTO(list);
    }

    private List<SanPhamChiTietDTO> mapToListDTO(List<SanPhamChiTiet> entities){
        List<SanPhamChiTietDTO> listDTOs = new ArrayList<>();
        for(SanPhamChiTiet entity : entities){
            SanPhamChiTietDTO dto = new ModelMapper().map(entity,SanPhamChiTietDTO.class);
            listDTOs.add(dto);
        }
        return listDTOs;
    }
    @Override
    public SanPhamChiTietDTO getById(Long id) {

        return null;
    }

    @Override
    public void create(SanPhamChiTietDTO dto) {
        SanPhamChiTiet entity = new ModelMapper().map(dto,SanPhamChiTiet.class);
        SanPham sanPham = sanPhamRepo.findById(dto.getSanPham().getId())
                .orElseThrow(() -> new CustomExceptionHandle("Khong tim thay san pham"));
        entity.setSanPham(sanPham);
        entity.setTen(sanPham.getTen());
        sanPham.setGia(sanPham.getGia() > entity.getGia() ? entity.getGia() : sanPham.getGia());
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + entity.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        sanPhamChiTietRepo.save(entity);
    }

    @Override
    public void update(SanPhamChiTietDTO dto, Long id) {
        SanPhamChiTiet entity = sanPhamChiTietRepo.findById(id)
                .orElseThrow(() -> new CustomExceptionHandle("Chi tiet san pham khong ton tai"));
        SanPham sanPham = entity.getSanPham();
        entity.setTen(sanPham.getTen());
        sanPham.setGia(sanPham.getGia() > entity.getGia() ? entity.getGia() : sanPham.getGia());
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() + entity.getSoLuongTonKho());
        sanPhamRepo.save(sanPham);
        sanPhamChiTietRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        // nếu sản phẩm chi tiết id này là sản phẩm chi tiêt cuối cùng của sản phẩm thì sẽ set lại trạng thái của sản phẩm
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(id)
                .orElseThrow(() -> new CustomExceptionHandle("Chi tiet san pham khong ton tai"));

        List<SanPhamChiTiet> list = sanPhamChiTietRepo.findBySanPhamId(sanPhamChiTiet.getSanPham().getId());
        if(list.size() == 1){
            SanPham sanPham = sanPhamChiTiet.getSanPham();
            sanPham.setTrangThai(0);
            sanPhamRepo.save(sanPham);
        }
        sanPhamChiTietRepo.deleteById(id);
    }
}
