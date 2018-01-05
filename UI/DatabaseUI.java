package com.company.UI;

import java.util.*;
import com.company.data.*;


public class DatabaseUI {
    private BibliographyDatabase database;

    DatabaseUI(BibliographyDatabase db){
        this.database = db;
    }

    void useDatabase(){
        database.startDatabaseSession();
        System.out.println("Welcome to the Bibliography Database! \n");
        while(database.getInSession()){
            printMainMenu();
            int choice = menuChoice();
            switch(choice){
                case 1:
                    findArticlesInGivenYear();
                    break;
                case 2:
                    addArticleToIssue();
                    break;
                case 3:
                    System.out.println("The most published author is " + database.findMostPublishedAuthor());
                    break;
                case 4:
                    database.endDatabaseSession();
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
                    break;
            }
            System.out.println(); //blank line before next menu or end message
        }
        System.out.println("Goodbye!");
    }

    void printMainMenu(){
        System.out.println("MAIN MENU \nYou may perform the following operations: ");
        System.out.println("1. Find articles in a given year \n2. Add an article to an issue");
        System.out.println("3. Find the most-published author \n4. Exit");
        System.out.println("Please enter a numeric option between 1 and 4: ");
    }

    int menuChoice(){
        Scanner inputChoice = new Scanner(System.in);
        boolean validSelection = false;
        int choice;
        do {
            while (!inputChoice.hasNextInt()) {
                System.out.println("That is not a valid selection. Please try again.");
                inputChoice.nextLine();
            }
            choice = inputChoice.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("That is not a valid selection. Please try again.");
                inputChoice.nextLine();
            } else {
                validSelection = true;
            }
        }while(!validSelection);
        return choice;
    }

    void findArticlesInGivenYear(){
        System.out.println("\nWhich year would you like to search?");
        Scanner inputChoice = new Scanner(System.in);
        boolean validYear = false;
        int year;
        do {
            while (!inputChoice.hasNextInt()) {
                System.out.println("That is not a valid year. Please try again.");
                inputChoice.nextLine();
            }
            year = inputChoice.nextInt();
            //valid years: 1500 -> current year + 1
            if (year > (Calendar.getInstance().get(Calendar.YEAR) + 1) || year < 1500) {
                System.out.println("That is not a valid year. Please try again.");
                inputChoice.nextLine();
            }else{
                validYear = true;
            }
        }while(!validYear);

        ArrayList<Article> articlesFromYear = database.getArticlesFromYear(year);
        //OUTPUT BIBLIOGRAPHY FOR OPTION 1
        if (articlesFromYear.isEmpty()) {
            System.out.println("There were no articles in " + year + ".");
        } else {
            System.out.print("Articles from " + year + ": \n");
            for (Article article : articlesFromYear) {
                System.out.println(article.toString());
            }
        }
    }

    void addArticleToIssue() {
        System.out.print("Here are the available issues: \n");
        ArrayList<Issue> issuesInDatabase = database.getIssuesFromDB();
        for (Issue eachIssue : issuesInDatabase) {
            System.out.println((issuesInDatabase.indexOf(eachIssue) + 1) + ") " + eachIssue.toString());
        }
        System.out.println("\nPlease select an issue to add to: \n");
        Scanner inputChoice = new Scanner(System.in);
        int choice = -1;
        boolean validSelection = false;
        do {
            if (!inputChoice.hasNextInt()) {
                System.out.println("That is not a valid selection. Please try again.");
                inputChoice.nextLine();
            } else {
                choice = inputChoice.nextInt() - 1; //-1 for indexing
                if (choice < 0 || choice >= issuesInDatabase.size()) {
                    System.out.println("That is not a valid selection. Please try again.");
                    inputChoice.nextLine();
                } else {
                    validSelection = true;
                }
            }
        } while (!validSelection);
        inputChoice.nextLine(); //consume rest of line and move buffer to next line
        System.out.print("Please specify the article title: ");
        String titleToAdd = inputChoice.nextLine();
        System.out.print("Please specify the author's name: ");
        String authorToAdd = inputChoice.nextLine();
        database.addArticleToIssue(choice, titleToAdd, authorToAdd);
        System.out.println("Thank you! The article \"" + titleToAdd + "\" by " + authorToAdd + " has been added to " +
                database.getIssuesFromDB().get(choice) + ".");
    }


    public static void main(String[] args) {
        BibliographyDatabase db = new BibliographyDatabase();
        db.setJournals(Journal.getSampleJournals());

        DatabaseUI ui = new DatabaseUI(db);
        ui.useDatabase();
    }
}
