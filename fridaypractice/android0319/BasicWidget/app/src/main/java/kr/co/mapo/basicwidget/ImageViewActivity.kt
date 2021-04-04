package kr.co.mapo.basicwidget
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kr.co.mapo.basicwidget.databinding.ImageViewLayoutBinding

class ImageViewActivity : Activity() {
    private val binding by lazy { ImageViewLayoutBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var jiYeonImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.tara_ji_yeon)
        binding.scaleTypeCenter.scaleType(ImageView.ScaleType.CENTER)
        binding.scaleTypeCenter.setImageBitmap(jiYeonImage)


        //이미지를 축소(1/4)하여 코드로 가져 옴
        val imageOptions   = BitmapFactory.Options()
        imageOptions.inSampleSize  = 4

        val originalImage = BitmapFactory.decodeResource(resources, R.drawable.tara_ji_yeon, imageOptions)
        jiYeonImage = Bitmap.createScaledBitmap(originalImage, 300, 300, true)
        binding.scaleTypeFitXY.scaleType(ImageView.ScaleType.FIT_XY)

        //ScaleType.CENTER_CROP 적용3

        jiYeonImage = Bitmap.createScaledBitmap(jiYeonImage, 50, 50, true)
        binding.scaleTypeCenterCrop.scaleType(ImageView.ScaleType.CENTER_CROP)
        binding.scaleTypeCenterCrop.setImageBitmap(jiYeonImage)

        //ScaleType.CENTER_CROP 적용4
        jiYeonImage = Bitmap.createScaledBitmap(jiYeonImage, 300, 300, true)
        binding.scaleTypeCenterInside.scaleType(ImageView.ScaleType.CENTER_INSIDE)
        binding.scaleTypeCenterInside.setImageBitmap(jiYeonImage)

        //ScaleType.FiT_CENTER 적용 5
        jiYeonImage = Bitmap.createScaledBitmap(jiYeonImage, 50, 50, true)
        binding.scaleTypeFitCenter.scaleType(ImageView.ScaleType.FIT_CENTER)
        binding.scaleTypeFitCenter.setImageBitmap(jiYeonImage)

        //ScaleType.FIT_END 적용 6
        binding.scaleTypeFitEnd.scaleType(ImageView.ScaleType.FIT_END)
        binding.scaleTypeFitEnd.setImageBitmap(jiYeonImage)
        //ScaleType.FIT_START 적용7

        //ScaleType.FIT_START 적용7
        jiYeonImage = Bitmap.createScaledBitmap(jiYeonImage, 300, 300, true)
        binding.scaleTypeFitStart.scaleType = ImageView.ScaleType.FIT_START
        binding.scaleTypeFitStart.setImageBitmap(jiYeonImage)

        //ScaleTypeMATRIX 적용8
        jiYeonImage = Bitmap.createScaledBitmap(jiYeonImage, 50, 50, true)
        binding.scaleTypeMatrix.scaleType = ImageView.ScaleType.MATRIX
        binding.scaleTypeMatrix.setImageBitmap(jiYeonImage)
    }
}

private fun ImageView.scaleType(center: ImageView.ScaleType) {
}

