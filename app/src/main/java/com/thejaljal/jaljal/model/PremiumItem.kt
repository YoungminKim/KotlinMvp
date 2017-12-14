package com.thejaljal.jaljal.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by no.1 on 2017-12-11.
 */
data class PremiumItem(val result: Boolean?, val message: String?, val data: Data){
    data class Data(val itemList: ArrayList<Item>)

    data class Item(
            val addtseq: Int,
            var addtName: String,
            var addtCapa: String,
            var addtPrice: Int = 0,
            var thmbFile: String,
            var ordrSeq: Int = 0,
            var isOrder: Boolean
    ): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readByte() != 0.toByte()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(addtseq)
            parcel.writeString(addtName)
            parcel.writeString(addtCapa)
            parcel.writeInt(addtPrice)
            parcel.writeString(thmbFile)
            parcel.writeInt(ordrSeq)
            parcel.writeByte(if (isOrder) 1 else 0)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel): Item {
                return Item(parcel)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }
}