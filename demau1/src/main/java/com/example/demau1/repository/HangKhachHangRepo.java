package com.example.demau1.repository;

import com.example.demau1.entity.HangKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HangKhachHangRepo extends JpaRepository<HangKhachHang , Integer> {
}
