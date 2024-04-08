package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PropertyName", "ClassName", "LocalVariableName")
internal open class _CommonExpirableTokenNode<NODE: _CommonExpirableTokenNode<NODE>>(
    _dict_tree: _TokenNodeRedBlackTreeSubst<NODE>,
    _expire_tree: _ExpirableTokenNodePriorityQueueSubst<NODE>
) : _CommonTokenNode<NODE>(_dict_tree) {
    internal var _expire_parent: NODE? = _expire_tree.PARENT_INITIALIZER
    internal var _expire_left: NODE? = _expire_tree.LEFT_CHILD_INITIALIZER
    internal var _expire_right: NODE? = _expire_tree.RIGHT_CHILD_INITIALIZER
    internal var _expire_color: AbstractRedBlackTree.Color = _expire_tree.COLOR_INITIALIZER
}