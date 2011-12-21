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
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RichTextActionMode implements ActionMode.Callback {
  private RichEditText editor;
  private static final HashMap<Integer, Effect<Boolean>> baseEffects=
      new HashMap<Integer, Effect<Boolean>>();

  static {
    baseEffects.put(R.id.bold, RichEditText.BOLD);
    baseEffects.put(R.id.italic, RichEditText.ITALIC);
    baseEffects.put(R.id.underline, RichEditText.UNDERLINE);
  }

  RichTextActionMode(RichEditText editor) {
    this.editor=editor;
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    MenuInflater inflater=new MenuInflater(editor.getContext());

    inflater.inflate(R.menu.actionmode, menu);

    return(true);
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return(false);
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    Effect<Boolean> effect=baseEffects.get(item.getItemId());
    
    if (effect!=null) {
      editor.toggleEffect(effect);
    }

    return(false);
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {
  }
}
