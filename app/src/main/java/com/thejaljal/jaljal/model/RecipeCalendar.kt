package com.thejaljal.jaljal.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by no.1 on 2017-10-12.
 */

data class RecipeCalendar(val result: Boolean?, val message: String?, val data: Data){
    data class Data(var calendarList: ArrayList<CalendarData>)

    data class CalendarData(var weekMenuSeq: Int,
                            var weekStartdate: String,
                            var weekEnddate: String,
                            var predictWeek: Int = 0,
                            var predictWeekString: String?,
                            var deliveryDate: String?,
                            var ordrSeq: Int = 0,
                            var ordrStatus: String?,
                            var addtPrice: Int = 0,
                            var dispYn: String?,
                            var redDt: String?,
                            var predictDate: String?,
                            var limitDate: String?,
                            var delYn: String?,
                            var weekRecipes: String?,
                            var recipes :ArrayList<Recipe.Data> = arrayListOf(),
                            var weekStartdateForm: String?,
                            var weekEnddateForm: String?,
                            var stopSimpleText: String?,
                            var stopLongText: String?,
                            var stopImageFile: String?): Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                arrayListOf(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(weekMenuSeq)
            parcel.writeString(weekStartdate)
            parcel.writeString(weekEnddate)
            parcel.writeInt(predictWeek)
            parcel.writeString(predictWeekString)
            parcel.writeString(deliveryDate)
            parcel.writeInt(ordrSeq)
            parcel.writeString(ordrStatus)
            parcel.writeInt(addtPrice)
            parcel.writeString(dispYn)
            parcel.writeString(redDt)
            parcel.writeString(predictDate)
            parcel.writeString(limitDate)
            parcel.writeString(delYn)
            parcel.writeString(weekRecipes)
            parcel.writeString(weekStartdateForm)
            parcel.writeString(weekEnddateForm)
            parcel.writeString(stopSimpleText)
            parcel.writeString(stopLongText)
            parcel.writeString(stopImageFile)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CalendarData> {
            override fun createFromParcel(parcel: Parcel): CalendarData {
                return CalendarData(parcel)
            }

            override fun newArray(size: Int): Array<CalendarData?> {
                return arrayOfNulls(size)
            }
        }


    }
}