package com.duyhk.apiwebbh.service.iplm;

import com.duyhk.apiwebbh.dto.KichCoDTO;
import com.duyhk.apiwebbh.entity.KichCo;
import com.duyhk.apiwebbh.repository.KichCoRepository;
import com.duyhk.apiwebbh.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KichCoServiceIplm implements KichCoService {
    private final KichCoRepository kichCoRepo;
    @Override
    public List<KichCoDTO> getAll() {
        List<KichCo> listEntity = kichCoRepo.findAll();
        List<KichCoDTO> listDTO = new ArrayList<>();
        mapToListDto(listEntity, listDTO);
        return listDTO;
    }

    @Override
    public KichCoDTO getById(Long id) {
        KichCo kichCo = kichCoRepo.findById(id).orElse(null);
        KichCoDTO kichCoDTO = new KichCoDTO();
        kichCoDTO.setId(kichCo.getId());
        kichCoDTO.setTen(kichCo.getTen());
        return kichCoDTO;
    }

    @Override
    public void create(KichCoDTO dto) {
        KichCo kichCo = new KichCo();
        kichCo.setTen(dto.getTen());
        kichCoRepo.save(kichCo);
    }

    @Override
    public void update(KichCoDTO dto, Long id) {
        KichCo kichCo = kichCoRepo.findById(id).orElse(null);
        if(kichCo!= null){
            kichCo.setTen(dto.getTen());
            kichCoRepo.save(kichCo);
        }else{
            throw new RuntimeException("Not found KichCo with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        kichCoRepo.deleteById(id);
    }
    private void mapToListDto(List<KichCo> entities, List<KichCoDTO> dtos){
        for(KichCo entity : entities){
            KichCoDTO dto = new KichCoDTO();
            dto.setId(entity.getId());
            dto.setTen(entity.getTen());
            dtos.add(dto);
        }
    }

}
