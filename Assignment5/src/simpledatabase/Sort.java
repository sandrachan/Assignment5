package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		if(tuplesResult.isEmpty()){
			Tuple tuple = child.next();
			ArrayList<Tuple> tuples = new ArrayList<Tuple>();
			while(tuple != null){
				tuples.add(tuple);
				tuple = child.next();
			}
			if(tuples.isEmpty() == false){
				tuple = tuples.get(0);
				int x = 0;
				for(int i = 0; i<tuple.attributeList.size();i++){
					if(tuple.getAttributeName(i).equals(orderPredicate)){
							x = i;
							break;
					}
				}
				while(tuples.isEmpty() == false){
					int m = 0;
					for(int j = 0; j<tuples.size();j++){
						if(tuples.get(j).getAttributeValue(x).toString().compareTo(tuples.get(m).getAttributeValue(x).toString())<0){
							m = j;
						}
					}
					tuplesResult.add(tuples.get(m));
					tuples.remove(m);
				}
			}
			else{
				return null;
			}
		}
		return tuplesResult.remove(0);
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}