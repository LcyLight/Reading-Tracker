# ReadingTracker

Welcome to the reading tracker! 

How to run:
    The reading tracker can either be ran from an IDE by running the application, or via command line. To run by command
    line open the command terminal at the directory where the classes folder is. From here, enter the command:

    Java --module-path "[Location of your JavaFX library]" --add-modules javafx.controls,javafx.fxml readtracker.demo3.ReadingTrackerApplication

.   Another way to run from command line is by jar file, to run by jar file, run the command line from the directory where 
    the jar file demo3.jar is located, for me, this is: C:\Users\poete\OneDrive\Desktop\demo3\out\artifacts\demo3_jar
    Then, enter the following command, replacing the javafx library location with your own:
    
    java --module-path "[JavaFX library file path]" --add-modules javafx.controls,javafx.fxml -jar demo3.jar

How to use:
    To add data, you can either load it from a csv text file, or enter it manually. To load from a file, open the 'File'
    menu at the top menu bar, then select 'Open from file'. Then select a csv text file you would like to use and it will
    read and load the information into the program. The lines in the text file must be in the format:

.       For reading list:
            READING LIST,[Title],[Author],[Interest (integer)],[valid genre]
        For book log:
            BOOK LOG,[Title],[Author],[Month],[Rating (integer)], [Pages (integer)], [valid genre]

.   Another way to load from a csv text file is by running the name of the file as a program argument in the IDE, or in
    the command line. Refer to the how to run information to see how to run from command line. By running with a file
    argument, the program will immediately load the information into the tracker.

.   Alternatively, to enter the data manually, simply select the radio button for what you would like to create, enter
    the appropriate information, then press the 'Create' button.

.   The data you add will be visible as titles in the book log and reading list text areas!
    Once you've added data, you can complete methods with your data using the program! To transfer a book from the 
    reading list to book log, select the List to Log radio button, add the appropriate information, then press 'Create'
.
    The other functions are accessible by the 'Functions' multiselect menu.
.
    To view book stats by genre, month, or rating, access these options in the top menu bar by their respective names.

Once you're done viewing your tracking information, you can save all your book info to a runnable csv text file by going
to the top menu bar, selecting 'File', then selecting 'Save to file'. From here, select the text file you would like to
save to and all the information will be written in a runnable format!

Enjoy and happy reading!
