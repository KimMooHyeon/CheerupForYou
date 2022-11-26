package com.sabuzak.yeonamplace.cheerupforyou.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLE_NAME = "BANNER_TB"

@Entity(tableName = TABLE_NAME)
data class Banner(
    @PrimaryKey(autoGenerate = true) val idx: Int,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "font") val font: Int?,
    @ColumnInfo(name = "size") val size: Int?,
    @ColumnInfo(name = "background") val background: Int?,
    @ColumnInfo(name = "color") val color: Int?,
    @ColumnInfo(name = "direction") val direction: Int?,
    @ColumnInfo(name = "speed") val speed: Int?,
    @ColumnInfo(name = "blink") val blink: Boolean?,
    @ColumnInfo(name = "outline") val outline: Boolean?,
    @ColumnInfo(name = "shining") val shining: Boolean?,
    @ColumnInfo(name = "shadow") val shadow: Boolean?
)