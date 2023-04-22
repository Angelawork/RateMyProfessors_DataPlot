package finalproject;

public class RatingDistributionByProf extends DataAnalyzer {

	private MyHashTable<String, MyHashTable<String, Integer>> storageTable;
    public RatingDistributionByProf(Parser p) {
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
		int name_index=parser.fields.get("professor_name");
		int rating_index=parser.fields.get("student_star");
		storageTable=new MyHashTable<>();

		for(int i=0;i< parser.data.size();i++){
			String name=parser.data.get(i)[name_index].trim().toLowerCase();
			double score=Double.parseDouble(parser.data.get(i)[rating_index]);

			int rating_number= (int) Math.floor(score);
			String rating="3";
			switch(rating_number){
				case 1:
					rating="1";
					break;
				case 2:
					rating="2";
					break;
				case 3:
					rating="3";
					break;
				case 4:
					rating="4";
					break;
				case 5:
					rating="5";
					break;
			}

			MyHashTable<String, Integer> infoTable=storageTable.get(name);
			if(infoTable==null){
				infoTable=new MyHashTable<>(5);
				infoTable.put("1",0);
				infoTable.put("2",0);
				infoTable.put("3",0);
				infoTable.put("4",0);
				infoTable.put("5",0);
			}

			infoTable.put(rating,infoTable.get(rating)+1);
			storageTable.put(name,infoTable);
		}
	}

}
