package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("ClassName")
internal class _ExpirableTokenNodePriorityQueueSubst<NODE: _CommonExpirableTokenNode<NODE>> : AbstractRedBlackTree<NODE>() {
    override fun _getColor(node: NODE): AbstractRedBlackTree.Color =
        node._expire_color

    override fun _getLeftChild(node: NODE): NODE? =
        node._expire_left

    override fun _getParent(node: NODE): NODE? =
        node._expire_parent

    override fun _getRightChild(node: NODE): NODE? =
        node._expire_right

    override fun _setColor(node: NODE, color: AbstractRedBlackTree.Color) {
        node._expire_color = color
    }

    override fun _setLeftChild(node: NODE, child: NODE?) {
        node._expire_left = child
    }

    override fun _setParent(node: NODE, parent: NODE?) {
        node._expire_parent = parent
    }

    override fun _setRightChild(node: NODE, child: NODE?) {
        node._expire_right = child
    }
}