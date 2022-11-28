package com.lucas.yourmarket.presentation.ui.helpers

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Top level interface for providing enum to provide resource ID
interface ResourceEnum {
    val resourceId: Int
}

sealed class StringResourceHelper {
    abstract fun produce(): String?

    interface StringEnum : ResourceEnum {
        val resId: Int

        override val resourceId: Int get() = resId

        fun toHelper() = EnumHelper(this)
        fun produce() = toHelper().produce()
    }

    data class EnumHelper(private val stringEnum: StringEnum) : StringResourceHelper(),
        KoinComponent {
        private val context: Context by inject()
        override fun produce() = context.getString(stringEnum.resourceId)
    }
}
