// Generated by view binder compiler. Do not edit!
package kr.co.softcampus.seekbar.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kr.co.softcampus.seekbar.R;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final Button button2;

  @NonNull
  public final Button button3;

  @NonNull
  public final Button button4;

  @NonNull
  public final SeekBar seekBar;

  @NonNull
  public final SeekBar seekBar2;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull Button button,
      @NonNull Button button2, @NonNull Button button3, @NonNull Button button4,
      @NonNull SeekBar seekBar, @NonNull SeekBar seekBar2, @NonNull TextView textView,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.button = button;
    this.button2 = button2;
    this.button3 = button3;
    this.button4 = button4;
    this.seekBar = seekBar;
    this.seekBar2 = seekBar2;
    this.textView = textView;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = rootView.findViewById(id);
      if (button == null) {
        break missingId;
      }

      id = R.id.button2;
      Button button2 = rootView.findViewById(id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button3;
      Button button3 = rootView.findViewById(id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.button4;
      Button button4 = rootView.findViewById(id);
      if (button4 == null) {
        break missingId;
      }

      id = R.id.seekBar;
      SeekBar seekBar = rootView.findViewById(id);
      if (seekBar == null) {
        break missingId;
      }

      id = R.id.seekBar2;
      SeekBar seekBar2 = rootView.findViewById(id);
      if (seekBar2 == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, button, button2, button3, button4,
          seekBar, seekBar2, textView, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
