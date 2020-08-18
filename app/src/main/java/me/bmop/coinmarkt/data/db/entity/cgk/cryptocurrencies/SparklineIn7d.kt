package me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies

import android.os.Parcel
import android.os.Parcelable

data class SparklineIn7d(
    val price: List<Double>

) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createDoubleList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDoubleList(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SparklineIn7d> {
        override fun createFromParcel(parcel: Parcel): SparklineIn7d {
            return SparklineIn7d(parcel)
        }

        override fun newArray(size: Int): Array<SparklineIn7d?> {
            return arrayOfNulls(size)
        }
    }
}

fun Parcel.createDoubleList(): List<Double> {
    val size = readInt()
    val output = ArrayList<Double>(size)
    for (i in 0 until size) {
        output.add(readDouble())
    }
    return output
}

fun Parcel.writeDoubleList(input:List<Double>) {
    writeInt(input.size)
    return input.forEach(this::writeDouble)
}