package com.example.demau1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hangKhachHang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HangKhachHang {
    @Id
    @Column(name = "MaHang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer maHang;
    @Column(name = "TenHang")
    private  String tenHang;
}
