package com.lab5.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjectsLabs")
data class SubjectLabEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "subject_id") val subjectId: Int,
    val title: String,
    val description: String,
    val comment: String? = null,
    val inProgress: Boolean = false,
    val isCompleted: Boolean = false,
)