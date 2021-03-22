/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Helpers.ConstantsHelper;
import Models.Article;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author m
 */
public class ArticleReader {
    private final ObjectInputStream objectInputStream;
    
    public ArticleReader(String s) throws FileNotFoundException, IOException {
        objectInputStream = new ObjectInputStream(
                new FileInputStream(ConstantsHelper.DATA_FOLDER+"/"+s));
    }
    
    public Article getArticle() throws IOException, ClassNotFoundException {
        return (Article) objectInputStream.readObject();
    }
    
    public void close() throws IOException{
        objectInputStream.close();
    }
}
