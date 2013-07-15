ColorKeys
=========

Keyword coloring for Android EditTexts made easy!  

This is a demo app of a couple classes I created to make Keyword coloring in Android EditTexts fast, functional, and easy.  
What does it do?
-----------------------------------------------------------------------------------------------------------------------
It allows you to dynamically change the color of specific keywords in an EditTexts to one that is specified.  

<img src="http://i.imgur.com/Zrmz6Yw.png" alt="Screenshot" />

<object width="560" height="315"><param name="movie" value="//www.youtube.com/v/Nil7plWDiXs?version=3&amp;hl=en_US"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="//www.youtube.com/v/Nil7plWDiXs?version=3&amp;hl=en_US" type="application/x-shockwave-flash" width="560" height="315" allowscriptaccess="always" allowfullscreen="true"></embed></object>

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
    -`highlighter = new EditTextHighlighter(int defaultColor);`  
  **2.** Add words to the object along with the colors you want the to be colored using the following instance methods:  
      -`highlighter.addKeyWord(String keyword,int r, int g, int b)`  
      -`highlighter.addKeyWord(String keyword,int color)`  
      -`highlighter.addKeysWithSingleValue(String[] keys, int r, int g, int b)`  
      -`highlighter.addKeysWithSingleValue(String[] keys, int color)`  
  **3.** Specify the EditText(s) that you want to add keyword highlighting to with one easy step:  
      -`highlighter.addSettingsToEditText(EditText editText)`  
  
  And thats it!

Whats the easiest way to use it?
------------------------------------------------------------------------------------------------------------------------
The only files you really need out of here are the following:
  -**EditTextHighlighter.java**
  -**SettingsDatabase.java**
  -**StringPhoneLattice.java**
The easiest thing to do is just copy these three files into your project. After that you should be up and running!  
(Dont forget to change the `package` declarations!)  

Caveats!
------------------------------------------------------------------------------------------------------------------------
-Any keywords added to the `highlighter` EditTextHighlighter object after `highlighter.addSettingsToEditText(EditText editText)` has been called will not be reflected on the `editText` unless `addSettingsToEditText` is called again.  
-The current implementation uses a String Phone Lattice data struture to store the words. This data structure takes more time to add words to its dataset relative to a String array. However it truly shines when querying its dataset to see if a String exists in it. Wanna know more? [Click Here!](https://github.com/sourabhdesai/StringPhoneLattice)  
