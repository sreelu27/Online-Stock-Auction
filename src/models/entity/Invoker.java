package models.entity;
//Author:Sreelekshmi Geetha
//Design Pattern:Command
//Invoker
public class Invoker {
	
	Command command;
	//Registering command 
	
	public void setCommand(Command command) {
		this.command=command;
	}
	
	//instructs commands to carry out the request in response to an event
	public String invoke(Command command) {
		return command.execute();
	}

}
