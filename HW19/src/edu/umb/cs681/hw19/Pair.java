package edu.umb.cs681.hw19;

import java.util.*;

public class Pair{
   String first;
   double second;

   // constructor for assigning values
   Pair(String first,Double second){
       this.first = first;
       this.second = second;
   }

   // function which returns a
   Pair values(){
       return new Pair(first,second);
   }

   // printing the pair class
   @Override
   public String toString(){
       return "("+first+","+second+")";
   }
}

