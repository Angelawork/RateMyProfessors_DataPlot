package finalproject;

import java.util.ArrayList;

public class RatingByGender extends DataAnalyzer{
	private MyHashTable<String, MyHashTable<String, MyHashTable<String, Integer>>> myTable;
	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		try {
			String[] words = keyword.trim().split(",");//only consider "," or ", ", otherwise return null
			String gender = words[0].toUpperCase();
			String type = words[1].toLowerCase();
			if(type.charAt(0)==' '){
				type=type.substring(1).replaceAll("\\s+$", "");
			}
			if(!gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("m")) return null; //return null for all other genders

			initialize_gender(gender);//ed post #1699, we should return a table of 0
			MyHashTable<String, MyHashTable<String, Integer>> genderTable=myTable.get(gender);
			if(genderTable!=null) return genderTable.get(type);
			else {return null;}//return null if not found
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void extractInformation() {
		int gender_index = parser.fields.get("gender");
		int rating_index=parser.fields.get("student_star");
		int diff_index = parser.fields.get("student_difficult");

		myTable=new MyHashTable<>();

		for(int i=0;i< parser.data.size();i++) {
			String gender = parser.data.get(i)[gender_index];
			double quality = Double.parseDouble(parser.data.get(i)[rating_index]);
			double difficulty = Double.parseDouble(parser.data.get(i)[diff_index]);

			int qual_num = (int) Math.floor(quality);
			int diff_num = (int) Math.floor(difficulty);
			String quality_rating = "5";
			String diff_rating = "3";
			switch (qual_num) {
				case 1:
					quality_rating = "1";
					break;
				case 2:
					quality_rating = "2";
					break;
				case 3:
					quality_rating = "3";
					break;
				case 4:
					quality_rating = "4";
					break;
				case 5:
					quality_rating = "5";
					break;
			}
			switch (diff_num) {
				case 1:
					diff_rating = "1";
					break;
				case 2:
					diff_rating = "2";
					break;
				case 3:
					diff_rating = "3";
					break;
				case 4:
					diff_rating = "4";
					break;
				case 5:
					diff_rating = "5";
					break;
			}

			MyHashTable<String, MyHashTable<String, Integer>> genderTable = myTable.get(gender);
			if(genderTable==null) genderTable=new MyHashTable<>(6);

			MyHashTable<String, Integer> qualityTable=genderTable.get("quality");
			if (qualityTable == null) {
				qualityTable = new MyHashTable<>(7);//run in O(1)?
				qualityTable.put("1", 0);
				qualityTable.put("2", 0);
				qualityTable.put("3", 0);
				qualityTable.put("4", 0);
				qualityTable.put("5", 0);
			}
			qualityTable.put(quality_rating, qualityTable.get(quality_rating) + 1);
			genderTable.put("quality", qualityTable);

			MyHashTable<String, Integer> diffTable =genderTable.get("difficulty");
			if (diffTable == null) {
				diffTable = new MyHashTable<>(7);//run in O(1)?
				diffTable.put("1", 0);
				diffTable.put("2", 0);
				diffTable.put("3", 0);
				diffTable.put("4", 0);
				diffTable.put("5", 0);
			}
			diffTable.put(diff_rating, diffTable.get(diff_rating) + 1);
			genderTable.put("difficulty", diffTable);

			myTable.put(gender,genderTable);
		}
	}

	private void initialize_gender(String gender){
		MyHashTable<String, MyHashTable<String, Integer>> temp_genderTable=myTable.get(gender);
		if(gender.equalsIgnoreCase("m") && temp_genderTable==null){
			if(myTable.get("F")!=null){
				temp_genderTable = new MyHashTable<>(6);

				MyHashTable<String, Integer> qualityTable = new MyHashTable<>(7);
				qualityTable.put("1", 0);
				qualityTable.put("2", 0);
				qualityTable.put("3", 0);
				qualityTable.put("4", 0);
				qualityTable.put("5", 0);
				MyHashTable<String, Integer> diffTable = new MyHashTable<>(7);
				diffTable.put("1", 0);
				diffTable.put("2", 0);
				diffTable.put("3", 0);
				diffTable.put("4", 0);
				diffTable.put("5", 0);

				temp_genderTable.put("quality", qualityTable);
				temp_genderTable.put("difficulty", diffTable);
				myTable.put(gender, temp_genderTable);
			}
		}else if(gender.equalsIgnoreCase("f") && temp_genderTable==null){
			if(myTable.get("M")!=null){
				temp_genderTable = new MyHashTable<>(6);

				MyHashTable<String, Integer> qualityTable = new MyHashTable<>(7);
				qualityTable.put("1", 0);
				qualityTable.put("2", 0);
				qualityTable.put("3", 0);
				qualityTable.put("4", 0);
				qualityTable.put("5", 0);
				MyHashTable<String, Integer> diffTable = new MyHashTable<>(7);
				diffTable.put("1", 0);
				diffTable.put("2", 0);
				diffTable.put("3", 0);
				diffTable.put("4", 0);
				diffTable.put("5", 0);

				temp_genderTable.put("quality", qualityTable);
				temp_genderTable.put("difficulty", diffTable);
				myTable.put(gender, temp_genderTable);
			}
		}

	}
}
