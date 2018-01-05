package com.company.data;

import java.util.*;

public class BibliographyDatabase {
    //list of journals in database
    private ArrayList<Journal> journals;

    //tracks whether database in use or not
    private boolean inSession;

    public void setJournals(ArrayList<Journal> journals){
        this.journals = journals;
    }

    public void startDatabaseSession(){
        inSession = true;
    }

    public void endDatabaseSession(){
        inSession = false;
    }

    public boolean getInSession(){
        return inSession;
    }

    public ArrayList<Article> getArticlesFromYear(int y){
        ArrayList<Article> articlesFromYear = new ArrayList<Article>();
        for (Journal journal : journals) {
            for (Issue issue : journal.getIssues()) {
                if (y == issue.getYear()) {
                    for(Article article : issue.getArticles()){
                        articlesFromYear.add(article);
                    }
                }
            }
        }
        return articlesFromYear;
    }

    public ArrayList<Issue> getIssuesFromDB(){
        ArrayList<Issue> issuesFromDB = new ArrayList<Issue>(0);
        for(Journal journal : journals){
            for(Issue issue : journal.getIssues()){
                issuesFromDB.add(issue);
            }
        }
        return issuesFromDB;
    }

    public void addJournal(Journal journalToAdd){

        this.journals.add(journalToAdd);
    }

    public void removeJournal(Journal journalToRemove){
        if(journals.contains(journalToRemove)) {
            journals.remove(journalToRemove);
        }
    }

    public void addListOfJournals(ArrayList<Journal> journalsToAdd){
        journals.addAll(journalsToAdd);
    }

    public void addIssue(Issue issueToAdd, Journal journalToAddTo){
        for(Journal journal : this.journals){
            if(journal.equals(journalToAddTo)){
                journal.getIssues().add(issueToAdd);
                issueToAdd.setJournal(journal);
            }
        }
    }

    public void removeIssue(Issue issueToRemove){
        for(Journal journal : this.journals){
            if (journal.getIssues().contains(issueToRemove)){
                journal.getIssues().remove(issueToRemove);
                issueToRemove.setJournal(null);
            }
        }
    }

    public void addArticleToIssue(int choice, String inputTitle, String inputAuthor){
        Issue issueToAddTo = this.getIssuesFromDB().get(choice);
        issueToAddTo.addArticle(new Article(inputTitle, inputAuthor, issueToAddTo));
    }

    public void removeArticle(Article articleToRemove){
        for(Journal journal : this.journals){
            for(Issue issue : journal.getIssues()){
                if(issue.getArticles().contains(articleToRemove)){
                    issue.getArticles().remove(articleToRemove);
                    articleToRemove.setIssue(null);
                }
            }
        }
    }

    public String findMostPublishedAuthor() {

        String mostPublishAuthor;
        Map<String, Integer> mapAuthors = new HashMap<String, Integer>();
        for (Journal journal : journals) {
            for (Issue issue : journal.getIssues()) {
                for (Article article : issue.getArticles()) {
                    if (mapAuthors.containsKey(article.getAuthor())) {
                        mapAuthors.put(article.getAuthor(), mapAuthors.get(article.getAuthor()) + 1);
                    } else {
                        mapAuthors.put(article.getAuthor(), 1);
                    }
                }
            }
        }
        return mapAuthors.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }
}