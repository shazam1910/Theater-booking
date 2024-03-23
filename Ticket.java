public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket (int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;

    }
    public void print(){
        System.out.println("Person Name: " + person.getName() );
        System.out.println("Person Surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());

        System.out.println("Row Number: " + row);
        System.out.println("Seat Number: " + seat);
        System.out.println("Price " + price);

    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }
}

