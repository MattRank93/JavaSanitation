package com.example.javasanitation.middleware;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RanksSanitizers {


//    [$gt]|[$eq]|[$lt]|[$lte]|
    public String MongoInput(String unsanitized){
        Pattern pattern = Pattern.compile("(gte)|(gt)|(eq)|(lt)|(lte)|[$]", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(unsanitized).replaceAll("");
    }



}
