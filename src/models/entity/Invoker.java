package models.entity;

public class Invoker {
	
	Command command;
	
	public void setCommand(Command command) {
		this.command=command;
	}
	
	public String invoke(Command command) {
		return command.execute();
	}

}
