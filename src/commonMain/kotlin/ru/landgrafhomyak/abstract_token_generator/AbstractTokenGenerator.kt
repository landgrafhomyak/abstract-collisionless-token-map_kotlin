package ru.landgrafhomyak.abstract_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree

@Suppress("PrivatePropertyName")
class AbstractTokenGenerator<NODE : AbstractTokenGenerator.TokenNode<NODE>> {
    @Suppress("PropertyName", "LeakingThis")
    open class TokenNode<NODE : TokenNode<NODE>>(
        owner: AbstractTokenGenerator<NODE>
    ) {
        internal var dict_leftSubtreeSize: ULong = 0u
        internal var _dict_wholeSubtreeSize: ULong = 1u
        internal var _dict_parent: NODE? = owner._dict_tree.PARENT_INITIALIZER
        internal var _dict_left: NODE? = owner._dict_tree.LEFT_CHILD_INITIALIZER
        internal var _dict_right: NODE? = owner._dict_tree.RIGHT_CHILD_INITIALIZER
        internal var _dict_color: AbstractRedBlackTree.Color = owner._dict_tree.COLOR_INITIALIZER

        val tokensBstLeftChild by this::_dict_left
        val tokensBstRightChild by this::_dict_right
    }

    private val _dict_tree = _TokenNodeRedBlackTreeSubst<NODE>()

    val tokensBstRoot: NODE? by this._dict_tree::root

    @Suppress("MemberVisibilityCanBePrivate")
    var tokensLinkedCount: ULong = 0u
        private set

    fun link(incrementor: TokenIncrementor<NODE>): NODE {
        val root = this._dict_tree.root

        if (root == null) {
            val node = incrementor.exportNode()
            this._dict_tree.root = node
            this._dict_tree.balanceAfterLinking(node)
            return node
        }

        var ptr: NODE = root
        val node: NODE
        while (true) {
            if (incrementor.isLowerThan(ptr)) {
                val left = ptr._dict_left
                if (left != null) {
                    ptr = left
                    continue
                }

                node = incrementor.exportNode()
                ptr._dict_left = node
                break
            } else {
                incrementor.increase(ptr.dict_leftSubtreeSize)
                val right = ptr._dict_right
                if (right != null) {
                    ptr = right
                    continue
                }

                node = incrementor.exportNode()
                ptr._dict_right = node
                break
            }
        }

        this._dict_tree.increaseSubtreeSizes(node)
        this._dict_tree.balanceAfterLinking(node)
        this.tokensLinkedCount++
        return node
    }

    fun unlink(node: NODE) {
        this._dict_tree.unlink(node)
        this.tokensLinkedCount--
    }

    fun clear() {
        this._dict_tree.clear()
        this.tokensLinkedCount = 0u
    }

    @Suppress("ClassName")
    private class _TokenNodeRedBlackTreeSubst<NODE : TokenNode<NODE>> : AbstractRedBlackTree<NODE>() {
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

        fun increaseSubtreeSizes(linkedNODE: NODE) {
            var ptr = linkedNODE
            while (true) {
                val parent = ptr._dict_parent ?: return
                if (ptr === parent._dict_left)
                    parent.dict_leftSubtreeSize++
                parent._dict_wholeSubtreeSize++
                ptr = parent
            }
        }
    }
}