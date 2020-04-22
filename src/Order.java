
abstract public class Order implements Comparable<Order>{
	
	protected Double level;
	protected Double size;
	protected String ticker;
	protected String timestamp;
	

	public void set_level(Double level) {
		
		this.level = level;
				
	}
	
	public Double get_level() {
		
			return this.level;	
		
	}
	
	public void set_size(Double size) {
		
		this.size = size;
		
		
	}
	
	public Double get_size() {
		
		return this.size;
	}
	
	abstract public void aggress_order(Order other_order);
	
	

}
