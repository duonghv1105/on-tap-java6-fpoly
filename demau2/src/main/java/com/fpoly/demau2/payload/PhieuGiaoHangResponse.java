package com.fpoly.demau2.payload;

import com.fpoly.demau2.entity.HoaDon;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuGiaoHangResponse {
    private UUID maPhieuGiao;

    private String nguoiNhan;

    private String sdtNguoiNhan;

    private BigDecimal phiGiaoHang;
    private String nguoiLap;
    private String hoaDonGhiChu;
}
