package ru.landgrafhomyak.abstract_token_generator

interface TokenIncrementor<NODE : AbstractTokenGenerator.TokenNode<NODE>> {
    fun increase(by: ULong)

    fun isLowerThan(node: NODE): Boolean

    fun finalize(): NODE
}