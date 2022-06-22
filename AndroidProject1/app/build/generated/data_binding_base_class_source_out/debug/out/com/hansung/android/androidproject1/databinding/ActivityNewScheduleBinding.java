// Generated by data binding compiler. Do not edit!
package com.hansung.android.androidproject1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.hansung.android.androidproject1.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityNewScheduleBinding extends ViewDataBinding {
  @NonNull
  public final Button Can;

  @NonNull
  public final TextView End;

  @NonNull
  public final Button Findbtn;

  @NonNull
  public final EditText Location;

  @NonNull
  public final TextView Start;

  @NonNull
  public final TextView Title;

  @NonNull
  public final EditText TitleText;

  @NonNull
  public final Button Up;

  @NonNull
  public final EditText UserText;

  @NonNull
  public final Button del;

  @NonNull
  public final NumberPicker enum1;

  @NonNull
  public final NumberPicker enum2;

  @NonNull
  public final NumberPicker enum3;

  @NonNull
  public final ListView listview;

  @NonNull
  public final TextView result;

  @NonNull
  public final NumberPicker snum1;

  @NonNull
  public final NumberPicker snum2;

  @NonNull
  public final NumberPicker snum3;

  @NonNull
  public final EditText sqlId;

  protected ActivityNewScheduleBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button Can, TextView End, Button Findbtn, EditText Location, TextView Start, TextView Title,
      EditText TitleText, Button Up, EditText UserText, Button del, NumberPicker enum1,
      NumberPicker enum2, NumberPicker enum3, ListView listview, TextView result,
      NumberPicker snum1, NumberPicker snum2, NumberPicker snum3, EditText sqlId) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Can = Can;
    this.End = End;
    this.Findbtn = Findbtn;
    this.Location = Location;
    this.Start = Start;
    this.Title = Title;
    this.TitleText = TitleText;
    this.Up = Up;
    this.UserText = UserText;
    this.del = del;
    this.enum1 = enum1;
    this.enum2 = enum2;
    this.enum3 = enum3;
    this.listview = listview;
    this.result = result;
    this.snum1 = snum1;
    this.snum2 = snum2;
    this.snum3 = snum3;
    this.sqlId = sqlId;
  }

  @NonNull
  public static ActivityNewScheduleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_new_schedule, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityNewScheduleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityNewScheduleBinding>inflateInternal(inflater, R.layout.activity_new_schedule, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityNewScheduleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_new_schedule, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityNewScheduleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityNewScheduleBinding>inflateInternal(inflater, R.layout.activity_new_schedule, null, false, component);
  }

  public static ActivityNewScheduleBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityNewScheduleBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityNewScheduleBinding)bind(component, view, R.layout.activity_new_schedule);
  }
}