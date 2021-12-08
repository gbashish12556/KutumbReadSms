package com.example.kutumbreadsms.util

import android.util.Log
import com.example.kutumbreadsms.data.SectionData

class Util {
    companion object {
        fun getHeaderTitleMap(): HashMap<Int, String> {
            val hashMap: HashMap<Int, String> = HashMap()
            hashMap.put(0, "0 Hour Ago")
            hashMap.put(1, "1 Hour Ago")
            hashMap.put(2, "2 Hour Ago")
            hashMap.put(3, "3 Hour Ago")
            hashMap.put(4, "6 Hour Ago")
            hashMap.put(5, "12 Hour Ago")
            hashMap.put(6, "1 Day Ago")
            return hashMap
        }

        fun getIndex(hour:Long):Int
        = when(hour){
                0L->0
                1L->1
                2L->2
                3L->3
                in 4..6->4
                in 7..12->5
                else->6
            }


        fun intialiseSectionData():MutableList<SectionData>{
            val mutableList :MutableList<SectionData> = mutableListOf()
            val titleMap:HashMap<Int, String> = getHeaderTitleMap()
            for (i in 0..6) {
                mutableList.add(SectionData(i, titleMap[i],false, mutableListOf()))
            }
            return mutableList
        }

    }

}