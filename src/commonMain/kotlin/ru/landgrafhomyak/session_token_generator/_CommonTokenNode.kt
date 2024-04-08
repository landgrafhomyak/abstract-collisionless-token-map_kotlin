package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PropertyName", "ClassName", "LocalVariableName")
internal open class _CommonTokenNode<NODE: _CommonTokenNode<NODE>>(
    _dict_tree: _TokenNodeRedBlackTreeSubst<NODE>
) {
    internal var dict_leftSubtreeSize: ULong = 0u
    internal var _dict_parent: NODE? = _dict_tree.PARENT_INITIALIZER
    internal var _dict_left: NODE? = _dict_tree.LEFT_CHILD_INITIALIZER
    internal var _dict_right: NODE? = _dict_tree.RIGHT_CHILD_INITIALIZER
    internal var _dict_color: AbstractRedBlackTree.Color = _dict_tree.COLOR_INITIALIZER
}