package com.example.Edit_Text_Color_Keys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/14/13
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class main_activity extends Activity {

    EditTextHighlighter textHighlighter;
    int r;
    int g;
    int b;

    @Override
    public void onCreate(Bundle savedInstance)  {
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        EditText mainEditText = (EditText) findViewById(R.id.entertext_editText_main);
        final EditText addWordEditText = (EditText) findViewById(R.id.addword_editText_main);
        final EditText rEditText = (EditText) findViewById(R.id.R_editext_main);
        final EditText gEditText = (EditText) findViewById(R.id.G_editext_main);
        final EditText bEditText = (EditText) findViewById(R.id.B_editext_main);

        final TextView[] textViews = new TextView[] {(TextView)findViewById(R.id.textView),(TextView)findViewById(R.id.textView1),(TextView)findViewById(R.id.textView2),(TextView)findViewById(R.id.textView3)};

        Button addWordButton = (Button) findViewById(R.id.add_button_main);
        Button setColorButton = (Button) findViewById(R.id.setcolor_button_main);

        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background_rellayout_main);

        //The following shows how you use the EditTextHighlighter object

        //You can create a new EditTextHighlighter object using one of two constructors
        //This constructor sets the default text color using the three int parameters which represent the default color's RGB components
        textHighlighter = new EditTextHighlighter(0,0,0); // (0,0,0) corresponds to the color Black
        //Alternatively, if you already have the default color's value in the form of a single integer, you can just use the new EditText( integercolor ) constructor

        //Adding words to highlight is easy, heres how you do one.
        textHighlighter.addKeyWord("Word",255,0,0); //When the user types the word "Word", it will appear in the color red

        //When adding multiple words of the same color, it is fastest to put these words into a String array and use the following method
        String[] words = {"Hello","World!"};
        textHighlighter.addKeysWithSingleValue(words,0,255,0);//The words "Hello" and "World" will appear in the color blue now, wherever they are typed.

        //Now you can add these coloring settings to any edit text object that you like!
        textHighlighter.addSettingsToEditText(mainEditText);
        //And thats all you need to do! To implement this library into your App, the easiest thing to do would be to copy the EditTextHighlighter.java,SettingsDatabase.java,andStringPhoneLattice.java classes into your projects source folder.



        //The rest here is just more of what you just read above.
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = addWordEditText.getText().toString();
                textHighlighter.addKeyWord(keyword,r,g,b);
            }
        });

        setColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = Integer.parseInt(rEditText.getText().toString());
                g = Integer.parseInt(gEditText.getText().toString());
                b = Integer.parseInt(bEditText.getText().toString());
                background.setBackgroundColor(Color.rgb(r,g,b));
                for(TextView tv : textViews)    {
                    tv.setTextColor(Color.rgb(255-r,255-g,255-b));
                }
            }
        });

        Toast.makeText(this,"The words \"Word\", \"Hello\", and \"World\" have already been assigned colors",Toast.LENGTH_LONG).show();
    }

}
