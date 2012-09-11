CWAC RichEditText: Letting Users Make Text Pretty
=================================================

Android's `EditText` widget supports formatted (a.k.a.,
"rich text") editing. It just lacks any way for the user
to supply formatting, and it does not provide much in the
way of convenience methods for a developer to, say, tie
in some sort of toolbar to allow users to format selections.

That's where `RichEditText` comes in.

`RichEditText` is a drop-in replacement for `EditText` that:

- Provides an action mode on Android 2.1+ that allows
users to format selected pieces of text
- Provides convenience methods to allow developers to 
trigger formatting for selected text via other means

This widget is packaged as an Android library project, with
a `demo/` subdirectory containing a regular Android project
with a couple of activities demonstrating the use of
`RichEditText`.

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

On its own, by default, `RichEditText` provides one means of users
applying formatting: the standard `<Ctrl>-<B>` for bold,
`<Ctrl>-<I>` for italics, and `<Ctrl>-<U>` for underline work if there
is a selection. You can disable this by calling
`setKeyboardShortcutsEnabled(false)`.

If you want an on-screen UI for formatting, you have two choices.

First, you can call
`enableActionModes()` on the `RichEditText`. This will add a "FORMAT"
entry on the action mode that comes up when the user highlights some
prose in the editor. Tapping that will allow the user to toggle various
effects. This is easy to set up, but there are two significant limitations:

1. If you intend to run on devices prior to API Level 11, you need to be
using ActionBarSherlock, and your activity hosting the `RichEditText` needs
to inherit from one of the Sherlock-flavored activiy classes.

2. The action modes work so-so on phones at this time &mdash;
tablets work better. To get it to work on phones at all, you will need
to include `android:imeOptions="flagNoExtractUi"` as an attribute on the
`RichEditText`.

Alternatively, you can have
your own toolbar or gesture interface or
whatever to allow users to format text. In that case, here are the two key
methods to call on `RichEditText`:

- `applyEffect()` changes the current selection, applying
or removing an effect (e.g., making the selection bold). The
first parameter is the effect to apply (e.g., `RichEditText.BOLD`).
The second parameter is the new value for the effect. Many
effects take boolean values, so `applyEffect(RichEditText.BOLD, true)`
would format the current selection as bold.

- `setOnSelectionChangedListener()` is where you register a
`RichEditText.OnSelectionChangedListener` object, which will
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
- `SUPERSCRIPT`
- `SUBSCRIPT`
- `TYPEFACE`

There are other effects presently implemented, but they
will be revised shortly, including name and data type
changes, so don't mess with them yet.

Dependencies
------------
This project depends upon [ActionBarSherlock](http://actionbarsherlock.com).
If you check out this project, you will need to update your local configuration
to point to your own copy of ActionBarSherlock.

(as the world patiently awaits Android library projects being able to be
packaged as JARs...)

Version
-------
This is version v0.2.0 of this module, meaning it is out of its months-long
slumber and is proceeding apace.

Demo
----
In the `demo/` sub-project you will find
a sample activity that demonstrates the use of `RichEditor`.

In the `tests/` sub-project you will find a small unit test suite for
exercising some of the effects.

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

If you have encountered what is clearly a bug, or if you have a feature request,
please post an [issue](https://github.com/commonsguy/cwac-richedit/issues).
Be certain to include complete steps for reproducing the issue.

Do not ask for help via Twitter.

Also, if you plan on hacking
on the code with an eye for contributing something back,
please open an issue that we can use for discussing
implementation details. Just lobbing a pull request over
the fence may work, but it may not.

Release Notes
-------------
- v0.2.0: added keyboard shortcuts for bold/italic/underline and test suite, bug fixes
- v0.1.1: added `disableActionModes()` and fixed bug related to conditional action mode usage
- v0.1: added action mode support using ActionBarSherlock for pre-Honeycomb devices
- v0.0.3: removed `RichEditor`, replaced it with custom action modes
- v0.0.2: added `RichEditor` and made various fixes
- v0.0.1: initial release
