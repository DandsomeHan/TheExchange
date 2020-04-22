import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class main {
	
	public static void print_help() {
		
		System.out.println();
		System.out.println("-a to see the best bid and offer for each ticker on the exchange");
		System.out.println();
		System.out.println("-o <B/S> <size> <ticker> <price> to enter a new order");
		System.out.println("      i.e. to buy 100 tsla @ $99.5: -o b 100 tsla 99.5");
		System.out.println("-t <ticker> to see the full stack for a ticker");
		System.out.println("      i.e. to see the full stack for Apple: -t aapl");
		System.out.println("-h to see this help message again");
		System.out.println();
		System.out.println("-q to exit");
				
	}
	
	public static HashMap<String, OrderBook> get_seeded_exchange(int n){
		
		
		HashMap<String, OrderBook> out_ex = new HashMap<String, OrderBook>();
		
		String[] tickers = new String[] {"AAPL", "AA", "TSLA", "SPY", "AMD", "F", "GE"};
		
		for (int i = 0; i < n; i++) {
			Order new_order;
			String ticker = tickers[new Random().nextInt(tickers.length)];
			Double level = new Double(new Random().nextInt(250) + 1);
			Double size = new Double(new Random().nextInt(1000) + 1);
			
			
			if(new Random().nextInt(2)==0) {
				  
				  new_order = new BuyOrder(ticker, level, size);
				  						  
			  } else{
				  
				  new_order = new SellOrder(ticker, level, size);
				  						  
			  }
			
			if (out_ex.get(ticker) == null) {

				out_ex.put(ticker, new OrderBook(new_order));

			} else {

				out_ex.get(ticker).add_order(new_order);

			}
						
		}
		
		return out_ex;
		
		
	}

	public static void main(String[] args) {

		HashMap<String, OrderBook> the_exchange = get_seeded_exchange(30);
		
		System.out.println("Welcome to the Exchange");
		System.out.println();
		print_help();
		
		boolean keep_looping = true;

		while (keep_looping) {

			Scanner darkly = new Scanner(System.in);

			String cur_line = darkly.nextLine().trim().replaceAll("\\s+", " ").toUpperCase();
			
			
			String[] cur_line_array = cur_line.split(" ");

		
			 switch(cur_line_array[0]) {
			 
			  case "-Q":
			  
				  System.out.println("Exiting the Exchange");
			  keep_looping = false; 
			  break;
			  
			  case "-H":
			  
				  print_help();
			  break;
			  
			  case "-T":
			  
				  try {
					  
					  String ticker = new String(cur_line_array[1]);
				  
				  if (the_exchange.get(ticker) == null) {

						System.out.println("There are no markets for "+ticker);

					} else {

						the_exchange.get(ticker).display_full_depth();

					} 
				  
				  
				  } catch(Exception e) {
						
					  System.out.println("The Depth of Market View did not follow the Input format");
					  System.out.println("      i.e. to see the full stack for Apple: -t aapl");
						
					}
				  				  
			  break; 
			  
			  case "-A":
				  
					for (Map.Entry<String, OrderBook> ticker : the_exchange.entrySet()) {
						System.out.println("------"+ticker.getKey()+"------");
						System.out.println();
						ticker.getValue().display_best_bid_offer();
						System.out.println();
						System.out.println();
					}
			  
			   
			  break; 
			  
			  case "-O":
				  
				  try {
					  
					  String side = new String(cur_line_array[1]);
					  String ticker = new String(cur_line_array[3]);
					  Double level = new Double(cur_line_array[4]);
					  Double size = new Double(cur_line_array[2]);
					  Order new_order;
					  
					  if(side.equals("B")) {
						  
						  new_order = new BuyOrder(ticker, level, size);
						  						  
					  } else if (side.equals("S")){
						  
						  new_order = new SellOrder(ticker, level, size);
  						  						  
					  } else {
						  
						  throw new Exception();
						  
					  }
					  
					  if (the_exchange.get(ticker) == null) {

							the_exchange.put(ticker, new OrderBook(new_order));

						} else {

							the_exchange.get(ticker).add_order(new_order);

						}
					  
					  the_exchange.get(ticker).display_full_depth();
					  
					  					  
				  } catch(Exception e){
					  
					  System.out.println("The Order did not follow the Input format");
					  System.out.println("      i.e. to buy 100 tsla @ $99.5: -o b 100 tsla 99.5");	  
				  }
				  				   
				  break;
			  
			  
			  default:
			  
			  System.out.println("The input did not match a command format"); 
			  print_help();
			  break; 
			  
			 
			 }
			 

		}
		
		

	}

}
