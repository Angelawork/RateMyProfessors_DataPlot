package finalproject;

import java.util.*;

public class myTester {
    static public void main(String[] a){
        /*
        long startTime = System.currentTimeMillis();
        Parser parser = new Parser("/src/RateMyProf_Data_Gendered.csv");
        parser.read();
        RatingDistributionByProf ratingDistributionByProf = new RatingDistributionByProf(parser);
        MyHashTable<String, Integer> dist = ratingDistributionByProf.getDistByKeyword("soazig  le bihan");
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        System.out.println("RatingDistributionByProf Runtime in ms: " + duration);

         */

        /*
        startTime = System.currentTimeMillis();
        parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered.csv");
        parser.read();
        RatingDistributionBySchool ratingDistributionBySchool = new RatingDistributionBySchool(parser);
        MyHashTable<String, Integer> dist2 = ratingDistributionBySchool.getDistByKeyword("pennsylvania college of technology");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("RatingDistributionBySchool Runtime in ms: " + duration);

         */

        /*
        startTime = System.currentTimeMillis();
        parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered.csv");
        parser.read();
        GenderByKeyword genderByKeyword = new GenderByKeyword(parser);
        MyHashTable<String, Integer> dist3 = genderByKeyword.getDistByKeyword("amazing");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("RatingByGender Runtime in ms: " + duration);

         */

        long startTime = System.currentTimeMillis();
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered.csv");
        parser.read();
        RatingByKeyword ratingByKeyword = new RatingByKeyword(parser);
        MyHashTable<String, Integer> dist4 = ratingByKeyword.getDistByKeyword("terrible");
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("RatingByKeyword Runtime in ms: " + duration);
        
        /*
        MyHashTable<Integer,String> tester = new MyHashTable<>();
        DataAnalyzer analyzer;
        tester.put(new Integer(2),"2 key");
        Iterator x=tester.iterator();
        MyPair y=(MyPair)x.next();
        System.out.println(y.getKey());
        System.out.println(y.getValue());
        System.out.println(((MyPair)x.next()));

        tester.put(new Integer(2),"2 key");
        tester.put(new Integer(4),"4 key");
        tester.put(new Integer(8),"8 key");
        tester.put(new Integer(16),"8 key");
        tester.put(new Integer(19),"19 key");
        tester.put(new Integer(25),"25 key");
        tester.put(new Integer(30),"25 key");
        tester.remove(4);
        System.out.println(tester.getKeySet().toString());
        System.out.println(tester.getValueSet().toString());
        tester.rehash();
        System.out.println(tester.getKeySet().toString());
        System.out.println(tester.getValueSet().toString());

        System.out.println("she\'s a very fair grader.".replaceAll("[^a-z’]", " "));
        System.out.println(Arrays.toString("she\'s a very fair grader.".replaceAll("[^a-z’]", " ").split(" ")));
*/






    }
}
