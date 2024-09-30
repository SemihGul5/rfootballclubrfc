package com.abrebo.rfootballclubrfc.data.model

import java.io.Serializable

data class Team(var team_name:String,
                var overall:String,
                var league_name:String,
                var team_image_url:String,
                var country_name:String,
                var european_cup:String,
                var stars:Double):Serializable {
}