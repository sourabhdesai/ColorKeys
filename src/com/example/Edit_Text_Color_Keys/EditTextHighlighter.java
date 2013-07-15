package com.example.Edit_Text_Color_Keys;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/9/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditTextHighlighter {
    private SettingsDatabase database;
    private int defaultColor;
    private int spaceToRight;
    private int endOfWordLeft;
    private TextWatcher textWatcher;
    /**
     * Initialize a new EditTextHighlighter object with three integer parameters that indicate the RGB color component values for the default color
     * @param rDefault the R component of the default text color
     * @param gDefault the G component of the default text color
     * @param bDefault the B component of the default text color
     */
    public EditTextHighlighter(int rDefault, int gDefault, int bDefault) {
        this.defaultColor = Color.rgb(rDefault,gDefault,bDefault);
        this.database = new SettingsDatabase("",this.defaultColor);
    }

    /**
     * Initialize a new EditTextHighlighter object with a single integer parameter that indicates the number of
     * @param color the default color of the text as expressed as a single integer value.
     */
    public EditTextHighlighter(int color) {
        this.defaultColor = color;
        this.database = new SettingsDatabase("",color);

    }

    private EditTextHighlighter() {}

    /**
     * Adds a keyword along with its highlight color defined in its RGB color components.
     * @param keyword
     * @param r
     * @param g
     * @param b
     *
     */
    public void addKeyWord(String keyword,int r, int g, int b) {
        int color = Color.rgb(r,g,b);

    }

    /**
     * Adds a keyword along with its highlight color defined as a single integer value.
     * @param keyword
     * @param color
     */
    public void addKeyWord(String keyword,int color) {
         this.database.addColoredWord(keyword,color);
    }

    /**
     *
     * @param key
     * @return color of keyword as set earlier
     */
    public int getColor(String key)    {
        int color = this.database.getColor(key);
        return color == -1?this.defaultColor:color;
    }

    /**
     * Add an array of keywords that all have the same color which is expressed in its RGB components
     * @param keys
     * @param r
     * @param g
     * @param b
     */
    public void addKeysWithSingleValue(String[] keys, int r, int g, int b)   {
        this.addKeysWithSingleValue(keys,Color.rgb(r,g,b));
    }

    /**
     * Add an array of keywords that all have the same color which is expressed as a single integer value
     * @param keys
     * @param color
     */
    public void addKeysWithSingleValue(String[] keys, int color)   {
        this.database.addWordsOfSameColor(keys,color);
    }

    public void addSettingsToEditText(EditText editText1)   {
        final EditText editText = editText1;
        final EditTextHighlighter highlighter = this;
        if(this.textWatcher==null) {
            this.textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //To change body of implemented methods use File | Settings | File Templates.

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>0)    {
                        String text = s.toString();
                        Editable editable = editText.getText();
                        if(start>=before)    { //new character(s) at the end of the text
                            for(int i = start+count-1;i>=0;i--)  {
                                 if(s.charAt(i)== ' ')   { //find space character to left.
                                    String word = text.substring(i+1,start+count);
                                    int color = highlighter.getColor(word);
                                    editable.setSpan(new ForegroundColorSpan(color),i,start+count,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //after you use setSpan, it seems as though the cursor resets back to the beginning of the edit text, this is to fix that
                                    editText.setSelection(start+count);
                                    break;
                                }
                            }

                        } else if(start != 0)  { //new character(s) in the middle of the text somewhere
                            int left = 0;
                            int right = start+count;
                            for(int i=start+count-1;i>=0;i--)   {
                                if(s.charAt(i)==' ' | i==0) {
                                    left = i;
                                    break;
                                }
                            }

                            for(int i=start;i<s.length();i++)   {
                                if(s.charAt(i)==' ') {
                                    right = i;
                                    break;
                                }
                            }
                            String word =left<right ? s.subSequence(left+1,right).toString(): s.subSequence(left,right).toString();
                            int color = highlighter.getColor(word);
                            editable.setSpan(new ForegroundColorSpan(color),left,right,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            editText.setSelection(start+count);
                        } else  { //start is 0, text is input at beginning of text
                            for(int n =0; n<s.length();n++) {
                                if(s.charAt(n)==' ' || n==s.length()-1)    {
                                    String word =n==s.length()-1?text.substring(0): text.substring(0,n);
                                    int color = highlighter.getColor(word);
                                    editable.setSpan(new ForegroundColorSpan(color),0,count,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    editText.setSelection(start+count);
                                    break;
                                }
                            }
                        }
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
        }
        editText.addTextChangedListener(this.textWatcher);
    }

}
