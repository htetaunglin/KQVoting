package edu.ucsmub.kqvoting.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.ucsmub.kqvoting.db.DateTImeConverter
import java.util.*

@Entity(tableName = "notification")
@TypeConverters(DateTImeConverter::class)
data class Notification(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "body")
    var body: String,

    @ColumnInfo(name = "time")
    var time: Date
)