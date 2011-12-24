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

import android.text.Spannable;
import android.util.Log;

public class SimpleBooleanEffect<T> extends Effect<Boolean> {
  private Class<T> clazz;

  SimpleBooleanEffect(Class<T> clazz) {
    this.clazz=clazz;
  }

  @Override
  boolean existsInSelection(RichEditText editor) {
    Selection selection=new Selection(editor);
    Spannable str=editor.getText();
    boolean result=false;

    if (selection.start != selection.end) {
      T[] spans=str.getSpans(selection.start, selection.end, clazz);

      result=(spans.length > 0);
    }
    else {
      T[] spansBefore=
          str.getSpans(selection.start - 1, selection.end, clazz);
      T[] spansAfter=
          str.getSpans(selection.start, selection.end + 1, clazz);

      result=(spansBefore.length > 0 && spansAfter.length > 0);
    }
    
    return(result);
  }

  @Override
  Boolean valueInSelection(RichEditText editor) {
    return(existsInSelection(editor));
  }

  @Override
  void applyToSelection(RichEditText editor, Boolean add) {
    Selection selection=new Selection(editor);
    Spannable str=editor.getText();
    T[] spans=str.getSpans(selection.start, selection.end, clazz);

    for (T span : spans) {
      str.removeSpan(span);
    }

    if (add) {
      try {
        str.setSpan(clazz.newInstance(), selection.start,
                    selection.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      catch (IllegalAccessException e) {
        Log.e("RichEditText",
              "Exception instantiating " + clazz.toString(), e);
      }
      catch (InstantiationException e) {
        Log.e("RichEditText",
              "Exception instantiating " + clazz.toString(), e);
      }
    }
  }
}
