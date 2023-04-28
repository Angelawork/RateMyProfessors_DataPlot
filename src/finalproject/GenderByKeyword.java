package finalproject;

import java.util.ArrayList;
import java.util.LinkedList;

public class GenderByKeyword extends DataAnalyzer {
	private MyHashTable<String, Integer> maleTable;
	private MyHashTable<String, Integer> femaleTable;
	private MyHashTable<String, Integer> xTable;
	private MyHashTable<String, Integer> output;
	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		int wordNotFound=0;
		keyword=keyword.trim().toLowerCase();
		output=new MyHashTable<>(6);

		Integer my_count=maleTable.get(keyword);
		if(my_count==null){
			output.put("M",0);
			wordNotFound++;
		}
		else output.put("M",my_count);

		my_count=femaleTable.get(keyword);
		if(my_count==null){
			output.put("F",0);
			wordNotFound++;
		}
		else output.put("F",my_count);

		my_count=xTable.get(keyword);
		if(my_count==null){
			output.put("X",0);
			wordNotFound++;
		}
		else output.put("X",my_count);

		if(wordNotFound==3) return null;//if all of them dont contain the word, return null instead of a table of 0?
		else return output;
	}

	@Override
	public void extractInformation() {
		int comment_index = parser.fields.get("comments");
		int gender_index = parser.fields.get("gender");
		maleTable=new MyHashTable<>(180);
		femaleTable=new MyHashTable<>(180);
		xTable=new MyHashTable<>(180);

		for (int i = 0; i < parser.data.size(); i++) {
			String gender=parser.data.get(i)[gender_index];
			String comment=parser.data.get(i)[comment_index].toLowerCase();

			String[] words=replace(comment).split(" ");//comment.replaceAll("[^a-z']"," ").split(" ");

			MyHashTable<String,String> unique_words=new MyHashTable<>(150);
			for (String elmt : words) {
				unique_words.put(elmt,elmt);
			}
			ArrayList<String> mywords=unique_words.getKeySet();

			for (String elmt : mywords) {
				if(elmt.equalsIgnoreCase(""))continue;//empty strings should not be considered as words

				if(gender.equalsIgnoreCase("M")){
					Integer count=maleTable.get(elmt);
					if(count==null)maleTable.put(elmt,1);
					else maleTable.put(elmt,count+1);
				}else if(gender.equalsIgnoreCase("F")){
					Integer count=femaleTable.get(elmt);
					if(count==null)femaleTable.put(elmt,1);
					else femaleTable.put(elmt,count+1);
				}else if(gender.equalsIgnoreCase("X")){
					Integer count=xTable.get(elmt);
					if(count==null)xTable.put(elmt,1);
					else xTable.put(elmt,count+1);
				}
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
