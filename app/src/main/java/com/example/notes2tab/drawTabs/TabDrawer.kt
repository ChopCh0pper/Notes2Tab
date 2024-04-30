
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface

class TabDrawer(private val context: Context) {

    private lateinit var paint: Paint
    private var maxCharsPerLine = 0
    private var prevMaxChars = 0

    // Отрисовка табулатуры
    fun drawTab(tabData: List<String>, screenWidth: Int): Bitmap {
        val lineHeight = 80 // Высота строки текста
        val linesPerBar = 6 // Количество строк на один такт

        maxCharsPerLine = estimateMaxCharsPerLine(screenWidth)

        val bitmapHeight = tabData.size * lineHeight * linesPerBar

        val bitmap = Bitmap.createBitmap(screenWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 60f
        paint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        var y = lineHeight

        for (lineIndex in tabData.indices) {
            val line = tabData[lineIndex]


            val parts = splitString(line, maxCharsPerLine)
            for (part in parts) {
                canvas.drawText(part, 0f, y.toFloat(), paint)
                y += lineHeight
            }

            // Добавляем пустые строки между тактами
            if (lineIndex % linesPerBar == linesPerBar - 1) {
                y += lineHeight * 2 // Добавляем две пустые строки
            }
        }

        return bitmap
    }

    private fun estimateMaxCharsPerLine(screenWidth: Int): Int {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 60f
        paint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)


        val textBounds = Rect()

        var maxChars = 100
        var textWidth: Float


        while (true) {
            val testText = "X".repeat(maxChars)
            paint.getTextBounds(testText, 0, testText.length, textBounds)
            textWidth = textBounds.width().toFloat()

            if (textWidth >= screenWidth) {
                break
            }

            maxChars += 100
        }

        while (true) {
            val testText = "X".repeat(maxChars)
            paint.getTextBounds(testText, 0, testText.length, textBounds)
            textWidth = textBounds.width().toFloat()

            if (textWidth >= screenWidth) {
                maxChars--
            } else {
                return maxChars
            }
        }
    }

    private fun splitString(input: String, maxLength: Int): List<String> {
        val parts = mutableListOf<String>()
        var index = 0
        while (index < input.length) {
            parts.add(input.substring(index, Math.min(index + maxLength, input.length)))
            index += maxLength
        }
        return parts
    }
}