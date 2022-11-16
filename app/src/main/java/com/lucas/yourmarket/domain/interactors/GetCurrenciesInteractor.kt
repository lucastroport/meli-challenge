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
        val currencies = currencyRepository.fetchCurrencies().isEmpty()
        return if (currencies) {
            Status.Success()
        } else {
            Status.Failure.GeneralFailure("Currencies could not be fetched")
        }
    }
}
