import java.util.ArrayList;
import java.util.Collections;

public class OrderStack {

	private ArrayList<Order> orders;
	
	public OrderStack() {
		
		orders = new ArrayList<Order>();
		
	}

	public void add_order(Order order) {

		this.orders.add(order);
		sort_stack();

	}
	
	public int get_stack_size() {
		
		return orders.size();
		
	}
	
	public Order get_nth_order(int n) {
		
		if(n >= orders.size()) {
			
			return null;
			
		} else {
		
		return orders.get(n);
		
		}
	}

	public void sort_stack() {
		
		Collections.sort(this.orders);
		
		
	}

	public void aggress_stack(Order order) {
		// TODO Auto-generated method stub

		Double current_size = order.get_size();


		while (!orders.isEmpty()) {
			
			orders.get(0).aggress_order(order);

			if (orders.get(0).get_size().equals(new Double(0))) {

				orders.remove(0);

			}

			Double new_size = order.get_size();

			if (new_size.equals(current_size) || new_size.equals(new Double(0)))  {

				break;

			} else {

				current_size = new_size;

			}

		}

	}

}
