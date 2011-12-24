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

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class RichEditor extends RelativeLayout {
  private RichEditText editor;
  
  /*
   * Standard one-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditor(Context context) {
    super(context);
    initEditor();
  }

  /*
   * Standard two-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditor(Context context, AttributeSet attrs) {
    super(context, attrs);
    initEditor();
  }

  /*
   * Standard three-parameter widget constructor, simply
   * chaining to superclass.
   */
  public RichEditor(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initEditor();
  }
  
  public RichEditText getEditText() {
    return(editor);
  }
  
  private void initEditor() {
    LayoutInflater inflater=
        (LayoutInflater)(getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));

    inflater.inflate(R.layout.editor, this);
    
    RichEditToolbar toolbar=(RichEditToolbar)findViewById(R.id.cwac_richedittext_toolbar);
    
    editor=(RichEditText)findViewById(R.id.cwac_richedittext_editor);
    toolbar.setEditor(editor);
  }
}
