package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PropertyName", "ClassName", "LocalVariableName")
internal open class _CommonExpirableTokenNode(
    dict_parent: _CommonTokenNode?,
    dict_left: _CommonTokenNode?,
    dict_right: _CommonTokenNode?,
    dict_color: AbstractRedBlackTree.Color,
    internal var expire_parent: _CommonExpirableTokenNode?,
    internal var expire_left: _CommonExpirableTokenNode?,
    internal var expire_right: _CommonExpirableTokenNode?,
    internal var expire_color: AbstractRedBlackTree.Color
) : _CommonTokenNode(dict_parent, dict_left, dict_right, dict_color)