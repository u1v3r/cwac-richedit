/***
  Copyright (c) 2008-2011 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.commonsware.cwac.richedit;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.commonsware.cwac.richedit.RichEditText.OnSelectionChangedListener;

public class RichEditToolbar extends LinearLayout implements
    OnClickListener, OnSelectionChangedListener,
    /* Animation.AnimationListener, */TextWatcher {
  private HashMap<View, SimpleEffectToggle> toggles=
      new HashMap<View, SimpleEffectToggle>();
  private RichEditText editor;

  /*
   * Standard one-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditToolbar(Context context) {
    super(context);
    initToolbar();
  }

  /*
   * Standard two-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditToolbar(Context context, AttributeSet attrs) {
    super(context, attrs);
    initToolbar();
  }

  /*
   * Standard three-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditToolbar(Context context, AttributeSet attrs,
                         int defStyle) {
    super(context, attrs, defStyle);
    initToolbar();
  }

  public void setEditor(RichEditText editor) {
    this.editor=editor;
    editor.setOnSelectionChangedListener(this);
    editor.addTextChangedListener(this);
  }

  @Override
  public void onClick(View v) {
    SimpleEffectToggle toggle=toggles.get(v);

    if (toggle != null) {
      toggle.applyAndUpdate(editor);
    }
  }

  @Override
  public void onSelectionChanged(int start, int end,
                                 List<Effect<?>> effects) {
    updateButtonStates();
  }

  @Override
  public void afterTextChanged(Editable s) {
    updateButtonStates();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count,
                                int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before,
                            int count) {
  }

  // public void onAnimationEnd(Animation animation) {
  // setVisibility(View.GONE);
  // }
  //
  // public void onAnimationRepeat(Animation animation) {
  // // not needed
  // }
  //
  // public void onAnimationStart(Animation animation) {
  // // not needed
  // }

  private void updateButtonStates() {
    for (SimpleEffectToggle toggle : toggles.values()) {
      toggle.update(editor);
    }
  }

  private void initToolbar() {
    setOrientation(VERTICAL);

    LayoutInflater inflater=
        (LayoutInflater)(getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));

    inflater.inflate(R.layout.toolbar, this);

    SimpleEffectToggle toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_bold),
                               RichEditText.BOLD, R.drawable.bold,
                               R.drawable.bold_off);

    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);

    toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_italic),
                               RichEditText.ITALIC, R.drawable.italic,
                               R.drawable.italic_off);
    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);

    toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_underline),
                               RichEditText.UNDERLINE,
                               R.drawable.underline,
                               R.drawable.underline_off);
    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);

    toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_strikethrough),
                               RichEditText.STRIKETHROUGH,
                               R.drawable.strikethrough,
                               R.drawable.strikethrough_off);
    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);

    toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_superscript),
                               RichEditText.SUPERSCRIPT,
                               R.drawable.superscript,
                               R.drawable.superscript_off);
    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);

    toggle=
        new SimpleEffectToggle(
                               (ImageButton)findViewById(R.id.cwac_richedittext_subscript),
                               RichEditText.SUBSCRIPT,
                               R.drawable.subscript,
                               R.drawable.subscript_off);
    toggles.put(toggle.btn, toggle);
    toggle.btn.setOnClickListener(this);
  }

  // private void show() {
  // TranslateAnimation anim=null;
  //
  // setVisibility(View.VISIBLE);
  // anim=new TranslateAnimation(getWidth(), 0.0f, 0.0f,
  // 0.0f);
  // anim.setDuration(3000);
  // anim.setInterpolator(new AccelerateInterpolator(1.0f));
  // startAnimation(anim);
  // }
  //
  // private void hide() {
  // TranslateAnimation anim=null;
  //
  // anim=new TranslateAnimation(0.0f, getWidth(), 0.0f,
  // 0.0f);
  // anim.setAnimationListener(this);
  // anim.setDuration(1);
  // startAnimation(anim);
  // }

  private static class SimpleEffectToggle {
    ImageButton btn;
    Effect<Boolean> effect;
    int onDrawable;
    int offDrawable;

    SimpleEffectToggle(ImageButton btn, Effect<Boolean> effect,
                       int onDrawable, int offDrawable) {
      this.btn=btn;
      this.effect=effect;
      this.onDrawable=onDrawable;
      this.offDrawable=offDrawable;
    }

    void applyAndUpdate(RichEditText editor) {
      if (editor.hasEffect(effect)) {
        editor.applyEffect(effect, false);
        btn.setImageResource(offDrawable);
      }
      else {
        editor.applyEffect(effect, true);
        btn.setImageResource(onDrawable);
      }
    }

    void update(RichEditText editor) {
      if (editor.hasEffect(effect)) {
        btn.setImageResource(onDrawable);
      }
      else {
        btn.setImageResource(offDrawable);
      }
    }
  }
}
