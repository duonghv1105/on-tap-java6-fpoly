package com.fpoly.demau2.controller;

import com.fpoly.demau2.payload.PhieuGiaoHangRequest;
import com.fpoly.demau2.payload.PhieuGiaoHangResponse;
import com.fpoly.demau2.service.PhieuGiaoHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/phieu-giao-hang")
public class PhieuGiaoHangController {
    @Autowired
    PhieuGiaoHangService phieuGiaoHangService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(
            @RequestParam(required = false) Integer pageNo
    ) {
        if (pageNo == null) {
            List<PhieuGiaoHangResponse> var = phieuGiaoHangService.findAll();
            if (var.size() == 0) {
                return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(var, HttpStatus.OK);
            }
        } else {
            int pageSize = 5;
            Pageable page = PageRequest.of(pageNo, pageSize);
            List<PhieuGiaoHangResponse> var1 = phieuGiaoHangService.findAll(page);
            if (var1.size() == 0) {
                return new ResponseEntity<>(Arrays.asList("No data found"), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(var1, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> filter() {
        BigDecimal max = new BigDecimal(300000);
        BigDecimal min = new BigDecimal(100000);
        List<PhieuGiaoHangResponse> var = phieuGiaoHangService.findAll().stream()
                .filter(x -> (x.getPhiGiaoHang().compareTo(min) >= 0 && x.getPhiGiaoHang().compareTo(max) <= 0))
                .collect(Collectors.toList());
        if (var.size() == 0) {
            return new ResponseEntity<>(Arrays.asList("No data found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(var, HttpStatus.OK);
        }
    }

    @GetMapping("/max")
    public ResponseEntity<Object> max() {
        List<PhieuGiaoHangResponse> var = phieuGiaoHangService.findAll();
        // truong hop map ManyToOne
        // tim phiGiaoHang max
        BigDecimal maxPhiGiaoHang = var
                .stream()
                .map(x -> x.getPhiGiaoHang())
                .max(BigDecimal::compareTo)
                .get();
//        System.out.println(maxPhiGiaoHang);
        var = phieuGiaoHangService.findAll();// khoi tao lai danh sach
        // loc cac PhieuGiaoHang co phiGiaoHang = phiGiaoHangMax
        List<PhieuGiaoHangResponse> hoaDonMax = var
                .stream()
                .filter(x -> x.getPhiGiaoHang().compareTo(maxPhiGiaoHang) == 0)
                .collect(Collectors.toList());

//        List<PhieuGiaoHangResponse> hoaDonMax = var
//                .stream()
//                .sorted(Comparator.comparing(PhieuGiaoHangResponse::getPhiGiaoHang))
//                .collect(Collectors.toList()); // sap xep hoa don theo phiGiaHang tang dan, map OneToOne thi chi can lay cai cuoi
        if (var.size() == 0) {
            return new ResponseEntity<>(Arrays.asList("No data found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hoaDonMax, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addPGH(
            @Valid @RequestBody PhieuGiaoHangRequest request,
            BindingResult error
    ) {
        if (error.hasErrors()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            error.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            PhieuGiaoHangResponse var = phieuGiaoHangService.addPGH(request);
            return new ResponseEntity<>(var, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePGH(
            @PathVariable UUID id,
            @Valid @RequestBody PhieuGiaoHangRequest request,
            BindingResult error
    ) {
        if (error.hasErrors()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            error.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            PhieuGiaoHangResponse var = phieuGiaoHangService.updatePGH(id, request);
            return new ResponseEntity<>(var, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKH(@PathVariable UUID id) {
        if (phieuGiaoHangService.deletePGH(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("No data for id", HttpStatus.NOT_FOUND);
    }
}
