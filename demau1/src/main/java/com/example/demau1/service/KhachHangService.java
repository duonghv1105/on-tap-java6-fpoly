package com.example.demau1.service;


import com.example.demau1.entity.HangKhachHang;
import com.example.demau1.entity.KhachHang;
import com.example.demau1.repository.HangKhachHangRepo;
import com.example.demau1.repository.KhachHangRepo;
import com.example.demau1.request.KhachHangRequest;
import com.example.demau1.response.KhachHangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepo khachHangRePo;
    @Autowired
    private HangKhachHangRepo hangKhachHangRePo;


    //Get all

    public List<KhachHangResponse> findAll() {
        List<KhachHang> var1 = khachHangRePo.findAll();
        return var1.stream().
                map(this::toResponse).
                collect(Collectors.toList());
    }

    public Page<KhachHangResponse> findAll(Pageable pageable) {
        Page<KhachHang> var1 = khachHangRePo.findAll(pageable);
        return var1.map(this::toResponse);
    }

    public KhachHangResponse addKH(KhachHangRequest request) {
       KhachHang kh = toEntity(request);
        return toResponse(kh);
    }

    // Update Doi  tuong

    public KhachHangResponse updateKH(Long id, KhachHangRequest request) {
        KhachHang var1 = khachHangRePo.findById(id).orElse(null);
        if (var1 != null) {
            var1.setTenKhachHang(request.getTenKhachHang());
            var1.setSoDienThoai(request.getSoDienThoai());
            var1.setGioiTinh(request.getGioiTinh());

            HangKhachHang hkh = hangKhachHangRePo.findById(request.getIdHangKH()).orElse(null);
            var1.setHangKhachHang(hkh);
//
            khachHangRePo.save(var1);
            return toResponse(var1);
        }
        return null;
    }


    // xoa doi tuong
    public boolean deleteKH(Long ma) {
        KhachHang kh = khachHangRePo.findById(ma).orElse(null);
        if (kh != null) {
            khachHangRePo.delete(kh);
            return true;
        }
        return false;
    }


    public KhachHang toEntity(KhachHangRequest request) {
        KhachHang var1 = new KhachHang();
//        khachHang.setMaKhachHang(kh.getMaKhachHang());
        var1.setTenKhachHang(request.getTenKhachHang());
        var1.setSoDienThoai(request.getSoDienThoai());
        var1.setGioiTinh(request.getGioiTinh());
//        HangKhachHang hkh = hangKhachHangRePo.findById(khachHangRequest.getHangKhachHang().getMaHang()).get();// throws NoSuchElementException: not used
        HangKhachHang var2 = null;
        if(request.getIdHangKH()!=null){
            var2 = hangKhachHangRePo.findById(request.getIdHangKH()).orElse(null);
        }

        // neu dung KHRequest thi can save truoc khi set cho KH
//        HangKhachHang hangKhachHang = new HangKhachHang();
//        hangKhachHang.setMaHang(kh.getHangKhachHang().getMaHang());
//        hangKhachHang.setTenHang(kh.getHangKhachHang().getTenHang());
//        hangKhachHangRePo.save(hangKhachHang);
        var1.setHangKhachHang(var2);

        // co the dung buider cho de nhin
//        KhachHang var1 = KhachHang.builder()
//                    .tenKhachHang(request.getTenKhachHang())
//                    .gioiTinh(request.getGioiTinh())
//                    .soDienThoai(request.getSoDienThoai())
//                    .hangKhachHang()
//                    .build();
        khachHangRePo.save(var1);
        return var1;
    }

    public KhachHangResponse toResponse(KhachHang kh) {
        KhachHangResponse khachHang = new KhachHangResponse();
        khachHang.setMaKhachHang(kh.getMaKhachHang());
        khachHang.setTenKhachHang(kh.getTenKhachHang());
        khachHang.setSoDienThoai(kh.getSoDienThoai());
        khachHang.setGioiTinh(kh.getGioiTinh());

        if (kh.getHangKhachHang() != null) {
            khachHang.setMaHang(kh.getHangKhachHang().getMaHang());
            khachHang.setTenHang(kh.getHangKhachHang().getTenHang());
        }
        return khachHang;
    }

}
