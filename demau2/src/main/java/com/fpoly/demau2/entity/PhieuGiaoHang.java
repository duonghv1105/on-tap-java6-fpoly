package com.fpoly.demau2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "PhieuGiaoHang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhieuGiaoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="MaPhieuGiao")
    private UUID maPhieuGiao;
    @Column(name = "NguoiNhan")
    private String nguoiNhan;
    @Column(name = "SdtNhan")
    private String sdtNguoiNhan;

    @Column(name = "PhiGiaoHang")
    private BigDecimal phiGiaoHang;
    @ManyToOne// nen map la OneToOne
    @JoinColumn(name = "HoaDonGiao")
    private HoaDon hoaDon;
}
