
import static org.junit.Assert.*;

import org.junit.*;

public class OrderTests {

	@Test
	public void test_order_setters_getters() {
		
		Double start_level = new Double(250);
		Double start_size = new Double(100);
		BuyOrder test_buy_order = new BuyOrder("TSLA",start_level,start_size);
		
		assertEquals(test_buy_order.get_level(),new Double(250.0)); //should be same level
		
		test_buy_order.set_level(new Double(275)); //set new level
		
		assertNotEquals(test_buy_order.get_level(),start_level); //level changed should be different
		
		assertEquals(test_buy_order.get_size(), new Double(100.0)); //check sizes
		
		test_buy_order.set_size(new Double(150));
		
		assertNotEquals(test_buy_order.get_size(), new Double(100.0)); //check sizes to make sure change stuck
		
		
	}
	
	@Test
	public void test_order_agress() {
		
		//Test 1
		Order buy_order_one = new BuyOrder("TSLA", new Double(250), new Double(100));
		Order sell_order_one = new SellOrder("TSLA", new Double(250), new Double(100));
		
		buy_order_one.aggress_order(sell_order_one);
		
		//same order size, should both be 0 after
		assertEquals(buy_order_one.get_size(), new Double(0));
		assertEquals(sell_order_one.get_size(), new Double(0));
		
		
		//Test 2
		Order buy_order_two = new BuyOrder("TSLA", new Double(250), new Double(100));
		Order sell_order_two = new SellOrder("TSLA", new Double(250), new Double(100));
		
		sell_order_two.aggress_order(buy_order_two);
		
		//same order size, should be the same going other way now
		assertEquals(buy_order_two.get_size(), new Double(0));
		assertEquals(sell_order_two.get_size(), new Double(0));	
		
		
		//Test 3
		Order buy_order_three = new BuyOrder("TSLA", new Double(250), new Double(100));
		Order sell_order_three = new SellOrder("TSLA", new Double(260), new Double(100));
		
		buy_order_three.aggress_order(sell_order_three);
		
		//price mismatch, sizes should remain unchanged
		assertEquals(buy_order_three.get_size(), new Double(100));
		assertEquals(sell_order_three.get_size(), new Double(100));			
		
		
		//Test 4
		Order buy_order_four = new BuyOrder("TSLA", new Double(250), new Double(100));
		Order sell_order_four = new SellOrder("TSLA", new Double(260), new Double(100));
		
		sell_order_four.aggress_order(buy_order_four);
		 
		//price mismatch, sizes should remain unchanged going other way
		assertEquals(buy_order_four.get_size(), new Double(100));
		assertEquals(sell_order_four.get_size(), new Double(100));	
		
		//Test 5
		
		Order buy_order_five = new BuyOrder("TSLA", new Double(250), new Double(100));
		Order sell_order_five = new SellOrder("TSLA", new Double(240), new Double(50));
		
		buy_order_five.aggress_order(sell_order_five);
		 
		//check if size decrements are correct for differing size orders agressing a buy
		assertEquals(buy_order_five.get_size(), new Double(50));
		assertEquals(sell_order_five.get_size(), new Double(0));	
		
		//Test 6
		
		Order buy_order_six = new BuyOrder("TSLA", new Double(250), new Double(25));
		Order sell_order_six = new SellOrder("TSLA", new Double(240), new Double(90));
		
		sell_order_six.aggress_order(buy_order_six);
		 
		//check if size decrements are correct for differing size orders agressing a sell
		assertEquals(buy_order_six.get_size(), new Double(0));
		assertEquals(sell_order_six.get_size(), new Double(65));
		
		//Test 7
		
		Order buy_order_seven = new BuyOrder("AAPL", new Double(250), new Double(50));
		Order sell_order_seven = new SellOrder("TSLA", new Double(250), new Double(50));

		buy_order_seven.aggress_order(sell_order_seven);

		//ticker mismatch, no trade should occur agressing buy
		assertEquals(buy_order_seven.get_size(), new Double(50));
		assertEquals(sell_order_seven.get_size(), new Double(50));

		//Test 8
		
		Order buy_order_eight = new BuyOrder("AAPL", new Double(250), new Double(50));
		Order sell_order_eight = new SellOrder("TSLA", new Double(250), new Double(50));

		sell_order_eight.aggress_order(buy_order_eight);

		//ticker mismatch, no trade should occur agressing sell
		assertEquals(buy_order_eight.get_size(), new Double(50));
		assertEquals(sell_order_eight.get_size(), new Double(50));		
		
				
		
	}
	
	@Test
	public void test_stack_operations() {
		
		
		OrderStack bid_stack_one = new OrderStack();
		Order test_buy = new BuyOrder("GE", new Double(15), new Double(5000));
		Order test_buy_two = new BuyOrder("GE", new Double(17), new Double(5000));
		
		bid_stack_one.add_order(test_buy);
		bid_stack_one.add_order(test_buy_two);
		
		//Test 1 add buy order then a better order, but make sure top of stack is the new better order
		assertEquals(bid_stack_one.get_nth_order(0), test_buy_two);
		assertEquals(bid_stack_one.get_nth_order(1), test_buy);
		
		
		OrderStack offer_stack_one = new OrderStack();
		Order test_sell = new SellOrder("WMT", new Double(5000), new Double(5000));
		Order test_sell_two = new SellOrder("WMT", new Double(4900), new Double(5000));
		
		offer_stack_one.add_order(test_sell);
		offer_stack_one.add_order(test_sell_two);
		
		//Test 2 add sell order then a better order, but make sure top of stack is the new better order
		assertEquals(offer_stack_one.get_nth_order(0), test_sell_two);
		assertEquals(offer_stack_one.get_nth_order(1), test_sell);
		
		
		
		OrderStack bid_stack_two = new OrderStack();
		Order test_buy_three = new BuyOrder("GE", new Double(15), new Double(5000));
		Order test_sell_three = new SellOrder("GE", new Double(15), new Double(5000));
		
		bid_stack_two.add_order(test_buy_three);
		bid_stack_two.aggress_stack(test_sell_three);
		
		//Test 3
		
		assertEquals(bid_stack_two.get_nth_order(0), null);
		
		
		
	}
	
	
}
