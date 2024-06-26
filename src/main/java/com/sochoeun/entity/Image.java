package com.sochoeun.entity;

import com.sochoeun.security.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int contentId;
    private String imageUrl;
}
