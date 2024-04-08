package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PropertyName", "ClassName")
internal open class _CommonTokenNode(
    internal var dict_parent: _CommonTokenNode?,
    internal var dict_left: _CommonTokenNode?,
    internal var dict_right: _CommonTokenNode?,
    internal var dict_color: AbstractRedBlackTree.Color
) {
    internal var dict_leftSubtreeSize: ULong = 0u
}