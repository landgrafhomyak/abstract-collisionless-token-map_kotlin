package ru.landgrafhomyak.session_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("ClassName")
internal class _TokenNodeRedBlackTreeSubst<NODE : _CommonTokenNode<NODE>> : AbstractRedBlackTree<NODE>() {
    override fun _getColor(node: NODE): Color =
        node._dict_color

    override fun _getLeftChild(node: NODE): NODE? =
        node._dict_left

    override fun _getParent(node: NODE): NODE? =
        node._dict_parent

    override fun _getRightChild(node: NODE): NODE? =
        node._dict_right

    override fun _setColor(node: NODE, color: Color) {
        node._dict_color = color
    }

    override fun _setLeftChild(node: NODE, child: NODE?) {
        node._dict_left = child
    }

    override fun _setParent(node: NODE, parent: NODE?) {
        node._dict_parent = parent
    }

    override fun _setRightChild(node: NODE, child: NODE?) {
        node._dict_right = child
    }
}