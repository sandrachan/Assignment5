package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple leftTuple = leftChild.next();
		Tuple rightTuple = rightChild.next();

		while(leftTuple != null){
			tuples1.add(leftTuple);
			leftTuple = leftChild.next();
		}
		
		while(rightTuple != null){
			for(int x = 0; x < tuples1.size(); x++) {
                if(rightTuple.attributeList.get(2).attributeValue.equals(tuples1.get(x).attributeList.get(0).attributeValue)) {
                	rightTuple.attributeList.addAll(tuples1.get(x).attributeList);
                }
            }
            return rightTuple;
        }
        return null;
    }
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}