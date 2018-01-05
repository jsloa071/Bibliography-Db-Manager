package com.company.data;

import java.util.*;

public class Journal {
    private String journalTitle;
    private ArrayList<Issue> issues;

    public Journal(String name){
        this.journalTitle = name;
    }

    public String getJournalTitle(){
        return this.journalTitle;
    }

    public ArrayList<Issue> getIssues(){
        return issues;
    }


    public static ArrayList<Journal> getSampleJournals(){
        Journal journal1 = new Journal("Journal of Cognitive Neuroscience");
        Journal journal2 = new Journal("Journal of Neuroscience");

        Issue j1_i1 = new Issue(2017,29,12, journal1);
        Issue j1_i2 = new Issue(2016,28,10, journal1);
        Issue j2_i1 = new Issue(2017,37,46, journal2);
        Issue j2_i2 = new Issue(2013, 33, 21, journal2);

        Article article1 = new Article("Neural Signatures of Spatial Statistical Learning",
                "Elisabeth Karuza",j1_i1);
        Article article2 = new Article("Restoration of fMRI Decodability Does Not Imply " +
                "Latent Working Memory States","Sebastian Schneegans",j1_i1);
        Article article3 = new Article("Distinct Neural Supression and Encoding Effects for Conceptual" +
                "Novelty and Familiarity","Niv Reggev", j1_i2);
        Article article4 = new Article("Neural Measures Reveal Implicit Learning during Language Processing",
                "Laura Batterink", j1_i2);
        Article article5 = new Article("Common Synaptic Input to Motor Neurons and Neural Drive to " +
                "Targeted Reinnervated Muscles", "Dario Farina", j2_i1);
        Article article6 = new Article("Baseline Levels of REM Sleep May Protect Against Excessive Activity " +
                "in Fear-Related Neural Circuitry","Itamar Lerner", j2_i1);
        Article article7 = new Article("Novel Flicker-Sensitive Visual Circuit Neurons Inhibited by " +
                "Stationary Patterns", "Laura Batterink", j2_i2);
        Article article8 = new Article("Salient Sounds Activate Human Visual Cortex Automatically",
                "John McDonald", j2_i2);

        //add articles to appropriate issue.articles
        ArrayList<Article> articlesToAdd = new ArrayList<Article>();
        articlesToAdd.add(article1);
        articlesToAdd.add(article2);
        j1_i1.setArticles(articlesToAdd);


        ArrayList<Article> articlesToAddj1i2 = new ArrayList<Article>();
        articlesToAddj1i2.add(article3);
        articlesToAddj1i2.add(article4);
        j1_i2.setArticles(articlesToAddj1i2);


        ArrayList<Article> articlesToAddj2i1 = new ArrayList<Article>();
        articlesToAddj2i1.add(article5);
        articlesToAddj2i1.add(article6);

        j2_i1.setArticles(articlesToAddj2i1);


        ArrayList<Article> articlesToAddj2i2 = new ArrayList<Article>();
        articlesToAddj2i2.add(article7);
        articlesToAddj2i2.add(article8);

        j2_i2.setArticles(articlesToAddj2i2);


        journal1.issues = new ArrayList<Issue>(2);
        journal1.issues.add(j1_i1);
        journal1.issues.add(j1_i2);
        journal2.issues = new ArrayList<Issue>(2);
        journal2.issues.add(j2_i1);
        journal2.issues.add(j2_i2);

        ArrayList<Journal> sampleJournals = new ArrayList<Journal>(2);
        sampleJournals.add(journal1);
        sampleJournals.add(journal2);

        return sampleJournals;

    }

}
