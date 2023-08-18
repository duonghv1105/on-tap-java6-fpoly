package com.example.demau1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KhachHang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KhachHang {
    @Id
    @Column(name = "MaKhachHang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhachHang;
    @Column(name = "TenKhachHang")
    private  String tenKhachHang;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @ManyToOne
    @JoinColumn(name = "HangKhachHang")
    private HangKhachHang hangKhachHang;
}
