package com.fpoly.demau2.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhieuGiaoHangRequest {
    @NotBlank(message = "khong de trong")
    private String nguoiNhan;
    @NotBlank(message = "khong de trong")
    private String sdtNguoiNhan;
    @NotNull(message = "khong de trong")
    private BigDecimal phiGiaoHang;
    private Long idHoaDon;
}
