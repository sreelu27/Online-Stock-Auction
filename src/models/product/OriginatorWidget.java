package models.product;

public class OriginatorWidget implements ProductOriginator{
	
	private String value = "";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		System.out.println("Originator: Setting state to " + value);
		this.value = value;
	}

	@Override
	public Memento createMemento() {
		WidgetMemento m= new WidgetMemento();
		m.setState(new String(value));
		System.out.println("Originator: Creating Memento.");
		return m;
	}
	
	@Override
	public void restore(Memento m) {
		WidgetMemento wm =(WidgetMemento) m;
		this.value=(wm.getState());
		System.out.println("Originator: State after restoring from Memento: " + wm.getState());
	}

	

}
