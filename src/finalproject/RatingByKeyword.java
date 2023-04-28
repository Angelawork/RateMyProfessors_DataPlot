package finalproject;

import java.util.ArrayList;

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

		storageTable = new MyHashTable<>(250);
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

			String[] words = replace(comment).split(" ");
			
			MyHashTable<String,String> unique_words=new MyHashTable<>(150);
			for (String elmt : words) {
				unique_words.put(elmt,elmt);
			}
			ArrayList<String> mywords=unique_words.getKeySet();//runs in O(n) of n words in a comment

			for (String elmt : mywords) {
				if (elmt.equalsIgnoreCase("")) continue;

				MyHashTable<String, Integer> infoTable=storageTable.get(elmt);
				if(infoTable==null){
					infoTable=new MyHashTable<>(8);
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
	private String replace(String target){
		StringBuilder output=new StringBuilder();
		for(int i=0;i<target.length();i++){
			char word=target.charAt(i);
			if(!(word>='a'&&word<='z') && word!='\''){
				output.append(' ');
			}else{
				output.append(word);
			}
		}
		return output.toString();
	}
}
