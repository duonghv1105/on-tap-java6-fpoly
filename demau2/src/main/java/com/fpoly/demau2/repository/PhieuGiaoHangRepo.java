package com.fpoly.demau2.repository;

import com.fpoly.demau2.entity.PhieuGiaoHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PhieuGiaoHangRepo extends JpaRepository<PhieuGiaoHang, UUID> {
    Page<PhieuGiaoHang> findAll(Pageable pageable);
}
