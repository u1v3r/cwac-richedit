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

package com.commonsware.cwac.richedit.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.commonsware.cwac.richedit.Effect;
import com.commonsware.cwac.richedit.RichEditText;

public class RichTextEditorDemoActivity extends Activity implements
    OnCheckedChangeListener, RichEditText.OnSelectionChangedListener {
  RichEditText editor=null;
  HashMap<CompoundButton, Effect<Boolean>> baseEffects=
      new HashMap<CompoundButton, Effect<Boolean>>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.main);

//    editor=(RichEditText)findViewById(R.id.editor);
//    editor.setOnSelectionChangedListener(this);
//
//    baseEffects.put((CompoundButton)findViewById(R.id.bold),
//                    RichEditText.BOLD);
//    baseEffects.put((CompoundButton)findViewById(R.id.italic),
//                    RichEditText.ITALIC);
//    baseEffects.put((CompoundButton)findViewById(R.id.underline),
//                    RichEditText.UNDERLINE);
//    baseEffects.put((CompoundButton)findViewById(R.id.strikethrough),
//                    RichEditText.STRIKETHROUGH);
//    baseEffects.put((CompoundButton)findViewById(R.id.superscript),
//                    RichEditText.SUPERSCRIPT);
//    baseEffects.put((CompoundButton)findViewById(R.id.subscript),
//                    RichEditText.SUBSCRIPT);
//
//    for (CompoundButton btn : baseEffects.keySet()) {
//      btn.setOnCheckedChangeListener(this);
//    }
  }

  @Override
  public void onCheckedChanged(CompoundButton v, boolean checked) {
    Effect<Boolean> effect=baseEffects.get(v);
    
    if (effect!=null) {
      editor.applyEffect(effect, new Boolean(checked));
    }
  }

  @Override
  public void onSelectionChanged(int start, int end,
                                 List<Effect<?>> effects) {
    for (Entry<CompoundButton, Effect<Boolean>> entry : baseEffects.entrySet()) {
      if (effects.contains(entry.getValue())!=entry.getKey().isChecked()) {
        entry.getKey().toggle();
      }
    }
  }
}