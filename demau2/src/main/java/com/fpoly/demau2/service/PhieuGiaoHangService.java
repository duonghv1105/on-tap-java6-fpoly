package com.fpoly.demau2.service;

import com.fpoly.demau2.entity.HoaDon;
import com.fpoly.demau2.entity.PhieuGiaoHang;
import com.fpoly.demau2.payload.PhieuGiaoHangRequest;
import com.fpoly.demau2.payload.PhieuGiaoHangResponse;
import com.fpoly.demau2.repository.HoaDonRepo;
import com.fpoly.demau2.repository.PhieuGiaoHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhieuGiaoHangService {
    @Autowired
    PhieuGiaoHangRepo phieuGiaoHangRepo;
    @Autowired
    HoaDonRepo hoaDonRepo;
    public List<PhieuGiaoHangResponse> findAll() {
        return phieuGiaoHangRepo.findAll().stream()
                .map(x->toResponse(x))
                .collect(Collectors.toList());
    }

    public List<PhieuGiaoHangResponse> findAll(Pageable pageable) {
        return phieuGiaoHangRepo.findAll(pageable)
                .map(x->toResponse(x))
                .getContent();
    }

    public PhieuGiaoHangResponse addPGH(PhieuGiaoHangRequest request) {
        PhieuGiaoHang pgh = new PhieuGiaoHang();
        phieuGiaoHangRepo.save(toEntity(pgh,request));
        return toResponse(pgh);
    }

    public PhieuGiaoHangResponse updatePGH(UUID id, PhieuGiaoHangRequest request) {
        PhieuGiaoHang pgh = phieuGiaoHangRepo.findById(id).orElse(null);
        if(pgh != null) phieuGiaoHangRepo.save(toEntity(pgh,request));
        return toResponse(pgh);
    }

    public boolean deletePGH(UUID id) {
        PhieuGiaoHang pgh = phieuGiaoHangRepo.findById(id).orElse(null);
        if(pgh != null) {
            phieuGiaoHangRepo.delete(pgh);
            return true;
        }
        return false;
    }

    public PhieuGiaoHang toEntity(PhieuGiaoHang entity, PhieuGiaoHangRequest request){
        entity.setNguoiNhan(request.getNguoiNhan());
        entity.setSdtNguoiNhan(request.getSdtNguoiNhan());
        entity.setPhiGiaoHang(request.getPhiGiaoHang());

        if(request.getIdHoaDon()!=null) {
            HoaDon var1 = hoaDonRepo.findById(request.getIdHoaDon()).orElse(null);
            entity.setHoaDon(var1);
        }
        return entity;
    }
    public PhieuGiaoHangResponse toResponse(PhieuGiaoHang var) {
        PhieuGiaoHangResponse var1 = new PhieuGiaoHangResponse();
        var1.setMaPhieuGiao(var.getMaPhieuGiao());
        var1.setNguoiNhan(var.getNguoiNhan());
        var1.setSdtNguoiNhan(var.getSdtNguoiNhan());
        var1.setPhiGiaoHang(var.getPhiGiaoHang());
        if(var.getHoaDon() != null) {
            var1.setNguoiLap(var.getHoaDon().getNguoiLap());
            var1.setHoaDonGhiChu(var.getHoaDon().getGhiChu());
        }
        return var1;
    }
}
