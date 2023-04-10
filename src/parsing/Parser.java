package parsing;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class Parser{

    private ArrayList<String> tokens;

    // Construtor

    public Parser(){
        this.tokens = new ArrayList<String>();
    }

    // Metodos

    public void splitLine(String line){

        String[] result = line.split(";",0);

        for (String token: result){

            this.tokens.add(token);
        }
    }

    public void cleanParser(){
        this.tokens.clear();
    } 

    public ArrayList<String> getTokens(){
        return new ArrayList<String>(this.tokens);
    }

    public String toString(){

        return this.tokens
                    .stream()
                    .reduce("", (a,b) -> a + " " + b);
    }
}