package com.example.user.lab12;

public class Data{
    Result result;
    class Result{
        int limit;
        int offset;
        int count;
        Results[] results;
        class Results{
            int _id;
            String Station;
            String Destination;
            String UpdateTime;
        }
    }
}