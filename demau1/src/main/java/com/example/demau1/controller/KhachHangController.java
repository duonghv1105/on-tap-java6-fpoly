package com.example.demau1.controller;

import com.example.demau1.entity.KhachHang;
import com.example.demau1.request.KhachHangRequest;
import com.example.demau1.response.KhachHangResponse;
import com.example.demau1.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @GetMapping("")
    public ResponseEntity<Object> getAll(
            // dung required = false hoac trinh bao boc Optional de nhan cac gia tri null
//            @RequestParam("offset") Optional<Integer> pageNo,
//            @RequestParam("limit") Optional<Integer> pageSize
            @RequestParam(required = false) Integer pageNo // co the su dung them defaultValue de gan gia tri mac dinh neu khong tim thay hoac gia tri bang null
    ) {
        // tao san pageSize mac dinh
        int pageSize = 5;
        if (pageNo == null) {
            List<KhachHangResponse> var1 = khachHangService.findAll();
            if (var1.size() == 0) {
                return new ResponseEntity<>("No data", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(var1, HttpStatus.OK);
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<KhachHangResponse> var2 = khachHangService.findAll(pageable);
            List<KhachHangResponse> var3 = var2.getContent();
            if (var3.size() == 0) {
                /*
                 * khong the chuyen doi String sang json khi su dung ResponseEntity<Object>
                 * vi json khong biet nen dung String de xu ly chuoi hay chuyen doi sang json
                 * Can de Response<String> hoac tao kieu du lieu khac voi String
                 * */
                return new ResponseEntity<>("No data", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(var3, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> addKH(
            @Valid @RequestBody KhachHangRequest request,
            BindingResult error
    ) {
        if (error.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            error.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);//loi khong chuyen doi dc DefaultMessageCodesResolver trong jackson vi khong tim thay bo chuyen doi
//            return ResponseEntity.badRequest().body(errorMap);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(errorMap);
        } else {
            KhachHangResponse var1 = khachHangService.addKH(request);
//            Object response = new Object() {
//                public final String TenKhachHang = khachHang.getTenKhachHang();
//            };
            return new ResponseEntity<>(var1, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKH(
            @PathVariable Long id,
            @Valid @RequestBody KhachHangRequest request,
            BindingResult error
    ) {
        if (error.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            error.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            KhachHangResponse var1 = khachHangService.updateKH(id, request);
            if (var1 == null) {
                return new ResponseEntity<>("No data for id: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(var1, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKH(@PathVariable Long id) {
        if (khachHangService.deleteKH(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("No data for id: " + id, HttpStatus.NOT_FOUND);
    }
}
