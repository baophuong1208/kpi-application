package com.nashtech.kpi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;
    @Column
    private String createdBy;
    @Column
    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;
    @Column
    private String lastUpdatedBy;

}
