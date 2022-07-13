package life.league.challenge.kotlin.data.model

import life.league.challenge.kotlin.domain.model.Post

data class PostDTO(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun PostDTO.toDomainPost(): Post {
    return Post(
        title = title,
        body = body
    )
}