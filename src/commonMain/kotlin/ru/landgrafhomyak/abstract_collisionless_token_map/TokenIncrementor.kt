package ru.landgrafhomyak.abstract_collisionless_token_map

interface TokenIncrementor<NODE : AbstractCollisionlessTokenMap.TokenNode<NODE>> {
    fun increase(by: ULong)

    fun isLowerThan(node: NODE): Boolean

    fun exportNode(): NODE
}