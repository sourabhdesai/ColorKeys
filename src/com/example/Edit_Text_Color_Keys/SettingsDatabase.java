package com.example.Edit_Text_Color_Keys;

import android.graphics.Color;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/10/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettingsDatabase {

    private int color;
    private StringPhoneLattice lattice;
    private SettingsDatabase next;

    private SettingsDatabase(int color) {
        this.color = color;
        this.lattice = new StringPhoneLattice();
    }

    /**
     * Creates a new SettingsDatabase object with a word and a color (as expressed by a single integer)
     * @param color
     * @param word
     */
    public SettingsDatabase(String word,int color)  {
        this.color = color;
        this.lattice = new StringPhoneLattice();
        this.lattice.addWord(word);
    }

    /**
     * Creates a new SettingsDatabase object with word and a color (as expressed by a three integer values representing the color's RGB components)
     * @param word
     * @param r
     * @param g
     * @param b
     */
    public SettingsDatabase(String word,int r, int g, int b)  {
        this.color = Color.rgb(r,g,b);
        this.lattice = new StringPhoneLattice();
        this.lattice.addWord(word);
    }

    /**
     *
     * @param key
     * @return if the key has not been added to the database it returns -1, else it returns the color set for the keyword
     */
    public int getColor(String key) {
        if(this.lattice.contains(key)) return this.color;
        if(this.next == null) return -1;

        return this.next.getColor(key);
    }

    public void addColoredWord(String word, int color)  {
        if(this.color == color) {
            this.lattice.addWord(word);
            return;
        }
        if(this.next == null)   {
            this.next = new SettingsDatabase(word,color);
            return;
        }
        this.next.addColoredWord(word,color);
    }

    public void addColoredWord(String word, int r,int g, int b)  {
        this.addColoredWord(word, Color.rgb(r, g, b));
    }

    public void addWordsOfSameColor(String[] words,int color)   {
        if(this.color == color) {
            for(String word : words)    {
                this.lattice.addWord(word);
            }
            return;
        }
        if(this.next == null)   {
            this.next = new SettingsDatabase(color);
            for(String word : words)    {
                this.next.lattice.addWord(word);
            }
            return;
        }

        this.next.addWordsOfSameColor(words,color);
    }

}
