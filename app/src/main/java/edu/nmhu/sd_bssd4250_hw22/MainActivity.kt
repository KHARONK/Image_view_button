package edu.nmhu.sd_bssd4250_hw22

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat


class MainActivity : AppCompatActivity() {

    private lateinit var LinearLayout : LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //layout to hold only red images
        val redLinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                 0, //no height because vertical layout will

                0.2f)//change the height for you based on the weight

            //Add the ImageView to the layout.
            //addView(makeButton( "red")) // add the red image
        }

        //layout to hold only blue image
        val blueLinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.7f) //change the height for you based on teh weight
            //Add the ImageView to the layout.
            addView(makeButton(  "blue")) // add the blue image
        }

        //not textViewCompat, even though it exists.
        //TextViewCompat is a helper class for TextView unlike LinearLayoutCompat
        var scoreNum = 9
        val Score = TextView( this ).apply {
            text = scoreNum.toString()
            val metrics: DisplayMetrics = Resources.getSystem().getDisplayMetrics()
            val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,128f, metrics)
            textSize = pixels
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.LTGRAY)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
            (layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.CENTER_IN_PARENT)
        }

        val relativeLayout = RelativeLayout( this ).apply{
            setBackgroundColor(Color.RED)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0, //no height because vertical layout will
                0.6f) //change the height for you based on the weight
            addView(Score)
        }

        //create a constraintLayout in which to add the ImageView
        LinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.BLUE)
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT)

            weightSum = 1.0f
            //Add the layouts.
            addView(redLinearLayout)
            addView(blueLinearLayout)
            addView(relativeLayout)
        }

        //Set the layout as the content view.
        setContentView(LinearLayout)
    }

    fun makeButton(Color: String): ImageButton
    {
        //no late inits needed
        //the if else will run and retuen to the button val
        //whatever the proper results is.

        val button = if(Color == "red")
        {
            //this whole thing below is one constructor call technically
            ImageButton(this).apply {
                setImageResource(R.drawable.red)
                background = null
                contentDescription = "Red Dot image" //resources.getString(R.string.my_image_desc)
                setOnClickListener(View.OnClickListener { view ->
                    //(view.parent as LinearLayoutCompat).remove(view)
                    (view.parent as LinearLayoutCompat).addView(makeButton( "blue"))
                })
                //set the ImageView bounds to match the Drawable's dimension
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0 ,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f)
            }
        }
        else
        {
            //must be blue
            //this whole thing below is one constructor call technically.
            ImageButton(this).apply {
                setImageResource(R.drawable.blue)
                background = null
                contentDescription = "Blue Dot image" //resources.getString(R.string.my_image_desc)
                setOnClickListener{
                    (it.parent as LinearLayoutCompat).removeView(it)
                }

                //set the ImageView bounds to match the Drawable's dimension
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f)
            }
        }
        return button
    }
}