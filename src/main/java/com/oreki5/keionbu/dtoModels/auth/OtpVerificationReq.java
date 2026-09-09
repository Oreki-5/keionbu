package com.oreki5.keionbu.dtoModels.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OtpVerificationReq {
    @NotEmpty
    private String id;
    @NotEmpty
    private String otp;

}
