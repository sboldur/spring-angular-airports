package com.sboldur.springangularairports.dto;

public class RunwayWithIdentificationsCount {
    private String identification;
    private Long identificationsCount;

    public RunwayWithIdentificationsCount(String identification, Long identificationsCount) {
        this.identification = identification;
        this.identificationsCount = identificationsCount;
    }

    public String getIdentification() {
        return identification;
    }

    public Long getIdentificationsCount() {
        return identificationsCount;
    }
}
