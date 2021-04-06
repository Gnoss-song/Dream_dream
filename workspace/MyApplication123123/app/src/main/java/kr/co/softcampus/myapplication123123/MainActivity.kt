import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import kr.co.softcampus.myapplication123123.R

public class MessageDemo extends Activity implements View.OnClickListener{
    Button alert; //팝업버튼선언

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //팝업버튼 설정
        alert=(Button)findViewById(R.id.alert);//R.id.alert는 팝업버튼 아이디
        alert.setOnClickListener(this);

    }
    public void onClick(View view){
        if(view==alert){ //view가 alert 이면 팝업실행 즉 버튼을 누르면 팝업창이 뜨는 조건
            new AlertDialog.Builder(this)
                .setTitle("히든목록") //팝업창 타이틀바
                .setMessage("FinessShot")  //팝업창 내용
                .setNeutralButton("닫기",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        //닫기 버튼을 누르면 아무것도 안하고 닫기 때문에 그냥 비움

                    }
                })
                .show(); // 팝업창 보여줌
        }

    }