package com.rio.responsi_mobile.model

data class TravelNote(
    var id: String = "",  // ID for Firebase
    var title: String = "",
    var description: String = "",
    var location: String = "",
    var date: String = ""
)