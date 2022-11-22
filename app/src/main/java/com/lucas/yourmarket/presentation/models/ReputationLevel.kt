package com.lucas.yourmarket.presentation.models

import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.helpers.StringResourceHelper

enum class UserReputationLevels(override var resId: Int) : StringResourceHelper.StringEnum {
    USER_REPUTATION_0(R.string.user_reputation_0),
    USER_REPUTATION_1(R.string.user_reputation_1),
    USER_REPUTATION_2(R.string.user_reputation_2),
    USER_REPUTATION_3(R.string.user_reputation_3),
    USER_REPUTATION_4(R.string.user_reputation_4),
    USER_REPUTATION_5(R.string.user_reputation_5)
}
