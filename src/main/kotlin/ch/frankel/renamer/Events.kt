package ch.frankel.renamer

import com.vaadin.data.HasValue.*


class PatternChangedEvent(event: ValueChangeEvent<String>) {
    val pattern: String = event.value
}

class PathChangedEvent(event: ValueChangeEvent<String>) {
    val path: String = event.value
}

class ReplaceByChangedEvent(event: ValueChangeEvent<String>) {
    val replaceBy: String = event.value
}

class RenameEvent