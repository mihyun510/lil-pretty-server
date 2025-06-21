package com.lil.pretty.domain.user;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @Column(name = "us_id", length = 50)
    private String usId;

    @Column(name = "us_pw", nullable = false, length = 100)
    private String usPw;

    @Column(name = "us_nm", length = 10)
    private String usNm;

    @Column(name = "us_email", nullable = false, length = 50)
    private String usEmail;

    @Column(name = "us_role", nullable = false, length = 5)
    private String usRole = "USER";

    @Column(name = "us_phone", nullable = false, length = 11)
    private String usPhone;
    
}
