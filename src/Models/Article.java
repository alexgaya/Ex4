/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author m
 */
public class Article implements Serializable{
    
    private String title;
    private int code;
    private Type type;
    
    public static final Article sentinel = new Article("zzz", 999, 
            Type.SENTINEL);

    public Article() {
    }

    public Article(String title, int code, Type type) {
        this.title = title;
        this.code = code;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Article{" + "title=" + title + ", code=" + code + ", type=" + type + '}';
    }

    public String getTitle() {
        return title;
    }

    public int getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public boolean isSentinel() {
        return title.equals(sentinel.title)
                && code == sentinel.code
                && type.equals(sentinel.type);
    }
    
}
