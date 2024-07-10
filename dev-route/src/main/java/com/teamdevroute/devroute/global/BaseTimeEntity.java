package com.teamdevroute.devroute.global;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(name = "date_created", updatable = false)
    private String dateCreated;

    @LastModifiedDate
    @Column(name = "date_updated")
    private String dateUpdated;

    @PrePersist // 생성 시 콜백 메서드 호출
    public void onPrePersist(){
        this.dateCreated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.dateUpdated = this.dateCreated;
    }

    @PreUpdate // 수정 시 콜백 메서드 호출
    public void onPreUpdate(){
        this.dateUpdated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}