package com.abrebo.rfootballclubrfc.data.model

import java.io.Serializable

data class League(var league_id:String,
                  var league_name:String,
                  var country:String,
                  var logo_url:String,
                  var flag_url:String):Serializable {
}