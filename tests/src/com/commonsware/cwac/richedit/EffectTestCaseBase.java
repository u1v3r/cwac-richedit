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

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import junit.framework.TestCase;

public abstract class EffectTestCaseBase extends TestCase {
  abstract Object[] getAllSpans(Spannable str);
  abstract void updateSpannable(Spannable str, int start, int end, boolean add);
  
  public void assertSpans(Spannable str, Object[] spans, int expectedLength, int... ranges) {
    assertEquals(expectedLength, spans.length);
    
    for (int i=0;i<ranges.length/2;i++) {
      assertEquals(ranges[i*2], str.getSpanStart(spans[i]));
      assertEquals(ranges[(i*2)+1], str.getSpanEnd(spans[i]));
    }
  }

  public void testAddAndRemove() {
    Spannable str=
        new SpannableString(Html.fromHtml("0123 56 89012345"));

    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 0, 4, true);
    assertSpans(str, getAllSpans(str), 1, 0, 4);

    updateSpannable(str, 0, 4, false);
    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 4, 10, true);
    assertSpans(str, getAllSpans(str), 1, 4, 10);

    updateSpannable(str, 4, 10, false);
    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 8, 15, true);
    assertSpans(str, getAllSpans(str), 1, 8, 15);

    updateSpannable(str, 8, 15, false);
    assertSpans(str, getAllSpans(str), 0);
  }

  public void testPartialSpanMiddle() {
    Spannable str=
        new SpannableString(Html.fromHtml("0123 56 89012345"));

    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 2, 9, true);
    assertSpans(str, getAllSpans(str), 1, 2, 9);

    updateSpannable(str, 4, 7, false);
    assertSpans(str, getAllSpans(str), 2, 2, 4, 7, 9);
  }

  public void testPartialSpanStart() {
    Spannable str=
        new SpannableString(Html.fromHtml("0123 56 89012345"));

    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 2, 9, true);
    assertSpans(str, getAllSpans(str), 1, 2, 9);

    updateSpannable(str, 2, 7, false);
    assertSpans(str, getAllSpans(str), 1, 7, 9);
  }

  public void testPartialSpanEnd() {
    Spannable str=
        new SpannableString(Html.fromHtml("0123 56 89012345"));

    assertSpans(str, getAllSpans(str), 0);

    updateSpannable(str, 2, 9, true);
    assertSpans(str, getAllSpans(str), 1, 2, 9);

    updateSpannable(str, 4, 9, false);
    assertSpans(str, getAllSpans(str), 1, 2, 4);
  }
}