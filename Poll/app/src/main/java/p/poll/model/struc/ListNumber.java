package p.poll.model.struc;

/**
 * Created by Nicolas on 21/04/2018.
 */



public class ListNumber
{
    private Node first;
    public ListNumber()
    {
        first = null;
    }

    int remove()
    {
        if(first == null)
        {
            return -1;
        }
        else
        {
            int value = first.number;
            first = first.next;
            return value;
        }
    }

    public boolean isEmpty()
    {
        if(first == null) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void insert(int number)
    {
        Node current = first;
        if(first == null)
        {
            first = new Node(number);
        }
        else
        {
            if(first.next == null)
            {
                if(first.number < number)
                {
                    Node ajout = new Node(number);
                    first.next = ajout;
                    return;
                }
                else
                {
                    Node ajout = new Node(number);
                    ajout.next = first;
                    first = ajout;
                    return;
                }
            }
            if(first.number > number)
            {
                Node ajout = new Node(number);
                ajout.next = first;
                first = ajout;
            }
            else
            {
                while(current.next != null)
                {
                    if(current.next.number < number)
                    {
                        current = current.next;
                    }
                    else
                    {
                        Node ajout = new Node(number);
                        ajout.next = current.next;
                        current.next = ajout;
                        return;
                    }
                }
                Node ajout = new Node(number);
                current.next = ajout;
            }
        }
    }
}
