package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("ClassName")
internal class _ExpirableTokenNodePriorityQueueSubst : AbstractRedBlackTree<_CommonExpirableTokenNode>() {
    override fun _getColor(node: _CommonExpirableTokenNode): Color =
        node.expire_color

    override fun _getLeftChild(node: _CommonExpirableTokenNode): _CommonExpirableTokenNode? =
        node.expire_left

    override fun _getParent(node: _CommonExpirableTokenNode): _CommonExpirableTokenNode? =
        node.expire_parent

    override fun _getRightChild(node: _CommonExpirableTokenNode): _CommonExpirableTokenNode? =
        node.expire_right

    override fun _setColor(node: _CommonExpirableTokenNode, color: Color) {
        node.expire_color = color
    }

    override fun _setLeftChild(node: _CommonExpirableTokenNode, child: _CommonExpirableTokenNode?) {
        node.expire_left = child
    }

    override fun _setParent(node: _CommonExpirableTokenNode, parent: _CommonExpirableTokenNode?) {
        node.expire_parent = parent
    }

    override fun _setRightChild(node: _CommonExpirableTokenNode, child: _CommonExpirableTokenNode?) {
        node.expire_right = child
    }
}