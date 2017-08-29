package fr.hadrienmp.magic.lib

import java.io.File
import java.net.URL
import java.util.stream.Collectors

class ClasspathFile(path: String) {

    private val url: URL

    init {
        val url = ClassLoader.getSystemResource(path)
        if (url == null) {
            val resourcePath = getResourcesRootPath()
            createMissingDirs(resourcePath, path)
            val file = File(resourcePath, path)
            file.printWriter().print("")
            this.url = file.toURI().toURL()
        } else {
            this.url = url
        }
    }

    private fun createMissingDirs(resourcePath: String, path: String) {
        val parentDirectory = path.substring(0, path.lastIndexOf("/") + 1)
        File(resourcePath + "/" + parentDirectory).mkdirs()
    }

    private fun getResourcesRootPath(): String {
        return ClasspathFile("resources-root.txt").read()
    }

    fun read(): String {
        return url.openStream()
                .bufferedReader()
                .lines()
                .collect(Collectors.joining("\n"))
    }

    fun write(content: String) {
        File(url.toURI())
                .printWriter()
                .use {out -> out.print(content)}
    }
}