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

import android.text.Spannable;
import android.text.style.TypefaceSpan;

public class TypefaceEffectTestCase extends EffectTestCaseBase {
  TypefaceEffect effect;

  public void setUp() {
    effect=new TypefaceEffect();
  }

  TypefaceSpan[] getAllSpans(Spannable str) {
    return(str.getSpans(0, str.length() - 1, TypefaceSpan.class));
  }

  void updateSpannable(Spannable str, int start, int end, boolean add) {
    String family=(add ? "monospace" : null);
    
    effect.applyToSpannable(str, new Selection(start, end), family);
  }
}
