
public class SellOrder extends Order {
	
	
	public SellOrder(String ticker, Double level, Double size) {
		
		this.ticker = ticker;
		this.level = level;
		this.size = size;
		
		
	}

	@Override
	public void aggress_order(Order other_order) {
		// TODO Auto-generated method stub
		if(other_order instanceof BuyOrder && this.ticker.equals(other_order.ticker)) {
			
			if (other_order.get_level() >= this.get_level()) {

				if (other_order.get_size() >= this.get_size()) {

					other_order.set_size(other_order.get_size() - this.get_size());
					this.set_size(0.0);

				} else {

					
					this.set_size(this.get_size() - other_order.get_size());
					other_order.set_size(0.0);

				}

			}
			
		}
		
	}

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		if (this.get_level() < o.get_level()) {

			return -1;

		} else if (this.get_level() > o.get_level()) {

			return 1;

		} else {

			if (this.get_size() > o.get_size()) {

				return -1;

			} else if (this.get_size() < o.get_size()) {

				return 1;

			} else {

				return 0;

			}

		}

	}
	
	public String toString() {
		
		
		return "Sell " + this.size + " " + this.ticker + " @ $" + this.level;
		
	}


}
