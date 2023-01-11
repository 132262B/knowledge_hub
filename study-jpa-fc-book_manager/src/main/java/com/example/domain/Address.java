package com.example.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Address {

    private String city;

    private String district;

    @Column(name = "address_detatil")
    private String detail;

    private String zipCode;

    public Address(String city, String district, String detail, String zipCode) {
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.zipCode = zipCode;
    }
}
