package com.example.demau1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhachHangRequest {
    @NotBlank(message = "khong de trong")
    private  String tenKhachHang;
    @NotBlank(message = "khong de trong")
    private String soDienThoai;
    @NotNull(message = "khong de trong")
    private Boolean gioiTinh;

    private Integer idHangKH;
}
