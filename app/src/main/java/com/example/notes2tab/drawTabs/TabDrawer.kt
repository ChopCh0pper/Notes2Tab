import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.PictureDrawable
import com.caverock.androidsvg.SVG
import com.example.notes2tab.R
import java.io.InputStream

class TabDrawer(private val context: Context) {

    private var digitWidth: Int = 0
    private var digitHeight: Int = 0
    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap

    // Отрисовка табулатуры
    fun drawTab(tabData: Array<Array<String>>, offsetX: Int, offsetY: Int): Bitmap {
        // Создаем Bitmap с нужными размерами
        val canvasWidth = tabData[0].size * digitWidth
        val canvasHeight = tabData.size * digitHeight
        bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)

        for (i in tabData.indices) {
            for (j in tabData[i].indices) {
                val digit = tabData[i][j]
                val svgInputStream: InputStream = context.resources.openRawResource(getSvgResourceId(digit))
                val svg: SVG = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                drawable.setBounds(
                    offsetX + j * digitWidth,
                    offsetY + i * digitHeight,
                    offsetX + (j + 1) * digitWidth,
                    offsetY + (i + 1) * digitHeight
                )
                drawable.draw(canvas)
            }
        }
        return bitmap
    }

    // Установка размеров векторного изображения цифры
    fun setDigitSize(width: Int, height: Int) {
        digitWidth = width
        digitHeight = height
    }

    // Получение идентификатора ресурса SVG изображения
    private fun getSvgResourceId(digit: String): Int {
        return when (digit) {
            "0" -> R.raw.digit_0
            "1" -> R.raw.digit_1
            "2" -> R.raw.digit_2
            "3" -> R.raw.digit_3
            "4" -> R.raw.digit_4
            "5" -> R.raw.digit_5
            "6" -> R.raw.digit_6
            "7" -> R.raw.digit_7
            "8" -> R.raw.digit_8
            "9" -> R.raw.digit_9
            else -> R.raw.test
        }
    }
}