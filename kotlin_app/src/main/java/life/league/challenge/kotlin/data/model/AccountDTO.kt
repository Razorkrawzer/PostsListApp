package life.league.challenge.kotlin.data.model

import com.google.gson.annotations.SerializedName
import life.league.challenge.kotlin.domain.model.Account

data class AccountDTO(@SerializedName("api_key") val apiKey: String)

fun AccountDTO.toDomainAccount(): Account {
    return Account(
        apiKey = apiKey
    )
}
