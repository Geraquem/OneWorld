package com.mmfsin.oneworld.domain.models

import com.mmfsin.oneworld.R

enum class EventType(val id: Int, val icon: Int, val title: Int, val description: Int) {
    NO_SPECIFIED(
        id = 0,
        icon = R.drawable.ic_calendar,
        title = R.string.category_no_specified,
        description = R.string.category_no_specified_description
    ),
    SOCIAL(
        id = 1,
        title = R.string.category_social,
        icon = R.drawable.ic_calendar,
        description = R.string.category_social_description
    ),
    ENVIRONMENTAL(
        id = 2,
        icon = R.drawable.ic_calendar,
        title = R.string.category_environmental,
        description = R.string.category_environmental_description
    ),
    WORKSHOP(
        id = 3,
        icon = R.drawable.ic_calendar,
        title = R.string.category_workshop,
        description = R.string.category_workshop_description
    ),
    FARMING(
        id = 4,
        icon = R.drawable.ic_calendar,
        title = R.string.category_farming,
        description = R.string.category_farming_description
    );

    companion object {
        fun getCategories(): List<EventType> = entries
        fun getCategoryById(id: Int): EventType = entries.find { it.id == id } ?: NO_SPECIFIED
    }
}