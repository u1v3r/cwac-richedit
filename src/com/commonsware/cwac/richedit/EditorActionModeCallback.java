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

import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

abstract public class EditorActionModeCallback implements
    ActionMode.Callback {
  int menuResource=0;
  EditText editor=null;
  Selection selection=null;
  Activity host=null;

  public EditorActionModeCallback(Activity host, int menuResource,
                                  RichEditText editor) {
    this.host=host;
    this.menuResource=menuResource;
    this.editor=editor;
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    MenuInflater inflater=mode.getMenuInflater();

    inflater.inflate(menuResource, menu);

    return(true);
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    if (selection != null) {
      selection.apply(editor);
    }

    return(false);
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {
  }

  void setSelection(Selection selection) {
    this.selection=selection;
  }

  protected void chainToNextMode(EditorActionModeCallback next) {
    next.setSelection(new Selection(editor));
    host.startActionMode(next);
  }
}
