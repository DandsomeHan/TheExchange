
public class OrderBook {

	OrderStack bid_stack;
	OrderStack offer_stack;

	public OrderBook(Order order) {

		bid_stack = new OrderStack();
		offer_stack = new OrderStack();

		add_order(order);

	}

	public void display_full_depth() {
		
		for(int i = 0; i < Math.max(bid_stack.get_stack_size(), offer_stack.get_stack_size()); i++) {
			
			String cur_bid_str = bid_stack.get_nth_order(i) == null ? String.format("%1$25s", " ") : String.format("%1$25s", bid_stack.get_nth_order(i));
			String cur_offr_str = offer_stack.get_nth_order(i) == null ? String.format("%1$25s", " ") : String.format("%1$25s", offer_stack.get_nth_order(i));
			System.out.println(cur_bid_str + "  |  "+cur_offr_str);
			
		}
		
	}
	
	public void display_best_bid_offer() {
		int i = 0;
		String cur_bid_str = bid_stack.get_nth_order(i) == null ? String.format("%1$25s", " ") : String.format("%1$25s", bid_stack.get_nth_order(i));
		String cur_offr_str = offer_stack.get_nth_order(i) == null ? String.format("%1$25s", " ") : String.format("%1$25s", offer_stack.get_nth_order(i));
		System.out.println(cur_bid_str + "  |  "+cur_offr_str);
		
		
	}

	public void add_order(Order order) {

		if (order instanceof BuyOrder) {

			offer_stack.aggress_stack(order);

			if (order.get_size() > new Double(0)) {

				bid_stack.add_order(order);

			}

		} else {

			bid_stack.aggress_stack(order);

			if (order.get_size() > new Double(0)) {

				offer_stack.add_order(order);

			}

		}

	}

}
