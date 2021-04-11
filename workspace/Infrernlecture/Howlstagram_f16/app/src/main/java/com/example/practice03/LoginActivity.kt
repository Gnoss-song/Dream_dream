package com.example.practice03

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice03.databinding.ActivityLoginBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


//구글 로그인 요청에 대한 request 코드
const val GOOGLE_LOGIN_CODE = 9001

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    //회원 가입을 위한 Authenticate 라이브러리 객체
    private var auth : FirebaseAuth? = null
    //구글 로그인 요청을 위한 gooleSignInClient 객체
    private var googleSignInClient : GoogleSignInClient? = null
    //페이스북 로그인 요청에 대한 콜백 객체
    private var callBackManager : CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.emailLoginButton.setOnClickListener {
            signInAndSignUp()
        }
        //구글 로그인 옵션
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.googleLoginButton.setOnClickListener {
            //구글 로그인 1step
            googleLogin()
        }
        callBackManager = CallbackManager.Factory.create()
        binding.facebookLoginButton.setOnClickListener {
            facebookLogin()
        }

    }

    fun signInAndSignUp() {
        //editText에 입력된 email, password값 가져오기
        auth?.createUserWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            ?.addOnCompleteListener {
                task ->
                if(task.isSuccessful) {
                    //회원가입이 성공했을 때 -> 로그인 시켜야함
                    moveMainPage(task.result!!.user)
                } else if (task.exception?.message.isNullOrEmpty()) {
                    //로그인 에러가 났을 때 에러메시지 출력
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                } else {
                    //회원가입, 에러가 아닐 때 -> 로그인 진행
                    signInEmail()
                }
        }
    }

    //이메일로 로그인
    fun signInEmail() {
        auth?.signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    //로그인 성공 : email, pw가 맞았을 때
                    moveMainPage(task.result!!.user)
                } else {
                    //로그인 실패 : email, pw가 틀림
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    //로그인 성공 후 다음페이지로 이동
    fun moveMainPage(user : FirebaseUser?) {        //파이어베이스 유저 상태를 넘겨주어야 한다.
        if(user != null) {  //user의 상태가 null이 아닐때 -> user가 있을 때 다음 페이지로 이동
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }

    fun facebookLogin() {
        //페이스북에 요청할 권한 설정
        //public_profile : 프로필 사진, email : 이메일
        LoginManager.getInstance()
            .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
        //LoginResult 콜백매니저 등록
        LoginManager.getInstance()
            .registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    //로그인 성공 -> 페이스북 데이터 파이어베이스에 저장
                    handleFacebookAccessToken(result!!.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException?) {
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE) {
            //구글 로그인 결과를 받아오는 인텐트(data)
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(result!!.isSuccess) {    //성공시 로그인된 값을 파이어베이스에 넘겨준다(저장)
                var account = result.signInAccount
                //구글 로그인 2step
                firebaseAuthWithGoogle(account)
            } else {
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
            }
        }
        //onActivityResult의 결과를 callbackManager로 넘겨줌(페이스북 로그인)
        callBackManager?.onActivityResult(requestCode, resultCode, data)
    }

    //파이어베이스에 구글 로그인 정보 전달
    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)   //account 안에 있는 id토큰 가져오기
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    //로그인 성공 : email, pw가 맞았을 때
                    moveMainPage(task.result!!.user)
                } else {
                    //로그인 실패 : email, pw가 틀림
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    //파이어베이스에 페이스북 로그인 정보 전달
    fun handleFacebookAccessToken(token : AccessToken) {
        var credential = FacebookAuthProvider.getCredential(token?.token!!)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    //로그인 성공 : email, pw가 맞았을 때
                    moveMainPage(task.result!!.user)
                } else {
                    //로그인 실패 : email, pw가 틀림
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}