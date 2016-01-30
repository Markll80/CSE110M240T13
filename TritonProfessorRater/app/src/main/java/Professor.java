/**
 * Professor class
 */
import java.util.ArrayList;
import java.util.List;

public class Professor {
    final String name;
    int avgHelpfulness;
    int avgEasiness;
    int avgClarity;
    List<String> comments;
    public Professor(String profName){
        name = profName;
        avgHelpfulness = 0;
        avgEasiness = 0;
        avgClarity = 0;
        comments = new ArrayList<String>();
        initializeSampleData();

    }

    private void initializeSampleData(){
        String commentA = "THIS COMMENT IS SO USELESS, BUT IT IS A STRING FOR TESTING, SO IT IS NOT USELESS THEN.... uhh";
        String commentB = "Sofa, guesss who is the first to comment ?!!";
        comments.add(commentA);
        comments.add(commentB);
    }






}
