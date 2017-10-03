package com.sboldur.springangularairports.dto;

import java.io.Serializable;

public class RunwayWithIdentificationsCount implements Serializable {
    private String identification;
    private Long identificationsCount;

    public RunwayWithIdentificationsCount() {
    }

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
