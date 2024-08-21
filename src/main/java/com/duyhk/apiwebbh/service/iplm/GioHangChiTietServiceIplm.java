package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.GioHangChiTietDTO;
import com.duyhk.apiwebbh.entity.GioHang;
import com.duyhk.apiwebbh.entity.GioHangChiTiet;
import com.duyhk.apiwebbh.entity.SanPhamChiTiet;
import com.duyhk.apiwebbh.exception.CustomExceptionHandle;
import com.duyhk.apiwebbh.repository.GioHangChiTietRepo;
import com.duyhk.apiwebbh.repository.GioHangRepository;
import com.duyhk.apiwebbh.repository.SanPhamChiTietRepository;
import com.duyhk.apiwebbh.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GioHangChiTietServiceIplm implements GioHangChiTietService {
    private final GioHangChiTietRepo gioHangChiTietRepo;
    private final GioHangRepository gioHangRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    @Override
    public String themVaoGioHang(GioHangChiTietDTO dto) {
        GioHangChiTiet entity = new GioHangChiTiet();
        GioHang gioHang = gioHangRepo.findById(dto.getGioHang().getId())
                .orElseThrow(() -> new RuntimeException("Gio Hang khong ton tai"));
        entity.setGioHang(gioHang);
        SanPhamChiTiet sp = sanPhamChiTietRepo.findById(dto.getSanPhamChiTiet().getId())
                .orElseThrow(() -> new CustomExceptionHandle("Khong tim thay sp"));
        entity.setSanPhamChiTiet(sp);
        GioHangChiTiet isExists = gioHangChiTietRepo.findByGioHangIdAndSanPhamChiTietId(dto.getGioHang().getId(), dto.getSanPhamChiTiet().getId())
                .orElse(null);
        if (isExists!= null) {
            entity.setId(isExists.getId());
            entity.setSoLuong(isExists.getSoLuong() + dto.getSoLuong());
        }else{
            entity.setSoLuong(dto.getSoLuong());
            gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() + 1);
            gioHangRepo.save(gioHang);
        }
        gioHangChiTietRepo.save(entity);
        return "Thêm sản phẩm vào giỏ hàng thành công";
    }

    @Override
    public String update(Long soLuong, Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gio Hang chi tiet khong ton tai"));

        gioHangChiTiet.setSoLuong(soLuong);
        gioHangRepo.save(gioHangChiTiet.getGioHang());
        return "Sửa thành công";
    }

    @Override
    public String xoaKhoiGioHang(Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gio Hang chi tiet khong ton tai"));

        GioHang gioHang = gioHangChiTiet.getGioHang();
        gioHang.setTongSoSanPham(gioHang.getTongSoSanPham() - 1);
        gioHangRepo.save(gioHang);
        gioHangChiTietRepo.deleteById(gioHangChiTiet.getId());
        return "Xóa thành công";
    }
}
