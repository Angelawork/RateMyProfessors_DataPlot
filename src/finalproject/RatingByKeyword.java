package finalproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class RatingByKeyword extends DataAnalyzer {
	private MyHashTable<String, MyHashTable<String, Integer>> storageTable;
    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		keyword=keyword.trim().toLowerCase();
		MyHashTable<String, Integer> output=storageTable.get(keyword);
		return output;
	}

	@Override
	public void extractInformation() {
		int comment_index = parser.fields.get("comments");
		int rating_index = parser.fields.get("student_star");

		storageTable = new MyHashTable<>();
		for (int i = 0; i < parser.data.size(); i++) {
			double score = Double.parseDouble(parser.data.get(i)[rating_index]);
			String comment = parser.data.get(i)[comment_index].toLowerCase();

			int rating_number = (int) Math.floor(score);
			String rating = "3";
			switch (rating_number) {
				case 1:
					rating = "1";
					break;
				case 2:
					rating = "2";
					break;
				case 3:
					rating = "3";
					break;
				case 4:
					rating = "4";
					break;
				case 5:
					rating = "5";
					break;
			}

			String[] words = comment.replaceAll("[^a-z’]", " ").split(" ");//apostrophe char?

			//for unique words in 1 comment
			HashSet<String> unique_words=new HashSet<>();
			Collections.addAll(unique_words, words);
			words=unique_words.toArray(new String[unique_words.size()]);

			for (String elmt : words) {
				if (elmt.equalsIgnoreCase("")) continue;

				MyHashTable<String, Integer> infoTable=storageTable.get(elmt);
				if(infoTable==null){
					infoTable=new MyHashTable<>();
					infoTable.put("1",0);
					infoTable.put("2",0);
					infoTable.put("3",0);
					infoTable.put("4",0);
					infoTable.put("5",0);
				}
				infoTable.put(rating,infoTable.get(rating)+1);
				storageTable.put(elmt,infoTable);
			}
		}
	}
}
