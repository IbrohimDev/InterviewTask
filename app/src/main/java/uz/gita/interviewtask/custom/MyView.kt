package uz.gita.interviewtask.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import uz.gita.interviewtask.R

class MyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    var verticalCount: Int = 0
    var horizontalCount: Int = 0

    private var xWidth = 0f
    private var xHeight = 0f
    private var _selectedColor: Int = Color.BLACK
    private var _unSelectedColor: Int = Color.WHITE
    private var _paintColor: Int = Color.parseColor("#50C878")
    private var map: Array<Array<Int>>? = null
    private var selectRectPosition: ((Int, Int) -> Unit)? = null

    private val paint = Paint().apply {
        color = _paintColor
        style = Paint.Style.FILL
    }

    init {
        val res = context.obtainStyledAttributes(null, R.styleable.MyView, 0, 0)
        _selectedColor = res.getColor(R.styleable.MyView_selectedColor, _selectedColor)
        _unSelectedColor = res.getColor(R.styleable.MyView_unSelectedColor, _unSelectedColor)
        verticalCount = res.getIndex(R.styleable.MyView_verticalCount)
        horizontalCount = res.getIndex(R.styleable.MyView_horizontalCount)
        res.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        map?.let {
            xWidth = width / (horizontalCount * 1f)
            xHeight = height / (verticalCount * 1f)
            for(i in 0 until horizontalCount){
                for(j in 0 until verticalCount){
                   paint.color = when(it[i][j]){
                       1 -> _selectedColor
                       2 -> _paintColor
                       else -> _unSelectedColor
                   }
                    canvas?.drawRect(i * xWidth, j * xHeight, (i + 1) * xWidth, (j + 1) * xHeight, paint)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                selectRectPosition?.invoke((event.x / xWidth).toInt(), (event.y / xHeight).toInt())
            }
        }
        return true
    }

    fun setSelectRectPosition(block: (Int, Int) -> Unit) {
        selectRectPosition = block
    }

    fun updateMap(map: Array<Array<Int>>) {
        this.map = map
        invalidate()
    }

}