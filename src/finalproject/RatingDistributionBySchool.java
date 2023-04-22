package finalproject;

import java.util.ArrayList;

public class RatingDistributionBySchool extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> storageTable;
	private MyHashTable<String, Double> scoreTable;
	private MyHashTable<String, MyHashTable<String, Integer>> outputTable;
	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword=keyword.trim().toLowerCase();
		MyHashTable<String, Integer> output=outputTable.get(keyword);
		return output;
	}

	@Override
	public void extractInformation() {
		int name_index = parser.fields.get("professor_name");
		int school_index = parser.fields.get("school_name");
		int rating_index=parser.fields.get("student_star");

		storageTable = new MyHashTable<>(150);
		scoreTable=new MyHashTable<>();
		for (int i = 0; i < parser.data.size(); i++) {
			String name = parser.data.get(i)[name_index].trim().toLowerCase();
			String school = parser.data.get(i)[school_index].trim().toLowerCase();
			double score=Double.parseDouble(parser.data.get(i)[rating_index]);

			MyHashTable<String, Integer> infoTable = storageTable.get(school);
			if (infoTable == null) {
				infoTable=new MyHashTable<>();//run in O(1)?
				infoTable.put(name, 1);
				storageTable.put(school, infoTable);
			} else {
				Integer total_reviews = infoTable.get(name);
				if(total_reviews!=null)
					infoTable.put(name,total_reviews+1);
				else
					infoTable.put(name,1);
			}

			Double total_score=scoreTable.get(name);
			if(total_score==null){
				scoreTable.put(name,score);
			}else{
				scoreTable.put(name,total_score+score);
			}
		}

		outputTable=new MyHashTable<>(150);
		for (int i = 0; i < parser.data.size(); i++) {
			String name = parser.data.get(i)[name_index].trim().toLowerCase();
			String school = parser.data.get(i)[school_index].trim().toLowerCase();
			double average = scoreTable.get(name)/storageTable.get(school).get(name);
			double rounded=(double) Math.round(average*100)/100;

			String output_name=name+"\n"+rounded;

			MyHashTable<String, Integer> schoolTable=outputTable.get(school);
			if(schoolTable==null) {
				schoolTable = new MyHashTable<>();
			}
			schoolTable.put(output_name,storageTable.get(school).get(name));
			outputTable.put(school,schoolTable);
		}
	}
}
