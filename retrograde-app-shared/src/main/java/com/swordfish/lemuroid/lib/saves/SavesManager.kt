package com.swordfish.lemuroid.lib.saves

import com.swordfish.lemuroid.lib.library.db.entity.Game
import com.swordfish.lemuroid.lib.storage.DirectoriesManager
import io.reactivex.Completable
import io.reactivex.Maybe
import java.io.File

class SavesManager(private val directoriesManager: DirectoriesManager) {
    fun getSaveRAM(game: Game): Maybe<ByteArray> = Maybe.fromCallable {
        val saveFile = getSaveFile(getSaveRAMFileName(game))
        if (saveFile.exists() && saveFile.length() > 0) {
            saveFile.readBytes()
        } else {
            null
        }
    }

    fun setSaveRAM(game: Game, data: ByteArray): Completable = Completable.fromAction {
        if (data.isEmpty())
            return@fromAction

        val saveFile = getSaveFile(getSaveRAMFileName(game))
        saveFile.writeBytes(data)
    }

    fun getSaveRAMInfo(game: Game): SaveInfo {
        val saveFile = getSaveFile(getSaveRAMFileName(game))
        val fileExists = saveFile.exists() && saveFile.length() > 0
        return SaveInfo(fileExists, saveFile.lastModified())
    }

    private fun getSaveFile(fileName: String): File {
        val savesDirectory = directoriesManager.getSavesDirectory()
        return File(savesDirectory, fileName)
    }

    /** This name should make it compatible with RetroArch so that users can freely sync saves across the two application. */
    private fun getSaveRAMFileName(game: Game) = "${game.fileName.substringBeforeLast(".")}.srm"
}
