package edu.ucsmub.kqvoting.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import edu.ucsmub.kqvoting.db.StringGsonConverters
import java.io.Serializable

@Entity(tableName = "selection")
@TypeConverters(StringGsonConverters::class)
data class Selection(

    @ColumnInfo(name = "id")
    var id: String,

    @PrimaryKey()
    @ColumnInfo(name = "orderNumber")
    var order: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "section")
    var section: String,

    @ColumnInfo(name = "isBoy")
    var isBoy: Boolean,

    @ColumnInfo(name = "profile")
    var profile_image: String,

    @ColumnInfo(name = "detail")
    var detail: String,

    @ColumnInfo(name = "fbId")
    var facebookID: String,

    @ColumnInfo(name = "fbName")
    var facebookName: String,

    @ColumnInfo(name = "photos")
    var photos: ArrayList<String>,

    @ColumnInfo(name = "isVote")
    var isVote: Boolean = false,

    @ColumnInfo(name = "firstCount")
    var firstCount: Int = 0,

    @ColumnInfo(name = "secondCount")
    var secondCount: Int = 0
) : Serializable