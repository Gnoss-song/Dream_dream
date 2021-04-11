// Generated by view binder compiler. Do not edit!
package kr.co.softcampus.matcheap_mypage.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kr.co.softcampus.matcheap_mypage.R;

public final class MyDialogBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView popupMain;

  @NonNull
  public final Button popupOk;

  private MyDialogBinding(@NonNull ScrollView rootView, @NonNull TextView popupMain,
      @NonNull Button popupOk) {
    this.rootView = rootView;
    this.popupMain = popupMain;
    this.popupOk = popupOk;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static MyDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MyDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.my_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MyDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.popup_main;
      TextView popupMain = rootView.findViewById(id);
      if (popupMain == null) {
        break missingId;
      }

      id = R.id.popup_ok;
      Button popupOk = rootView.findViewById(id);
      if (popupOk == null) {
        break missingId;
      }

      return new MyDialogBinding((ScrollView) rootView, popupMain, popupOk);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}