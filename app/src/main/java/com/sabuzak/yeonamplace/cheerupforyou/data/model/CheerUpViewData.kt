package com.sabuzak.yeonamplace.cheerupforyou.data.model

data class CheerUpViewData (
    var text : String,
    var text_size  :Int?,
    var background_color :Int?,
    var text_color :Int?,
    var direction :Int?,
    var speed :Int?,
    var font :Int?,
    //효과가 없으면 0 있으면 1
    var effect0 :Int?,
    var effect1 :Int?,
    var effect2 :Int?,
    var effect3 :Int?

)