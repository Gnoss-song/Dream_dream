package com.basic.resource

import android.annotation.SuppressLint
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParserException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private val DEBUG_TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * layout 설정을 inflation 시킴
         */
        setContentView(R.layout.main)

        /*
         * 현재 어플리케이션에 선언된 자원을 핸들링 할 수 있는 관리 객체를 리턴 받는다.
         */
        val resMgObj: Resources = this.resources
        /*
         * 단순 자원을 안드로이드 코드에서 활용하는 방법
         */
         simpleResourcesDisplay(resMgObj)
        /*
         * 이미지 및 애니메이션 자원 사용 방법
         */drawImageAnimationResoucesHandling(resMgObj)
        //
        /**
         * /res/xml/customers.xml 핸들링
         * 여기선언된 xml화일은 스트림 기반이 아닌, xml 형식의 문서이므로 XML Parser 사용
         */
        parsingResXMLData(resMgObj)
    }

    private fun simpleResourcesDisplay(resMgObj: Resources) {
        //문자열을 코드로 가져오는 가장 단순한 방법
        val helloStr: String = resMgObj.getString(R.string.hello)
        Log.d(DEBUG_TAG, "R.string.hello 값은  : $helloStr")

        //단순 문자열 자원이 아닌 스타일을 유지하기 위해선 getText()를 사용
        val boldTagHello: CharSequence = resMgObj.getText(R.string.boldHello)
        Log.d(DEBUG_TAG, "R.string.boldHello의 표시 값은 : $boldTagHello")

        //getString(int) 메소드로 그대로 받아 내면 자원 설정 값 그대로 출력 함
        val simpleFormatStr: String = resMgObj.getString(R.string.simpleFormatString)
        Log.d(DEBUG_TAG, "R.string.simpleFormatString의 표시값은 : $simpleFormatStr")

        //서식 문자열로 사용하려면 치환할 값을 escape 시킴
        val formatStringWithNumbers: String = resMgObj.getString(R.string.formatStringWithNumbers)
        val passMessage = TextUtils.htmlEncode("PASS")
        val appliedMessage = String.format(formatStringWithNumbers, 75, 87, passMessage)
        Log.d(DEBUG_TAG, "포맷이 적용된 서식 문자열 출력값은 : $appliedMessage")

        //array 사용
        val arraySizes: Array<String> = resMgObj.getStringArray(R.array.size)
        run {
            var i = 0
            val arrSize = arraySizes.size
            while (i < arrSize) {
                Log.d(DEBUG_TAG, "R.array.size[" + i + "] 값은  : " + arraySizes[i])
                i++
            }
        }
        val pyoInfos: Array<String> = resMgObj.getStringArray(R.array.pyoinsooInfo)
        var i = 0
        val pyoArrSize = pyoInfos.size
        while (i < pyoArrSize) {
            Log.d(DEBUG_TAG, "R.array.pyoinsooInfo[" + i + "] 값은  : " + pyoInfos[i])
            i++
        }

        /**
         * dimension 설정값 사용
         * dimension의 실제 코드상에서의 값은 float value가 됨
         */
        val myTextPointSize: Float = resMgObj.getDimension(R.dimen.textPointSize)
        Log.d(DEBUG_TAG, " R.dimen.textPointSize 값은  : $myTextPointSize")
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun drawImageAnimationResoucesHandling(resMgObj: Resources) {
        /**
         * /res/values/drawables.xml 에 도형에 설정할 값들 알아오기
         * 여기 선언된 값은 그래픽 관련 객체를 얻어야 함
         */
        val valuesDrawable = resMgObj.getDrawable(R.drawable.redFillRect, null) as ColorDrawable
        Log.d(DEBUG_TAG, " R.drawable.redFillRect's Resource Object 값 : " +
                valuesDrawable.toString())
        /**
         * layout 정의된 Widget 및 View 객체의 레퍼런스를 얻어옴.
         */
        val flagsImageView: ImageView = findViewById(R.id.fragsImageView)
        //해당 ImageView에 kr_flags.png를 붙임
        flagsImageView.setImageResource(R.drawable.kr_flags)

        //Bitmap Image 객체 획득
        val krFlagsImage = resMgObj.getDrawable(R.drawable.kr_flags, null) as BitmapDrawable
        //현재 이미지의 정보(넓이,높이)
        var width = krFlagsImage.intrinsicWidth
        var heigth = krFlagsImage.intrinsicHeight
        Log.d(DEBUG_TAG, "kr_flags.png 이미지의 높이 : $heigth , 너비 : $width")

        //Nine-Patch Image 획득
        val ninePatchImage = resMgObj.getDrawable(R.drawable.ninepatch_image, null) as NinePatchDrawable
        width = ninePatchImage.intrinsicWidth
        heigth = ninePatchImage.intrinsicHeight
        Log.d(DEBUG_TAG, "ninepatch_image.png 높이 : " + heigth +
                " ,  너비 : " + width)
        /**
         * /res/anim/rotate_anim.xml로 선언된 애니메이션 핸들링(Image 객체에 적용) 방법
         */
        val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)

        //flagsImageView 이미지객체(태극기)에 애니메이션 효과를 줌
        flagsImageView.startAnimation(rotateAnim)
        /**
         * /res/anim/zoom_in_out.xml로 선언된 애니메이션 핸들링(Shape객체에 적용) 방법
         */
        val shapeOval: ImageView = findViewById(R.id.innerImageView)
        val redOval = AnimationUtils.loadAnimation(this, R.anim.zoom_in_out)
        shapeOval.startAnimation(redOval)
    }

    private fun parsingResXMLData(resMgObj: Resources) {
        val customersXML: XmlResourceParser = resMgObj.getXml(R.xml.customers)
        try {
            cusotmersXMLParse(customersXML)
        } catch (xppe: XmlPullParserException) {
            Log.e(DEBUG_TAG, " CustomerXML Parsing Error : ", xppe)
        } catch (ioe: IOException) {
            Log.e(DEBUG_TAG, " CustomerXML IO Error : ", ioe)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun cusotmersXMLParse(parser: XmlResourceParser) {
        var xmlDetailType = -1
        while (xmlDetailType != XmlResourceParser.END_DOCUMENT) {
            if (xmlDetailType == XmlResourceParser.START_DOCUMENT) {
                Log.d(DEBUG_TAG, "Customers Document Start!")
            } else if (xmlDetailType == XmlResourceParser.START_TAG) {
                val childTag = parser.name
                if (childTag == "customer") {
                    Log.d(DEBUG_TAG, "Found A Customer!")
                    Log.d(DEBUG_TAG, "   Name :    " + parser.getAttributeValue(null, "name"))
                    Log.d(DEBUG_TAG, "   Age   :    " + parser.getAttributeValue(null, "age"))
                    Log.d(DEBUG_TAG, "   Address : " +
                            parser.getAttributeValue(null, "address"))
                }
            }
            xmlDetailType = parser.next()
        }
        Log.d(DEBUG_TAG, "Customers Document End!")
    }
}
