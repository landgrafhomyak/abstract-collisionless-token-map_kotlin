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

    override fun _rotateLeftPostUpdate(oldTop: NODE, newTop: NODE) {
        oldTop._dict_wholeSubtreeSize -= newTop._dict_wholeSubtreeSize

        val midSubtreeSize = newTop._dict_left?._dict_wholeSubtreeSize ?: 0u
        newTop.dict_leftSubtreeSize -= midSubtreeSize
        newTop._dict_wholeSubtreeSize -= midSubtreeSize

        oldTop._dict_wholeSubtreeSize += midSubtreeSize

        newTop.dict_leftSubtreeSize += oldTop._dict_wholeSubtreeSize
        newTop._dict_wholeSubtreeSize += oldTop._dict_wholeSubtreeSize
    }

    override fun _rotateRightPostUpdate(oldTop: NODE, newTop: NODE) {
        oldTop._dict_wholeSubtreeSize -= newTop._dict_wholeSubtreeSize
        oldTop.dict_leftSubtreeSize -= newTop._dict_wholeSubtreeSize

        val midSubtreeSize = newTop._dict_right?._dict_wholeSubtreeSize ?: 0u
        newTop._dict_wholeSubtreeSize -= midSubtreeSize

        oldTop._dict_wholeSubtreeSize += midSubtreeSize
        oldTop.dict_leftSubtreeSize += midSubtreeSize

        newTop.dict_leftSubtreeSize += oldTop._dict_wholeSubtreeSize
    }

    override fun _swapUserData(node1: NODE, node2: NODE) {
        val wholeSize = node1._dict_wholeSubtreeSize
        node1._dict_wholeSubtreeSize = node2._dict_wholeSubtreeSize
        node2._dict_wholeSubtreeSize = wholeSize

        val leftSize = node1.dict_leftSubtreeSize
        node1.dict_leftSubtreeSize = node2.dict_leftSubtreeSize
        node2.dict_leftSubtreeSize = leftSize
    }

    override fun _unlinkPreUpdate(unlinkedNode: NODE) {
        var ptr = unlinkedNode
        while (true) {
            val parent = ptr._dict_parent ?: return
            if (ptr === parent._dict_left)
                parent.dict_leftSubtreeSize--
            parent._dict_wholeSubtreeSize--
            ptr = parent
        }
    }
}