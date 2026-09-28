package com.mmfsin.oneworld.domain.models

import androidx.compose.ui.graphics.Color
import com.mmfsin.noexcusescompose.presentation.core.theme.BlueLight
import com.mmfsin.noexcusescompose.presentation.core.theme.GreenLight
import com.mmfsin.noexcusescompose.presentation.core.theme.GreenMedium
import com.mmfsin.noexcusescompose.presentation.core.theme.OrangeLight
import com.mmfsin.noexcusescompose.presentation.core.theme.RedLight
import com.mmfsin.oneworld.R

enum class EventCategory(val id: Int, val icon: Int, val title: Int, val description: Int, val color: Color) {
    NO_SPECIFIED(
        id = 0,
        icon = R.drawable.ic_calendar,
        title = R.string.category_no_specified,
        description = R.string.category_no_specified_description,
        color = BlueLight
    ),
    SOCIAL(
        id = 1,
        title = R.string.category_social,
        icon = R.drawable.ic_calendar,
        description = R.string.category_social_description,
        color = RedLight
    ),
    ENVIRONMENTAL(
        id = 2,
        icon = R.drawable.ic_calendar,
        title = R.string.category_environmental,
        description = R.string.category_environmental_description,
        color = OrangeLight
    ),
    WORKSHOP(
        id = 3,
        icon = R.drawable.ic_calendar,
        title = R.string.category_workshop,
        description = R.string.category_workshop_description,
        color = GreenLight
    ),
    FARMING(
        id = 4,
        icon = R.drawable.ic_calendar,
        title = R.string.category_farming,
        description = R.string.category_farming_description,
        color = GreenMedium
    );

    companion object {
        fun getCategories(): List<EventCategory> = entries
        fun getCategoryById(id: Int): EventCategory = entries.find { it.id == id } ?: NO_SPECIFIED
    }
}