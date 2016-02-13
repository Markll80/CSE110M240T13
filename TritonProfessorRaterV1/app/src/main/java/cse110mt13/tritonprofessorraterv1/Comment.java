package cse110mt13.tritonprofessorraterv1;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("Comment")
public class Comment extends ParseObject{


    public void setup(String comment){
        put("comment", comment);
        put("numLikes", 0);
    }

    public String getComment() {
        return getString("Comment");
    }

    public int getNumLikes() {
        return getInt("numLikes");
    }

    public void putComment(String comment){
        put("comment",comment);
    }

    public void addNumLikes(){
        put("numLikes",getNumLikes()+1);
    }

    public void setComment(String comment, int numLikes){
        put("comment", comment);
        put("numLikes", numLikes);
    }

    public void addComment(String comment){
        add("comments",comment);
    }

    @Override
    public String toString(){
        return getComment() + "\nLikes: "+getNumLikes();
    }
}
