@file:Suppress("DEPRECATION")

package kr.co.pyo.fileupload

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

const val TARGET_URL = "http://172.16.200.184:5678/androidNetwork/fileUpload.pyo"
const val MY_PERMISSION_REQUEST_STORAGE = 500

private const val PICK_FROM_GALLERY = 100
private const val PICK_FROM_CAMERA = 200

class FileUpLoadActivity : AppCompatActivity() {
    private lateinit var currentSelectedUri: Uri
    private lateinit var myImageDir: File
    private lateinit var senderBtn: Button
    private lateinit var phoneGalleryBtn: Button
    private lateinit var cameraCaptureBtn: Button
    private lateinit var memberName: EditText
    private lateinit var memberImage: ImageView

    private var fileLocation = ""

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memberName = findViewById(R.id.memberName)
        memberImage = findViewById(R.id.memberImage)
        senderBtn = findViewById(R.id.send)
        phoneGalleryBtn = findViewById(R.id.btn_phone_gallery)
        cameraCaptureBtn = findViewById(R.id.camera_capture)

        phoneGalleryBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, PICK_FROM_GALLERY)
        })
        cameraCaptureBtn.setOnClickListener(View.OnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //업로드할 파일의 이름
            if (fileLocation.isNotEmpty()) {
                fileLocation = ""
            }
            val cameraImageName = "upload_" + System.currentTimeMillis() / 1000 + ".jpg"
            val currentFile = File(myImageDir, cameraImageName)
            fileLocation = currentFile.absolutePath
            currentSelectedUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    this@FileUpLoadActivity,
                    "kr.co.pyo.fileupload.provider",
                    currentFile
                )
            } else {
                //7.0 이전에 파일 디렉토리 사용하는 방법
                Uri.fromFile(currentFile)
            }
            cameraIntent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                currentSelectedUri
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
            startActivityForResult(cameraIntent, PICK_FROM_CAMERA)
        })
        senderBtn.setOnClickListener {
            val queryString = memberName.text.toString().trim()
            if (queryString.isNotEmpty()) {
                fileUploadAsync(queryString)
            }
        }
    }

    private fun fileUploadAsync(queryString: String) {
        Thread {
            val uploadFile = File(fileLocation)
            var response: Response? = null
            try {
                //업로드는 타임 및 리드타임을 넉넉히 준다.
                val toServer: OkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .build()

                val fileUploadBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM) //파일 업로드시 반드시 설정
                    .addFormDataPart("queryName1", queryString) //기본 쿼리
                    .addFormDataPart(
                        "uploadfile", uploadFile.name,
                        RequestBody.create("image/*".toMediaTypeOrNull(), uploadFile)
                    )
                    .build()
                //요청 세팅
                val request: Request = Request.Builder()
                    .url(TARGET_URL)
                    .post(fileUploadBody) //반드시 post로
                    .build()
                //동기 방식
                response = toServer.newCall(request).execute()
                if (response.isSuccessful) {
                    Log.e("TAG", "OK")
                }
            } catch (e: Exception) {
                Log.e("UploadError", e.toString())
            } finally {
                response?.close()
            }
        }.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (fileLocation.isNotEmpty()) {
            outState.putString("fileLocation", fileLocation)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (!savedInstanceState.isEmpty) {
            fileLocation = savedInstanceState.getString("fileLocation").toString()
        }
    }

    public override fun onResume() {
        super.onResume()

        val currentAppPackage = packageName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            myImageDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                File(Environment.getExternalStorageDirectory().absolutePath, currentAppPackage)
            } else {
                File(Environment.getExternalStorageDirectory().absolutePath, "uploadImage")
            }
            checkSDCardPermission()
        } else {
            myImageDir = File(
                Environment.getExternalStorageDirectory().absolutePath, "uploadImage"
            )
        }
        if (myImageDir.mkdirs()) {
            Toast.makeText(application, " 저장할 디렉토리가 생성 됨", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkSDCardPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                }
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    MY_PERMISSION_REQUEST_STORAGE
                )
            } else {
                //사용자가 허락함
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var returedImgURI: Uri?
        if (resultCode != RESULT_OK || resultCode == RESULT_CANCELED) {
            return
        }
        when (requestCode) {
            PICK_FROM_GALLERY -> {
                returedImgURI = data?.data
                if (returedImgURI != null) {
                    Log.e("PICK_FROM_GALLERY", returedImgURI.toString())
                    memberImage.setImageURI(returedImgURI)
                    //업로드 할 수 있도록 절대 주소를 알아낸다.
                    if (findImageFileNameFromUri(returedImgURI)) {
                        Log.e("PICK_FROM_GALLERY", " 갤러리에서 절대주소 Pick 성공")
                    } else {
                        Log.e("PICK_FROM_GALLERY", " 갤러리에서 절대주소 Pick 실패")
                    }
                } else {
                    val extras = data?.extras
                    val returedBitmap = extras!!["data"] as Bitmap?
                    if (tempSavedBitmapFile(returedBitmap)) {
                        Log.e("TAG", "갤러리에서 Uri값이 없어 실제 파일로 저장 성공")
                    } else {
                        Log.e("TAG", "갤러리에서 Uri값이 없어 실제 파일로 저장 실패")
                    }
                }
            }
            PICK_FROM_CAMERA -> {
                var returedUri: Uri? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    returedUri = getUriForFile(this, "kr.co.pyo.fileupload.provider", File(fileLocation))
                    memberImage.setImageURI(returedUri)
                } else {
                    val fileSize = File(fileLocation)
                    Log.e("dataSize", fileSize.length().toString())
                    returedUri = Uri.fromFile(fileSize)
                    memberImage.setImageURI(returedUri)
                }
            }
            else -> {
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSION_REQUEST_STORAGE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                //사용자가 퍼미션을 OK 했을 경우
            } else {
                //사용자가 퍼미션을 거절했을 경우
            }
        }
    }

    private fun tempSavedBitmapFile(tempBitmap: Bitmap?): Boolean {
        val flag = false
        try {
            val tempName = "upload_" + System.currentTimeMillis() / 1000
            val fileSuffix = ".jpg"
            //임시파일을 실행한다.(현재앱이 종료되면 파일자동삭제)
            val tempFile = File.createTempFile(
                tempName,  // prefix
                fileSuffix,  // suffix
                myImageDir // directory
            )
            val bitmapStream = FileOutputStream(tempFile)
            tempBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, bitmapStream)
            bitmapStream.close()
            fileLocation = tempFile.absolutePath
        } catch (i: IOException) {
            Log.e("저장중 문제발생", i.toString(), i)
        }
        return flag
    }

    private fun findImageFileNameFromUri(tempUri: Uri): Boolean {
        var flag = false
        //실제 Image Uri의 절대이름(절대디렉토리)
        val IMAGE_DB_COLUMN = arrayOf(MediaStore.Images.ImageColumns.DATA)
        var cursor: Cursor? = null
        try {
            //Primary Key값을 추출
            val imagePK = ContentUris.parseId(tempUri).toString()
            //Image DB에 쿼리를 날린다.
            cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_DB_COLUMN,
                MediaStore.Images.Media._ID + "=?", arrayOf(imagePK), null, null
            )
            if (cursor!!.count > 0) {
                cursor.moveToFirst()
                fileLocation = cursor.getString(
                    cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                )
                flag = true
            }
        } catch (sqle: SQLiteException) {
            Log.e("findImage....", sqle.toString(), sqle)
        } finally {
            cursor?.close()
        }
        return flag
    }
}