package ch.frankel.renamer

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.*
import kotlin.properties.Delegates

class SourceTargetPair(val source: Path) : Comparable<SourceTargetPair> {

    constructor(source: Path, pattern: String, replaceBy: String) : this(source) {
        this.pattern = pattern
        this.replaceBy = replaceBy
    }

    override fun compareTo(other: SourceTargetPair) = source.compareTo(other.source)

    var target: Path = source

    var pattern: String by Delegates.observable("") { _, _, _ ->
        refreshState()
    }

    var replaceBy: String by Delegates.observable("") { _, _, _ ->
        refreshState()
    }

    private fun refreshState() {
        val fileName = source.fileName.toString()
        val matcher = Pattern.compile(pattern).matcher(fileName)
        val replaced = matcher.replaceAll(replaceBy)
        val parent = source.parent
        target = parent.resolve(replaced)
    }

    fun rename() {
        Files.move(source, target)
    }
}