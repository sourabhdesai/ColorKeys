package com.example.Edit_Text_Color_Keys;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/9/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */

public class StringPhoneLattice {
    private char aChar;
    private StringPhoneLattice[] nexts;

    public StringPhoneLattice()	{
        this.aChar = ' ';
        this.nexts = new StringPhoneLattice[0];
    }

    private StringPhoneLattice(String str)  {
        if(str.length()==1) {
            this.aChar = str.charAt(0);
            this.nexts = new StringPhoneLattice[0];
            return;
        }
        this.aChar = str.charAt(0);
        this.nexts =new StringPhoneLattice[] {new StringPhoneLattice(str.substring(1))};
    }

    /**
     * Adds a new word to the phone lattice
     * @param str
     */
    public void addWord(String str)   {
        if(str == null || str.length()==0) return;
        str = " "+str+" ";
        boolean firstCharsMatch = this.aChar == str.charAt(0);
        this.addWordRecursive(firstCharsMatch?str.substring(1):str, firstCharsMatch,this);
    }

    private void addWordRecursive(String key, boolean foundThread,StringPhoneLattice root)  {
        //A base case! It reached the end of a thread so it needs to extend it by however much it has to
        if(this.nexts.length==0)    {
            this.nexts = new StringPhoneLattice[] {new StringPhoneLattice(key)};
            return;
        }
        char currentChar = key.charAt(0);
        //A base case! The string is now only one letter so all there is left to do is see if the single char in it is already a value in one of the leaves. If not, must create a new leaf with its single char in it!
        if(key.length()==1) {
            //At this point, the following for loop checks if the word has been added previously.Will return if it has!
            for(StringPhoneLattice lattice : this.nexts)    {
                if(lattice.aChar == currentChar)    {
                    return;
                }
            }
            this.addNewLatticeToNexts(new StringPhoneLattice(key));
            return;
        }
        if(foundThread) {
            for(StringPhoneLattice lattice : this.nexts)    {
                if(lattice.aChar == currentChar)    {
                    lattice.addWordRecursive(key.substring(1),true,root);
                    return;
                }
            }
            this.addNewLatticeToNexts(new StringPhoneLattice(key));
            return;
        } else  {
            for(StringPhoneLattice lattice : this.nexts)    {
                StringPhoneLattice childLattice = lattice.getChildContainingChar(currentChar);
                if(childLattice != null) {
                    childLattice.addWordRecursive(key.substring(1),true,root);
                    return;
                }
            }
            root.addNewLatticeToNexts(new StringPhoneLattice(key));
            return;
        }
    }

    private StringPhoneLattice getChildContainingChar(char c)    {
        if(this.nexts.length == 0) return null;
        if(this.aChar == c) return this;
        for(StringPhoneLattice lattice : this.nexts)    {
            if(lattice.getChildContainingChar(c) != null) return lattice;
        }
        return null;
    }

    private StringPhoneLattice getClosestLeafChild()  {
        if(this.nexts.length==0) return this;
        StringPhoneLattice childWithLeastChildren = this.nexts[0];
        int minimumChildren = this.nexts[0].nexts.length;
        for(StringPhoneLattice lattice : this.nexts)    {
            if (lattice.nexts.length<minimumChildren) {
                childWithLeastChildren = lattice;
                minimumChildren = lattice.nexts.length;
            }
        }
        return childWithLeastChildren.getClosestLeafChild();
    }

    private void addNewLatticeToNexts(StringPhoneLattice newNext)  {
        StringPhoneLattice[] newNexts = new StringPhoneLattice[this.nexts.length+1];
        for(int i=0;i<this.nexts.length;i++)  {
            newNexts[i] = this.nexts[i];
        }
        newNexts[this.nexts.length] = newNext;
        this.nexts = newNexts;
    }

    public boolean contains(String key) {
        if(key==null || key.length()==0) return false;
        key = " "+key+" ";
        boolean firstCharsMatch = this.aChar == key.charAt(0);
        return this.containsRecursive(firstCharsMatch?key.substring(1):key, firstCharsMatch);
    }

    private boolean containsRecursive(String key,boolean foundThread) {
        char currentChar = key.charAt(0);
        if(this.nexts.length==0) {
            if(key.length() == 1)  {
                return this.aChar == currentChar;
            }
            return false;
        }
        if(key.length()==1)	{
            for(StringPhoneLattice lattice : this.nexts)	{
                if(lattice.aChar == currentChar) return true;
            }
            return false;
        }
        if(foundThread)  {
            for(StringPhoneLattice lattice : this.nexts)    {
                if(lattice.aChar == currentChar)  {
                    return lattice.containsRecursive(key.substring(1),true);
                }
            }
            //Always use .substring(1) if you know that the aChar variable of the StringPhoneLattice that you are calling the containsRecursive method on matches the currentChar
            return false;
        } else   {
            //Still searching for a matching char
            for(StringPhoneLattice lattice : this.nexts) {
                //Finally found the thread (maybe...)
                if(lattice.aChar == currentChar) {
                    return lattice.containsRecursive(key.substring(1),true);
                }
            }
            //Still didnt find the thread!
            for(StringPhoneLattice lattice : this.nexts) {
                //If the recursive calls on the lattice leaf gives us a true value, then return true! else, continue on to the next leaf available
                if(lattice.containsRecursive(key,false)) return true;
            }
            //none of the recursive calls on the leaves gave us a true value! The word was not found anywhere in the tree :(
            return false;
        }
    }


}

