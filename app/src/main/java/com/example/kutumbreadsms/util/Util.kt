package com.example.kutumbreadsms.util

import android.util.Log
import com.example.kutumbreadsms.data.SectionData

class Util {
    companion object {
        fun getHeaderTitleMap(): HashMap<Int, String> {
            val hashMap: HashMap<Int, String> = HashMap()
            hashMap.put(0, "1 Hour")
            hashMap.put(1, "2 Hour")
            hashMap.put(2, "3 Hour")
            hashMap.put(3, "6 Hour")
            hashMap.put(4, "12 Hour")
            return hashMap
        }

        fun getIndex(hour:Long):Int
        = when(hour){
                1L->0
                2L->1
                3L->2
                in 4..6->3
                else->4
            }


        fun intialiseSectionData():MutableList<SectionData>{
            val mutableList :MutableList<SectionData> = mutableListOf()
            val titleMap:HashMap<Int, String> = getHeaderTitleMap()
            for (i in 0..4) {
                mutableList.add(SectionData(0, titleMap[i], mutableListOf()))
            }
            Log.d("SectionData3",mutableList[4].sectionName.toString())
            return mutableList
        }

    }

}