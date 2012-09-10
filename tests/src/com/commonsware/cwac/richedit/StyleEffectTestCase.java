/***
  Copyright (c) 2012 CommonsWare, LLC
  
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

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.StyleSpan;

public class StyleEffectTestCase extends EffectTestCaseBase {
  StyleEffect effect;

  public void setUp() {
    effect=new StyleEffect(Typeface.BOLD);
  }

  StyleSpan[] getAllSpans(Spannable str) {
    return(str.getSpans(0, str.length() - 1, StyleSpan.class));
  }

  void updateSpannable(Spannable str, int start, int end, boolean add) {
    effect.applyToSpannable(str, new Selection(start, end), add);
  }
}
