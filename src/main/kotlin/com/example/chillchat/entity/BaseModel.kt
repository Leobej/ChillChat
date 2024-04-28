package com.example.chillchat.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*


@MappedSuperclass
abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    var createdDateTime: Date? = null
}
