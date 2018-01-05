package com.company.data;

public class Article {
    private String title;
    private String author;
    private Issue issue;

    public Article(String title, String author, Issue issue){
        this.title = title;
        this.author = author;
        this.issue = issue;
    }

    public String getAuthor() {
        return author;
    }

    public void setIssue(Issue issue){ this.issue = issue; }

    @Override
    public String toString(){
        return author + ". " + title + ". " + issue.getJournal().getJournalTitle() + ", (" + issue.getIssue() + "), " + issue.getYear();
    }

}
