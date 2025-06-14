package com.lil.pretty.domain.common.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(name = "IN_DATE", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") //오라클의 SYSDATE를 기본값
    private LocalDateTime inDate;

    @Column(name = "IN_USER", updatable = false, nullable = false)
    private String inUser;

    @UpdateTimestamp
    @Column(name = "UPD_DATE", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updDate;

    @Column(name = "UPD_USER", insertable = false)
    private String updUser;

    @PrePersist //JPA를 사용하면서 오라클 SYSDATE를 직접 활용
    protected void onCreate() {
        this.inDate = LocalDateTime.now(); // 생성 날짜 설정
        this.inUser = getCurrentUserId(); // 생성한 사용자 ID 설정
    }

    @PreUpdate //upd_user와 upd_date는 @PreUpdate를 통해 자동 업데이트
    protected void onUpdate() {
        this.updDate = LocalDateTime.now(); // 수정 날짜 설정
        this.updUser = getCurrentUserId(); // 수정한 사용자 ID 설정
    }

    private String getCurrentUserId() {
        try {
        	//@PrePersist를 사용하여 자동으로 로그인한 사용자 ID 저장 (SecurityContextHolder 활용)
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername(); // 로그인한 사용자의 ID 반환
            }
        } catch (Exception e) {
            return "ADMIN"; // 로그인 정보가 없을 경우 기본값 설정
        }
        return "ADMIN";
    }
}
