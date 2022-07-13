package life.league.challenge.kotlin.data.model

import life.league.challenge.kotlin.domain.model.User

data class UserDTO(
    val address: Address,
    val avatar: Avatar,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserDTO.toDomainUser(): User {
    return User(
        avatar = avatar.large,
        username = username,
        id = id
    )
}

