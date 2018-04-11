package ch.frankel.renamer

import com.vaadin.ui.*
import org.vaadin.spring.events.EventBus

class FilesGrid(eventBus: EventBus.UIEventBus) : Grid<SourceTargetPair>() {

    init {
        setSizeFull()
        dataProvider = FileDataProvider(eventBus)
        addColumn(SourceTargetPair::source, { it.fileName.toString() }).caption = "Source"
        addColumn(SourceTargetPair::target, { it.fileName.toString() }).caption = "Target"
    }
}