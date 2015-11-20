package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		
		Tuple tuple = child.next();
		while (tuple != null){
			if(child.from.equals(whereTablePredicate) == false){
				return tuple;
			}
			for (int x = 0; x < child.getAttributeList().size(); x++) {
				if (child.getAttributeList().get(x).getAttributeName().equals(whereAttributePredicate)){
						if(child.getAttributeList().get(x).getAttributeValue().equals(whereValuePredicate)){
							return tuple;
						}
				}
			}
			tuple = child.next();
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}
}