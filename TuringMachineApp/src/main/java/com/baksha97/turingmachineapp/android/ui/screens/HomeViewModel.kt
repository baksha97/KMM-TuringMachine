package com.baksha97.turingmachineapp.android.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksha97.turingmachineapp.models.TuringMachine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.baksha97.turingmachineapp.android.Result
import java.util.*

/**
 * An internal representation of the Home route state, in a raw form
 */

interface MachineRepository {
    fun observeMachines(): Flow<List<TuringMachine>>
    fun getMachines(): Result<List<TuringMachine>>
    fun getMachine(id: String): Result<List<TuringMachine>>
}

data class HomeViewModelState(
    val machines: List<TuringMachine> = emptyList(),
//    val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
//    val isArticleOpen: Boolean = false,
//    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
)


/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel(
    private val postsRepository: MachineRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    init {
        refreshMachines()

        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            postsRepository.observeMachines().collect { machines ->
                viewModelState.value = viewModelState.value.copy(machines = machines)
            }
        }
    }

    /**
     * Refresh posts and update the UI state accordingly
     */
    fun refreshMachines() {
        // Ui state is refreshing
        viewModelState.value = viewModelState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = postsRepository.getMachines()
            viewModelState.value = when(result) {
                is Result.Success -> viewModelState.value.copy(
                    isLoading = false,
                    machines  = result.data
                )
                is Result.Error -> {
                    val errors = viewModelState.value.errorMessages + "Could not get machines."
                    viewModelState.value.copy(errorMessages = errors, isLoading = false)
                }
            }


        }
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectArticle(postId: String) {
        // Treat selecting a detail as simply interacting with it
        interactedWithArticleDetails(postId)
    }

    /**
     * Notify that the user interacted with the article details
     */
    fun interactedWithArticleDetails(postId: String) {
//        viewModelState.update {
//            it.copy(
//                selectedPostId = postId,
//                isArticleOpen = true
//            )
//        }
    }

}