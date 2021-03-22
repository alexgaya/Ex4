/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Helpers.ConstantsHelper;
import Models.Article;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author m
 */
public class ArticleWriter {
    private final ObjectOutputStream objectOutputStream;
    
    public ArticleWriter(String s) throws FileNotFoundException, IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                ConstantsHelper.DATA_FOLDER+"/"+s));
    }
    
    public void post(Article article) throws IOException{
        objectOutputStream.writeObject(article);
    }
    
    public void close() throws IOException{
        post(Article.sentinel);
        objectOutputStream.close();
    }
}
