package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PropertyName", "ClassName", "LocalVariableName")
internal open class _CommonExpirableTokenNode(
    _dict_tree: _TokenNodeRedBlackTreeSubst,
    _expire_tree: _ExpirableTokenNodePriorityQueueSubst
) : _CommonTokenNode(_dict_tree) {
    internal var _expire_parent: _CommonExpirableTokenNode? = _expire_tree.PARENT_INITIALIZER
    internal var _expire_left: _CommonExpirableTokenNode? = _expire_tree.LEFT_CHILD_INITIALIZER
    internal var _expire_right: _CommonExpirableTokenNode? = _expire_tree.RIGHT_CHILD_INITIALIZER
    internal var _expire_color: AbstractRedBlackTree.Color = _expire_tree.COLOR_INITIALIZER
}