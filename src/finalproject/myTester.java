package finalproject;

import finalproject.MyHashTable;

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
/*
        long startTime = System.currentTimeMillis();
        Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered.csv");
        parser.read();
        RatingByKeyword ratingByKeyword = new RatingByKeyword(parser);
        MyHashTable<String, Integer> dist4 = ratingByKeyword.getDistByKeyword("terrible");
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("RatingByKeyword Runtime in ms: " + duration);

 */
        Parser parser = new Parser("/src/testing.csv");
        parser.read();
        DataAnalyzer ratingByKeyword = new RatingByGender(parser);
        MyHashTable<String, Integer> output = ratingByKeyword.getDistByKeyword("  m, q ,ua, li ty          ");//return null

        MyHashTable<Integer,String> timer = new MyHashTable<>();
        String[] vals={"a","b","c","d","e"};

        //---------------------------------------------//
        for(int j=0;j<10000;j++){
            timer.put(j,vals[j%5]);
        }
        long startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("put 10000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");
        //---------------------------------------------//

        timer = new MyHashTable<>();
        for(int j=0;j<50000;j++){
            timer.put(j,vals[j%5]);
        }
        startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("put 50000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");
        //---------------------------------------------//

        timer = new MyHashTable<>();
        for(int j=0;j<70000;j++){
            timer.put(j,vals[j%5]);
        }
        startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("put 70000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");
        //---------------------------------------------//
        timer = new MyHashTable<>();
        for(int j=0;j<100000;j++){
            timer.put(j,vals[j%5]);
        }
        startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("put 100000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");
        //---------------------------------------------//
        timer = new MyHashTable<>();
        for(int j=0;j<130000;j++){
            timer.put(j,vals[j%5]);
        }
        startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("put 130000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");
        //---------------------------------------------//
        timer = new MyHashTable<>();
        for(int j=0;j<150000;j++){
            timer.put(j,vals[j%5]);
        }
        startTime = System.currentTimeMillis();
        timer.put(500,"hi");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("put 150000 Runtime in ms: " + duration);

        removeRuntime(timer);

        setgetRuntime(timer,'K');
        setgetRuntime(timer,'V');
        rehashtime(timer);
        System.out.println("-------------------------");





    }

    static void removeRuntime(MyHashTable<Integer,String> timer){
        long startTime = System.currentTimeMillis();
        timer.remove(4);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("remove Runtime in ms: " + duration);
    }
    static void setgetRuntime(MyHashTable<Integer,String> timer,char x){
        long startTime = System.currentTimeMillis();
        //ArrayList<Integer> yz=new ArrayList<>();
        if(x=='K'){timer.getKeySet();}
        else timer.getValueSet();

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("get Set of "+x+" for Runtime in ms: " +duration);
    }
    static void rehashtime(MyHashTable<Integer,String> timer){
        long startTime = System.currentTimeMillis();
        timer.rehash();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("remove Runtime in ms: " + duration);
    }
}
