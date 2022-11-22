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

    var formatArgs: Array<Any>? = null

    interface StringEnum : ResourceEnum {
        val resId: Int

        override val resourceId: Int get() = resId

        fun toHelper() = EnumHelper(this)
        fun produce() = toHelper().produce()
    }

    data class EnumHelper(private val stringEnum: StringEnum) : StringResourceHelper(),
        KoinComponent {
        private val context: Context by inject()
        override fun produce() = formatArgs?.let { argList ->
            context.getString(stringEnum.resourceId, *getStringParams(argList))
        } ?: run {
            context.getString(stringEnum.resourceId)
        }

        private fun getStringParams(args: Array<Any>): Array<String> =
            args.map {
                try {
                    when (it) {
                        is StringEnum -> context.getString(it.resourceId)
                        is StringResourceHelper -> it.produce() ?: ""
                        else -> it.toString()
                    }
                } catch (e: ClassCastException) {
                    it.toString()
                }
            }.toTypedArray()
    }
}