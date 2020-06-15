package academy.mukandrew.bank.presenter.home

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

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        ioScope.launch {
            when (val response = authUseCase.getCurrentUser()) {
                is BaseResponse.Success -> {
                    currentUser.postValue(response.data)
                    requestStatements(response.data.id.toString())
                }
                is BaseResponse.Error -> {
                }
            }
        }
    }

    private suspend fun requestStatements(userId: String) {
        when (val response = useCase.getStatementList(userId)) {
            is BaseResponse.Success -> {
                statementList.postValue(response.data)
            }
            is BaseResponse.Error -> {
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