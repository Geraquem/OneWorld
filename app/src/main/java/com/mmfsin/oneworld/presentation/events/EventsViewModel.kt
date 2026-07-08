package com.mmfsin.oneworld.presentation.events

import com.mmfsin.oneworld.domain.usecases.GetEventsUseCase
import com.mmfsin.oneworld.domain.usecases.GetLatestCategoryEventsUseCase
import com.mmfsin.oneworld.domain.usecases.UpdateLatestCategoryEventsUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getLatestCategoryEventsUseCase: GetLatestCategoryEventsUseCase,
    private val updateLatestCategoryEventsUseCase: UpdateLatestCategoryEventsUseCase,
    private val getEventsUseCase: GetEventsUseCase,
) : BaseViewModel<EventsStates>(EventsStates()) {

    init {
        getLatestCategory()
    }

    fun getLatestCategory() {
        executeUseCase(
            { getLatestCategoryEventsUseCase() },
            { category ->
                _uiState.update { it.copy(searchingCategory = category) }
                getEvents(category)
            },
            {}
        )
    }

    fun updateSearchingCategory(newCategory: Int) {
        executeUseCase(
            { updateLatestCategoryEventsUseCase(newCategory) },
            {
                _uiState.update {
                    it.copy(
                        searchingCategory = newCategory,
                        categoryDialogVisibility = false
                    )
                }
                getEvents(newCategory)
            },
            {},
        )
    }

    fun getEvents(category: Int) {
        executeUseCase(
            { getEventsUseCase(category) },
            { events ->
                _uiState.update {
                    it.copy(
                        events = events ?: emptyList(),
                        isLoading = false
                    )
                }
            },
            {}
        )
    }

    fun categoryDialogVisibility(value: Boolean) = _uiState.update { it.copy(categoryDialogVisibility = value) }
}