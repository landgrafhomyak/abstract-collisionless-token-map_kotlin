package ru.landgrafhomyak.abstract_token_generator

import ru.landgrafhomyak.collections.AbstractRedBlackTree
import ru.landgrafhomyk.collections.AbstractRedBlackPriorityQueue

@Suppress("PrivatePropertyName")
class AbstractExpirableTokenGenerator<NODE : AbstractExpirableTokenGenerator.ExpirableTokenNode<NODE>> {

    private val _token_gen = AbstractTokenGenerator<NODE>()

    @Suppress("PropertyName")
    open class ExpirableTokenNode<NODE : ExpirableTokenNode<NODE>>(
        owner: AbstractExpirableTokenGenerator<NODE>,
        expirationTime: ULong
    ) : AbstractTokenGenerator.TokenNode<NODE>(owner._token_gen) {
        var expirationTime: ULong = expirationTime
            internal set

        internal var _expire_parent: NODE? = owner._expire_tree.PARENT_INITIALIZER
        internal var _expire_left: NODE? = owner._expire_tree.LEFT_CHILD_INITIALIZER
        internal var _expire_right: NODE? = owner._expire_tree.RIGHT_CHILD_INITIALIZER
        internal var _expire_color: AbstractRedBlackTree.Color = owner._expire_tree.COLOR_INITIALIZER
    }

    private val _expire_tree = ExpirableTokenNodePriorityQueueSubst<NODE>()

    fun link(incrementor: TokenIncrementor<NODE>): NODE {
        val node = this._token_gen.link(incrementor)
        this._expire_tree.link(node)
        return node
    }

    fun unlink(node: NODE) {
        this._expire_tree.unlink(node)
        this._token_gen.unlink(node)
    }

    fun clear() {
        this._token_gen.clear()
        this._expire_tree.clear()
    }

    val closestTokenForExpiration: NODE?
        get() = this._expire_tree.maxPriorityNodeOrNull

    fun changeExpirationTime(node: NODE, newTime: ULong) {
        this._expire_tree.unlink(node)
        node.expirationTime = newTime
        this._expire_tree.link(node)
    }


    private class ExpirableTokenNodePriorityQueueSubst<NODE : ExpirableTokenNode<NODE>> : AbstractRedBlackPriorityQueue<NODE>() {
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

        override fun _hasHigherPriority(node: NODE, than: NODE): Boolean =
            node.expirationTime < than.expirationTime
    }
}