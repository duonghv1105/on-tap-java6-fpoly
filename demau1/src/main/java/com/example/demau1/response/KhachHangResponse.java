package com.example.demau1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhachHangResponse {

    private Long MaKhachHang;

    private  String TenKhachHang;

    private String SoDienThoai;

    private Boolean GioiTinh;

    private Integer MaHang;

    private  String TenHang;
}
