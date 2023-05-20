public class DLinkedNode<T>
{
    // Instance Variables
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    // Constructor
    public DLinkedNode(T data, double prio)
    {
        dataItem = data;
        priority = prio;
        next = null;
        prev = null;
    }

    // Default Constructor
    public DLinkedNode()
    {
        dataItem = null;
        priority = 0;
    }

    // Getter method to return priority
    public double getPriority()
    {
        return priority;
    }

    // Getter method to return data item
    public T getDataItem() {
        return dataItem;
    }

    // Getter method to return next node
    public DLinkedNode<T> getNext() {
        return next;
    }

    // Getter method to return previous node
    public DLinkedNode<T> getPrev() {
        return prev;
    }

    // Setter method to set data item
    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }

    // Setter method to set next node
    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    // Setter method to set previous node
    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }

    // Setter method to set priority
    public void setPriority(double priority) {
        this.priority = priority;
    }
}
