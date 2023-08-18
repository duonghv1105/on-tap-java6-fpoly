package com.example.demau1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class KHRequest {
    @NotBlank(message = "khong de trong")
    private  String tenKhachHang;
    @NotBlank(message = "khong de trong")
    private String soDienThoai;
    @NotNull(message = "khong de trong")
    private Boolean gioiTinh;

    private HangKhachHangRequest hangKhachHang;
}
