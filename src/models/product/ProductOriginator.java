package models.product;

public interface ProductOriginator {
	
	public void restore(Memento m);
	
	public Memento createMemento();

}
