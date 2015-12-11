package de.hub.util;

public class Ansi{
    
    public enum Attribute{
        NORMAL(0),
        BRIGHT(1),
        DIM(2),
        UNDERLINE(4),
        BLINK(5),
        REVERSE(7),
        HIDDEN(8);
        
        private String value;
        
        private Attribute(int value){
            this.value = String.valueOf(value);
        }
        public String toString(){
            return ""+value;
        }
    }
 
    public enum Color{ BLACK, RED, GREEN, YELLOW, BLUE,
        MAGENTA, CYAN, WHITE }
 
    private static final String PREFIX = "\u001b["; //NOI18N
    private static final String SUFFIX = "m";
    private static final String SEPARATOR = ";";
    private static final String END = PREFIX + SUFFIX;
 
    
    public static String format(String text, Color foreground){
    	return format(text, null, foreground, null);
    }
    
    public static String format(String text, Color foreground,
        Color background){
    	return format(text, null, foreground, background);
    }
    
    public static String format(String text, Attribute attr,
        Color foreground, Color background){  
        StringBuilder buff = new StringBuilder();
        
        if(attr!=null)
            buff.append(attr);
 
        if(foreground!=null){
            if(buff.length()>0) 
                buff.append(SEPARATOR);
            buff.append(30+foreground.ordinal());
        }
        
        if(background!=null){
            if(buff.length()>0)
                buff.append(SEPARATOR);
            buff.append(40+background.ordinal());
        }
        
        buff.insert(0, PREFIX);
        buff.append(SUFFIX);
        buff.append(text);
        buff.append(END);
        return buff.toString();
    }
}
