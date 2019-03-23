package models.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CareTaker {
	private Stack mementoStack, originatorStack;
	private OriginatorWidget ow;
	List<String> products;

	public CareTaker() {
		mementoStack = new Stack();
		originatorStack = new Stack();
	}

	public void setWidget(OriginatorWidget ow) {
		this.ow = ow;
	}

	public void undoOperation() {
		if(!originatorStack.isEmpty())
		{
			ProductOriginator o = (ProductOriginator) originatorStack.pop();
			o.restore((Memento) mementoStack.pop());
		}
		else
		{
			System.out.println("Empty state");
		}
	}
	
	public List<String> getProductList() {
		products =new ArrayList<String>();
		for (Object item: mementoStack) {
			products.add(((WidgetMemento)item).getState());
		}

		return products;
	}

	@SuppressWarnings("unchecked")
	public void setWidgetValue(String value) {
		ow.setValue(value);
		mementoStack.push(ow.createMemento());
		originatorStack.push(ow);
		

	}

	public String getWidgetValue() {
		return ow.getValue();
	}
}
