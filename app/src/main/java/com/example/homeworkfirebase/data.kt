package com.example.homeworkfirebase

import com.google.gson.annotations.SerializedName

data class DirectionResponce(val routes:List<Routes>)
data class Routes(@SerializedName("overview_polyline") val overviewPolyline:OverviewPolyline)
data class OverviewPolyline(val points:String)