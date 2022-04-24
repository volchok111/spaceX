package com.example.spacexserv.model.ships

data class Ships(
    val abs: Int,
    val active: Boolean,
    val attempted_landings: Int,
    val `class`: Int,
    val course_deg: Int,
    val home_port: String,
    val id: String,
    val image: String,
    val imo: Int,
    val missions: List<Mission>,
    val mmsi: Int,
    val model: String,
    val name: String,
    val position: Position,
    val roles: List<String>,
    val speed_kn: Int,
    val status: String,
    val successful_landings: Int,
    val type: String,
    val url: String,
    val weight_kg: Int,
    val weight_lbs: Int,
    val year_built: Int
) {

    companion object {
        val sortByYear = object : Comparator<Ships> {
            override fun compare(p0: Ships?, p1: Ships?): Int {
                if (p0 != null && p1 != null)
                    return p0.year_built.compareTo(p1.year_built)
                return 0
            }

        }
    }
}