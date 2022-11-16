package com.lucas.yourmarket.domain.interactors

import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.repository.interfaces.CurrencyRepository
import com.lucas.yourmarket.domain.usecases.BaseUseCase
import com.lucas.yourmarket.domain.usecases.GetCurrenciesUseCase
import org.koin.core.component.inject

class GetCurrenciesInteractor : GetCurrenciesUseCase {

    // DI
    private val currencyRepository: CurrencyRepository by inject()

    override suspend fun execute(request: BaseUseCase.Request?): Status<BaseUseCase.Response> {
        currencyRepository.fetchCurrencies()
        return Status.Success()
    }
}
