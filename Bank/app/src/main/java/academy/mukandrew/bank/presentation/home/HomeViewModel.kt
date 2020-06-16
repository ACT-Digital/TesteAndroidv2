package academy.mukandrew.bank.presentation.home

import academy.mukandrew.bank.commons.extensions.ioScope
import academy.mukandrew.bank.data.models.BaseResponse
import academy.mukandrew.bank.domain.models.Statement
import academy.mukandrew.bank.domain.models.UserInfo
import academy.mukandrew.bank.domain.useCases.AuthUseCase
import academy.mukandrew.bank.domain.useCases.StatementUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: StatementUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    val statementList = MutableLiveData<List<Statement>>()
    val currentUser = MutableLiveData<UserInfo>()
    val logoutResult = MutableLiveData<Boolean>()
    val statementListResult = MutableLiveData<Boolean>()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        ioScope.launch {
            getCurrentUserCallback(authUseCase.getCurrentUser())
        }
    }

    private fun getCurrentUserCallback(response: BaseResponse<UserInfo>) {
        when (response) {
            is BaseResponse.Success -> {
                currentUser.postValue(response.data)
                requestStatements(response.data.id.toString())
            }
            is BaseResponse.Error -> {
                postLogout()
            }
        }
    }

    private fun requestStatements(userId: String) {
        ioScope.launch { statementListCallback(useCase.getStatementList(userId)) }
    }

    private fun statementListCallback(response: BaseResponse<List<Statement>>) {
        when (response) {
            is BaseResponse.Success -> {
                statementList.postValue(response.data)
            }
            is BaseResponse.Error -> {
                statementListResult.postValue(true)
            }
        }
    }

    fun postLogout() {
        ioScope.launch {
            authUseCase.postLogout().run {
                logoutResult.postValue(true)
            }
        }
    }
}