/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise4;

import Helpers.ConstantsHelper;
import Models.Article;
import Models.Type;
import Services.ArticleReader;
import Services.ArticleWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m
 */
public class Exercise4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            menu();
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
    }
    
    private static void insert(String title, String file, Type type) throws IOException {
        int numberOfArticles = readNum(title);
        ArticleWriter articleWriter = new ArticleWriter(file);
        for(int i = 0; i < numberOfArticles; i++){
            articleWriter.post(new Article(
                readString("Title: "),
                readNum("Code: "),
                type
            ));
        }
        articleWriter.close();
    }
    
    private static void insertArticles() throws IOException {
        insert("\nHow many articles do you want to insert? ",
                ConstantsHelper.MASTER,
                Type.NEW);
    }
    
    private static void insertModifications() throws IOException{
        insert("\nHow many modifications do you want to insert? ",
                ConstantsHelper.UPDATE,
                Type.MODIFY);
    }
    
    private static void read(String file) throws IOException, ClassNotFoundException {
        ArticleReader articleReader = new ArticleReader(file);
        Article article = articleReader.getArticle();
        while(!article.isSentinel()){
            System.out.println(article);
            article = articleReader.getArticle();
        }
        articleReader.close();
    }
    
    private static void readArticles() throws IOException, ClassNotFoundException {
        read(ConstantsHelper.MASTER);
    }
    
    private static void readModifications() throws IOException, ClassNotFoundException {
        read(ConstantsHelper.UPDATE);
    }
    
    private static void readUpdatedArticles() throws IOException, ClassNotFoundException {
        read(ConstantsHelper.NMASTER);
    }
    
    private static void readAnomalies() throws ClassNotFoundException, IOException {
        read(ConstantsHelper.ANOM);
    }
    
    private static void updateArticles() throws IOException, ClassNotFoundException {
        ArticleReader masterReader = new ArticleReader(ConstantsHelper.MASTER);
        ArticleReader updateReader = new ArticleReader(ConstantsHelper.UPDATE);
        ArticleWriter nMasterWriter = new ArticleWriter(ConstantsHelper.NMASTER);
        ArticleWriter anomWriter = new ArticleWriter(ConstantsHelper.ANOM);
        
        Article update = updateReader.getArticle();
        Article master = masterReader.getArticle();
        
        while(!update.isSentinel()){
            while(master.getCode() < update.getCode()){
                nMasterWriter.post(master);
                master = masterReader.getArticle();
            }
            if(update.getCode() == master.getCode()) {
                nMasterWriter.post(update);
                master = masterReader.getArticle();
            }else{
                anomWriter.post(update);
            }
            update = updateReader.getArticle();
        }
        
        while(!master.isSentinel()) {
            nMasterWriter.post(master);
            master = masterReader.getArticle();
        }
        
        masterReader.close();
        updateReader.close();
        nMasterWriter.close();
        anomWriter.close();
    }
    
    private static void menu() throws IOException, ClassNotFoundException {
        showOptions();
        int selectedOption = readNum("\n\nType option's number: ");
            switch(selectedOption) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertArticles();
                    menu();
                    break;
                case 2:
                    insertModifications();
                    menu();
                    break;
                case 3:
                    updateArticles();
                    menu();
                    break;
                case 4:
                    readArticles();
                    menu();
                    break;
                case 5:
                    readModifications();
                    menu();
                    break;
                case 6:
                    readUpdatedArticles();
                    menu();
                    break;
                case 7:
                    readAnomalies();
                    menu();
                    break;
            }
    }
    
    private static void showOptions() {
        System.out.println("\n\nSelect an option:");
        System.out.println("\n\t1. Insert Articles");
        System.out.println("\t2. Insert modifications");
        System.out.println("\t3. Update Articles");
        System.out.println("\t4. See articles");
        System.out.println("\t5. See modifications");
        System.out.println("\t6. See updated articles");
        System.out.println("\t7. See anomalies");
        System.out.println("\t0. Exit");
    }
    
    private static int readNum(String msg) {
        int x = 0;
        try {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
            x = Integer.parseInt(s);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }
    
    private static String readString(String msg) {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return s;

    }
}
