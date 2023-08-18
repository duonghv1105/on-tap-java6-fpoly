package com.example.demau1.repository;

import com.example.demau1.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Long> {
   Page<KhachHang> findAll(Pageable pageable);
}
