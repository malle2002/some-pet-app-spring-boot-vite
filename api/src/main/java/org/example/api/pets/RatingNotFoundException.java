package org.example.api.pets;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(){
        super("Rating Not Found");
    }
}
