CWAC RichEditText: Letting Users Make Text Pretty
=================================================

Android's `EditText` widget supports formatted (a.k.a.,
"rich text") editing. It just lacks any way for the user
to supply formatting, and it does not provide much in the
way of convenience methods for a developer to, say, tie
in some sort of toolbar to allow users to format selections.

That's where `RichEditText` comes in.

`RichEditText` is a drop-in replacement for `EditText` that:

- Provides an action mode on Android 3.0+ that allows
users to format selected pieces of text
- Provides convenience methods to allow developers to 
trigger formatting for selected text via other means

This widget is packaged as an Android library project, with
a `demo/` subdirectory containing a regular Android project
with a couple of activities demonstrating the use of
`RichEditText`.

**NOTE: THIS IS A HIGHLY EXPERIMENTAL RELEASE.** While some
rudimentary stuff works, there are many limitations and
probably more than its fair share of bugs. Mostly, this was
released to serve as a reference point for some bugs to be
filed against Android itself (related to action modes).
Expect significant revisions of this component, including
API changes, in the coming weeks and months. That being said,
you are welcome to try it out and supply feedback.

Usage
-----
Add the project as a library project to your main project.
Then, simply add `com.commonsware.cwac.richedit.RichEditText`
widgets to your layout as needed:

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.commonsware.cwac.richedit.RichEditText xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@+id/editor"
  android:layout_width="fill_parent"
  android:layout_height="fill_parent"
  android:gravity="top|left"
  android:inputType="textMultiLine">

  <requestFocus/>

</com.commonsware.cwac.richedit.RichEditText>
```
At this time, there are no custom attributes used by
`RichEditText`.

### Action Mode

If you want to enable the extended action mode, where users
can format selected alongside their existing cut/copy/paste
options, call `enableActionMode()`. By default this is disabled,
and it will be up to your activity or fragment to arrange
to allow the user to format selected text.

Note that it is safe to call `enableActionMode()` on all
versions of Android &mdash; it is this component's responsibility
to do the right thing.

The action mode support, at present, is limited to bold
and italic. There is an underline option, but it will not
work, due to a bug/design limitation in Android. The fervent
hope is that the action mode will support all formatting options
in the coming weeks and months.

Also note that action modes do not appear to work on Android 4.0
in landscape, at least on phones. They do work on Android 3.x
tablets and on Android 4.0 phones in portrait mode, but the
action mode does not even show up on Android 4.0 in landscape.
The hope is that this is a bug or misunderstanding in the
use of action modes, as having action modes not be available
in **THE MOST COMMON EXTENDED TYPING POSITION ON PHONES** would just
be scary.

### Rolling Your Own Controls

If you want to have your own toolbar or gesture interface or
whatever to allow users to format text, here are the two key
methods to call on `RichEditText`:

- `applyEffect()` changes the current selection, applying
or removing an effect (e.g., making the selection bold). The
first parameter is the effect to apply (e.g., `RichEditText.BOLD`).
The second parameter is the new value for the effect. Many
effects take boolean values, so `applyEffect(RichEditText.BOLD, true)`
would format the current selection as bold.

- `setOnSelectionChangedListener()` is where you register a
RichEditText.OnSelectionChangedListener object, which will
be called with `onSelectionChanged()` whenever the user changes
the selection in the widget (i.e., highlights text or taps
to un-select the highlight). You are provided the start and
end positions of the selection (as were supplied to `onSelectionChanged()`
to `RichEditText` itself by Android), plus a list of effects
that are active on that selection. This will allow you to 
update your toolbar to indicate what is and is not in use,
and so you know what to do when the user taps on one of
those toolbar buttons again.

### Supported Effects

At the time of this writing, here are the `RichEditText`
static data members for each supported effect:

- `BOLD`
- `ITALIC`
- `UNDERLINE`
- `STRIKETHROUGH`
- `SANS`
- `SUPERSCRIPT`
- `SUBSCRIPT`

There are other effects presently implemented, but they
will be revised shortly, including name and data type
changes, so don't mess with them yet.

Dependencies
------------
This project has no dependencies.

Version
-------
This is version v0.0.1 of this module, meaning it has all
the stability of a sand castle. You have been warned.

Demo
----
In the `demo/` sub-project you will find
a sample activity that demonstrates the use of `RichEditText`.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please post a question
on [StackOverflow](http://stackoverflow.com/questions/ask) tagged with `commonsware` and `android`. Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

If you have encountered what is clearly a bug, or a feature request,
please post an [issue](https://github.com/commonsguy/cwac-endless/issues).
Be certain to include complete steps for reproducing the issue.

Do not ask for help via Twitter.

Also, if you plan on hacking
on the code with an eye for contributing something back,
please open an issue that we can use for discussing
implementation details. Just lobbing a pull request over
the fence may work, but it may not.

Release Notes
-------------
* v0.0.1: initial release
