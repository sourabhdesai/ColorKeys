ColorKeys
=========

Keyword coloring for Android EditTexts made easy!  

This is a demo app of a couple classes I created to make Keyword coloring in Android EditTexts fast, functional, and easy.  
What does it do?
-----------------------------------------------------------------------------------------------------------------------
It allows you to dynamically change the color of specific keywords in an EditTexts to one that is specified.  

What use is it?
-----------------------------------------------------------------------------------------------------------------------
As with anything, the only limit to its use is your creativity. But here are some ideas:  
  -Android IDE's  
  -Word Based Games  
  -Educational Spelling Apps
  
How do I use it?
-----------------------------------------------------------------------------------------------------------------------
Its made for programmers, by a programmer! You can check out the main activity in the source files but heres all you need to know:  
  **1.** Create an EditTextHighlighter object using one of these constructors:  
    -`highlighter = new EditTextHighlighter(rDefault,gDefault,bDefault);`  
    -`highlighter = new EditTextHighlighter(int defaultcolor);`  
  **2.** Add words to the object along with the colors you want the to be colored using the following instance methods:  
      -`highlighter.addKeyWord(String keyword,int r, int g, int b)`  
      -`highlighter.addKeyWord(String keyword,int color)`  
      -`highlighter.addKeysWithSingleValue(String[] keys, int r, int g, int b)`  
      -`highlighter.addKeysWithSingleValue(String[] keys, int color)`  
  **3.** Specify the EditText(s) that you want to add the keyword highlighting settings with one easy step:  
      -`addSettingsToEditText(EditText editText1)`
