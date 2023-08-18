package com.fpoly.demau2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HoaDon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoaDon")
    private Long maHoaDon;
    @Column(name = "NguoiLap")
    private String nguoiLap;
    @Column(name = "GhiChu")
    private String ghiChu;
}
