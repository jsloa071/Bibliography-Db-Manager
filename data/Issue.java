package com.company.data;

import java.util.*;

public class Issue {
    private int year;
    private int volume;
    private int issue;
    private ArrayList<Article> articles;
    private Journal journal;

    public Issue(int year, int volume, int issue, Journal journal){
        this.year = year;
        this.volume = volume;
        this.issue = issue;
        this.journal = journal;
    }

    public int getYear(){
        return this.year;
    }

    public int getIssue(){
        return this.issue;
    }

    public Journal getJournal(){
        return this.journal;
    }

    public void setJournal(Journal j){this.journal = j;}

    public ArrayList<Article> getArticles(){
        return articles;
    }

    public void addArticle(Article articleToAdd){
        articles.add(articleToAdd);
    }

    public void setArticles(ArrayList<Article> a){
        this.articles = a;
    }


    @Override
    public String toString(){
        return journal.getJournalTitle() + ", Volume " + volume + ", Issue " + issue;
    }
}
