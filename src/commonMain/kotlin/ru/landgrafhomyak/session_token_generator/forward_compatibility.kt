@file:JvmName("ForwardCompatibilityKt")

package ru.landgrafhomyak.session_token_generator

import kotlin.jvm.JvmName
import ru.landgrafhomyak.collections.AbstractRedBlackTree


internal fun AbstractRedBlackTree<*>.clear() {
    this.root = null
}

internal val <NODE : Any> AbstractRedBlackTree<NODE>.PARENT_INITIALIZER: NODE?
    get() = null
internal val <NODE : Any> AbstractRedBlackTree<NODE>.LEFT_CHILD_INITIALIZER: NODE?
    get() = null
internal val <NODE : Any> AbstractRedBlackTree<NODE>.RIGHT_CHILD_INITIALIZER: NODE?
    get() = null
internal val AbstractRedBlackTree<*>.COLOR_INITIALIZER: AbstractRedBlackTree.Color
    get() = AbstractRedBlackTree.Color.RED
