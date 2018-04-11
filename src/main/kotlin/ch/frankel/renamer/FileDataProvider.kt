package ch.frankel.renamer

import com.vaadin.data.provider.*
import com.vaadin.ui.*
import org.vaadin.spring.events.EventBus
import org.vaadin.spring.events.annotation.EventBusListenerMethod
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.*


class FileDataProvider(eventBus: EventBus) : AbstractDataProvider<SourceTargetPair, Any>() {

    init {
        eventBus.subscribe(this)
    }

    private var path: Path? = null
        set(value) {
            if (Files.isDirectory(value)) {
                field = value
                listFiles()
            } else {
                files.clear()
                field = null
            }
        }

    private var pattern: String = ""
    private var replaceBy: String = ""

    private val files = mutableListOf<SourceTargetPair>()

    @EventBusListenerMethod
    @Suppress("UNUSED")
    fun onPathChanged(event: PathChangedEvent) {
        path = Paths.get(event.path)
        refreshAll()
    }

    @EventBusListenerMethod
    @Suppress("UNUSED")
    fun onPatternChanged(event: PatternChangedEvent) {
        pattern = event.pattern
        updateFiles()
        refreshAll()
    }

    @EventBusListenerMethod
    @Suppress("UNUSED")
    fun onReplaceByChanged(event: ReplaceByChangedEvent) {
        replaceBy = event.replaceBy
        updateFiles()
        refreshAll()
    }

    @EventBusListenerMethod
    @Suppress("UNUSED")
    fun onRenamePressed(@Suppress("UNUSED_PARAMETER") event: RenameEvent) {
        files.forEach { it.rename() }
        Notification.show("Files renamed ")
        listFiles()
        refreshAll()
    }

    override fun isInMemory() = true
    override fun size(query: Query<SourceTargetPair, Any>) = files.size
    override fun fetch(query: Query<SourceTargetPair, Any>): Stream<SourceTargetPair> = files.stream().sorted()

    private fun listFiles() {
        val isVisibleFile: (Path) -> Boolean = { !Files.isDirectory(it) && !Files.isHidden(it) }
        files.clear()
        files.addAll(Files.newDirectoryStream(path, isVisibleFile)
                .map { SourceTargetPair(it, pattern, replaceBy) }
                .toList())
    }

    private fun updateFiles() {
        if (pattern.isNotBlank() && replaceBy.isNotBlank()) {
            files.forEach {
                it.pattern = pattern
                it.replaceBy = replaceBy
            }
        }
    }
}
