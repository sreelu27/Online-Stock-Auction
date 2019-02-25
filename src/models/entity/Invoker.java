package models.entity;

public class Invoker {
	
	void placeOrder(Command command) {
		command.execute();
	}

}
