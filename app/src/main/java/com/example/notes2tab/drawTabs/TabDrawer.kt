import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log

class TabDrawer(private val context: Context) {

    private val textSize = 45f // Размер текста
    private val textColor = Color.BLACK // Цвет текста
    private val paint = Paint()

    init {
        paint.textSize = textSize
        paint.color = textColor
        paint.typeface = Typeface.MONOSPACE // Установка моноширинного шрифта
    }

    fun drawTab(tabData: List<String>, screenWidth: Int): Bitmap {
        val lineHeight = textSize
        val bitmapHeight = 2000 // Исходная высота битмапа (можно настроить по вашему усмотрению)
        var y = textSize

        val bitmap = Bitmap.createBitmap(screenWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val maxCharsPerLine = findMaxCharsPerLine(screenWidth)

        // Список для хранения обрезанных частей строк
        val remainingLines = mutableListOf<String>()

        // Список для хранения кусков строк в зависимости от их индекса
        val lineChunks = mutableListOf<MutableList<String>>()

        // Инициализация списков для каждой строки
        for (i in tabData.indices) {
            lineChunks.add(mutableListOf())
        }

        // Обработка строк и сбор кусков в списки
        for ((index, line) in tabData.withIndex()) {
            var remainingLine = line

            while (remainingLine.isNotEmpty()) {
                val chunkSize = Math.min(remainingLine.length, maxCharsPerLine)
                var chunk = remainingLine.take(chunkSize)

                if (chunk.length == maxCharsPerLine && remainingLine.length > chunkSize) {
                    chunk += " " // Добавляем пробел, если это не конец строки
                }

                // Добавление куска в соответствующий список
                lineChunks[index].add(chunk)

                remainingLine = remainingLine.drop(chunkSize)
            }
        }

        // Отрисовка кусков поочередно
        var lineCounter = 0
        for (i in 0 until maxCharsPerLine) {
            for (line in lineChunks) {
                if (i < line.size) {
                    val chunk = line[i]

                    if (y + lineHeight <= bitmapHeight) {
                        canvas.drawText(chunk, 0f, y, paint)
                    } else {
                        remainingLines.add(chunk)
                    }

                    y += lineHeight
                    lineCounter++

                    // Вставляем пробел после каждых 6 строк
                    if (lineCounter % 6 == 0) {
                        y += lineHeight
                    }
                }
            }
        }
        return bitmap
    }

    private fun findMaxCharsPerLine(screenWidth: Int): Int {
        var low = 1
        var high = screenWidth

        while (low < high) {
            val mid = low + (high - low) / 2
            val testString = "X".repeat(mid) // Создаем строку из mid символов
            val textWidth = paint.measureText(testString) // Измеряем ширину этой строки
            Log.d("TabDrawer", "Test string: $testString, Text width: $textWidth, Screen width: $screenWidth")

            if (textWidth > screenWidth) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        return low
    }
}
