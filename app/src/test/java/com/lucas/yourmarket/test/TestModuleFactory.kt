package com.lucas.yourmarket.test

import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.Address
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.domain.model.User
import com.lucas.yourmarket.domain.model.response.asSuccess
import com.lucas.yourmarket.domain.usecases.*
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

object TestModuleFactory {

    private val getCurrenciesUseCase = mock<GetCurrenciesUseCase> {
        onBlocking { execute(any()) } doAnswer {
            BaseUseCase.Response().asSuccess()
        }
    }

    private val fetchProductUseCase = mock<ProductUseCase> {
        onBlocking { execute(any()) } doAnswer {
            ProductUseCase.Response(
                ProductWithCurrencyQuery(
                    id = "1",
                    title = "testTitle",
                    price = 1.00,
                    condition = "new",
                    thumbnail = "test thumbnail",
                    warranty = "test warranty",
                    currency = "$",
                    pictures = emptyList(),
                    freeShipping = false,
                    sellerId = 1,
                    soldQuantity = 1
                )
            ).asSuccess()
        }
    }

    private val fetchUserUseCase = mock<UserUseCase> {
        onBlocking { execute(any()) } doAnswer {
            UserUseCase.Response(
                User(
                    id = 1,
                    nickname = "some nickname",
                    address = Address(city = "some city", state = "some state"),
                    sellerReputation = SellerReputation(levelId = "some level", powerSellerStatus = "power seller")
                )
            ).asSuccess()
        }
    }

    private val fetchStateUseCase = mock<StateUseCase> {
        onBlocking { execute(any()) } doAnswer {
            StateUseCase.Response(
                State(
                    id = "id test",
                    name = "name test"
                )
            ).asSuccess()
        }
    }

    fun produce() = module {
        single<DispatcherProvider> { TestDispatcherProvider() }
        single { getCurrenciesUseCase }
        single { fetchProductUseCase }
        single { fetchStateUseCase }
        single { fetchUserUseCase }
    }
}