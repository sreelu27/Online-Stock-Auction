package models.product;

import java.util.Stack;

public class CareTaker {
	private Stack mementoStack, originatorStack;
	private OriginatorWidget ow;

	public CareTaker() {
		mementoStack = new Stack();
		originatorStack = new Stack();
	}

	public void setWidget(OriginatorWidget ow) {
		this.ow = ow;
	}

	public void undoOperation() {
		ProductOriginator o = (ProductOriginator) originatorStack.pop();
		o.restore((Memento) mementoStack.pop());
	}

	public void setWidgetValue(String value) {
		mementoStack.push(ow.createMemento());
		originatorStack.push(ow);
		ow.setValue(value);

	}

	public String getWidgetValue() {
		return ow.getValue();
	}
}
