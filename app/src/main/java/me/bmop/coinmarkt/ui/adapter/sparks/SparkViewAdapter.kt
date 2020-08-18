package me.bmop.coinmarkt.ui.adapter.sparks

import com.robinhood.spark.SparkAdapter

class SparkViewAdapter(
    private val yData: List<Double>
) : SparkAdapter() {

    override fun getCount(): Int {
        return yData.size
    }

    override fun getItem(index: Int): Any {
        return yData[index]
    }

    override fun getY(index: Int): Float {
        return yData[index].toFloat()
    }

}