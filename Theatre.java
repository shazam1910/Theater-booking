import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;


public class Theatre {

    // Initialize the arrays to keep track of sold and free seats
    public static int[] row1 = new int[12];
    public static int[] row2 = new int[16];
    public static int[] row3 = new int[20];


    // Creating an arraylist tickets to save all the Tickets
    public static  ArrayList<Ticket> ticketInfo = new ArrayList<>();

    // Creating method to get row count from the user
    public static int rowCount() {
        System.out.println("Enter the row number(1-3): ");
        while (!input.hasNextInt()) {   // Creating while loop to get the integer input from the user
            input.next();
            System.out.println("Enter the numerical value for row number: ");
        }
        int row = input.nextInt() - 1;
        if (row > 2 || row < 0) {
            System.out.println("Invalid row number");
            return rowCount();
        }
        return row;
    }

    // Creating method to get seat count from the user
    public static int seatCount(int row) {
        System.out.println("Enter the seat number : ");

        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Enter the numerical value for seat number: ");
        }
        int seat = input.nextInt() - 1;

        if (row == 0){
            if (seat < 0 || seat > 11) {
                System.out.println("Invalid seat number");
                return seatCount(row);
            }
        } else if (row == 1) {
            if (seat < 0 || seat > 15) {
                System.out.println("Invalid seat number");
                return seatCount(row);
            }
        } else if (row == 2) {
            if (seat < 0 || seat > 19) {
                System.out.println("Invalid seat number");
                return seatCount(row);
            }
        }
        return seat;
    }

    private static Scanner input = new Scanner(System.in);

    // Creating a main method
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        ;
        // Set all seats to 0 (free)
        for (int i = 0; i < row1.length; i++) {
            row1[i] = 0;
        }
        for (int i = 0; i < row2.length; i++) {
            row2[i] = 0;
        }
        for (int i = 0; i < row3.length; i++) {
            row3[i] = 0;
        }

        int option = -1;
        while (option != 0) {

            // Display the menu
            System.out.println("-------------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("-------------------------------------------------");

            // Getting the user's option
            System.out.print("Enter option: ");
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Enter the numerical value for option: ");
            }
            option = input.nextInt();

            switch (option) {
                case 1:
                    // Buy a ticket
                    buy_tickets();
                    break;
                case 2:
                    // Print seating area
                    print_seating_area();
                    break;
                case 3:
                    // Cancelling a ticket
                    cancel_ticket();
                    break;
                case 4:
                    // List available seats
                    show_available();
                    break;
                case 5:
                    // Save to file
                    save();
                    break;
                case 6:
                    // Load from file
                    load();
                    break;
                case 7:
                    // Print ticket information and total price
                    show_tickets_info();
                    break;
                case 8:
                    // Sort tickets by price
                    sort_tickets();
                    break;
                case 0:
                    // Quit
                    System.out.println("Quit");
                    break;
                default:
                    System.out.println("Invalid option selected");
                    break;
            }
        }
        System.out.println("Program terminated");
    }

    // Method to buy the tickets
    public static void buy_tickets() {
        Person person = getUserInfo();
        int row = rowCount();
        int seat =  seatCount(row);
        System.out.println("Price: ");
        double price = input.nextDouble();
        Ticket ticket = new Ticket(row+1,seat+1,price, person);

        if (row == 0) {
            if (row1[seat] == 0) {
                row1[seat]=1;
                System.out.println("The seat is booked");
                System.out.println("Ticket purchased successfully!");
                ticketInfo.add(ticket);
            }
            else {
                System.out.println("This seat is already booked");
                buy_tickets();
            }
        }
        else if (row == 1) {
            if (row2[seat] == 0) {
                row2[seat] = 1;
                System.out.println("The seat is booked");
                System.out.println("Ticket purchased successfully!");
                ticketInfo.add(ticket);
            }
            else {
                System.out.println("This seat is already booked");
                buy_tickets();
            }
        }
        else if (row == 2) {
            if (row3[seat] == 0) {
                row3[seat] = 1;
                System.out.println("The seat is booked");
                System.out.println("Ticket purchased successfully!");
                ticketInfo.add(ticket);
            }
            else {
                System.out.println("This seat is already booked");
                buy_tickets();
            }
        }
    }

    // Method to print the seating area with available and sold seats
    public static void print_seating_area() {
        System.out.println("       ***********");
        System.out.println("        * STAGE *");
        System.out.println("       ***********");

        System.out.print("      ");
        for (int i = 0; i < 12; i++) {
            if (row1[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == 5){
                System.out.print(" ");
            }
        }System.out.println();

        System.out.print("    ");
        for (int i = 0; i < 16; i++) {
            if (row2[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == 7){
                System.out.print(" ");
            }
        }System.out.println();

        System.out.print("  ");
        for (int i = 0; i < 20; i++) {
            if (row3[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == 9){
                System.out.print(" ");
            }
        }System.out.println();
    }

    // Method to cancel the tickets that have been booked by the user
    public static void cancel_ticket() {
        boolean checking = true;
        Person person = getUserInfo();
        int row = rowCount();
        int seat = seatCount(row);

        for (int i = 0; i < ticketInfo.size(); i++ ) {
            Ticket ticket = ticketInfo.get(i);
            if (person.getName().equals(ticket.getPerson().getName())) {    // Checking whether the username is correct
                if (person.getSurname().equals(ticket.getPerson().getSurname())) {  // Checking whether the surname is correct
                    if (person.getEmail().equals(ticket.getPerson().getEmail())) {  // Checking whether the email is correct
                        int rowUser = ticket.getRow()-1;
                        int seatUser = ticket.getSeat()-1;
                        if (rowUser == row && seatUser == seat) {
                            if (row == 0) {
                                if (row1[seat] == 1) {
                                    row1[seat] = 0;
                                    ticketInfo.remove(ticket);
                                    checking = false;
                                } else {
                                    System.out.println("The seat is not occupied");
                                }
                            } else if (row == 1) {
                                if (row2[seat] == 1) {
                                    row2[seat] = 0;
                                    ticketInfo.remove(ticket);
                                    checking = false;
                                } else {
                                    System.out.println("The seat is not occupied");
                                }
                            } else if (row == 2) {
                                if (row3[seat] == 1) {
                                    row3[seat] = 0;
                                    ticketInfo.remove(ticket);
                                    checking = false;
                                } else {
                                    System.out.println("The seat is not occupied");
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!checking) {
            System.out.println("Ticket cancelled successfully!");
        } else {
            System.out.println("The Account is wrong");
        }
    }

    // Method to show the available seats
    public static void show_available () {
        System.out.print("Seats available in row 1: ");
        for (int i=0, j=-1; i<12; i++){
            if (row1[i] == 0) {
                if (j != -1) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                j = i;
            }
        }System.out.println(".");

        System.out.print("Seats available in row 2: ");
        for (int i=0, j=-1; i<16; i++){
            if (row2[i] == 0) {
                if (j != -1) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                j = i;
            }
        }System.out.println(".");

        System.out.print("Seats available in row 3: ");
        for (int i=0, j=-1; i<20; i++){
            if (row3[i] == 0) {
                if (j != -1) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                j = i;
            }
        }System.out.println(".");
    }

    // Method the save the file
    public static void save()
    {
        try {
            FileWriter myWriter = new FileWriter("file.txt");
            for (int i = 0; i < 12; i++) {

                myWriter.write(String.valueOf(row1[i]) + " ");
            }
            myWriter.write("\n");

            for (int i = 0; i < 16; i++) {

                myWriter.write(String.valueOf(row2[i]) + " ");
            }
            myWriter.write("\n");


            for (int i = 0; i < 20; i++) {

                myWriter.write(String.valueOf(row3[i]) + " ");
            }
            myWriter.write("\n");
            System.out.println("file saved successfully!!!");
            myWriter.close();
        }
        catch (IOException e) {

            System.out.println("An error occurred.");
        }
    }

    // Load the file saved and restore the 3 arrays with the row's information
    public static void load() {
        try {
            FileReader myReader = new FileReader("file.txt");
            Scanner file_reader = new Scanner(myReader);
            for (int i = 0; i < 12; i++) {
                row1[i] = file_reader.nextInt();
            }
            for (int i = 0; i < 16; i++) {
                row2[i] = file_reader.nextInt();
            }
            for (int i = 0; i < 20; i++) {
                row3[i] = file_reader.nextInt();
            }
            System.out.println("file loaded successfully!!!");
            myReader.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
    }

    // Method to get user information
    private static Person getUserInfo(){
        System.out.println("Enter your name: ");
        String name = input.next();

        System.out.println("Enter your surname: ");
        String surname = input.next();

        System.out.println("Enter your email: ");
        String email = input.next();

        Person person = new Person(name, surname, email);

        return person;
    }

    // Method to show ticket information
    public static void show_tickets_info() {
        boolean check = true;
        Person person = getUserInfo();
        double Total= 0;
        for (int i = 0; i < ticketInfo.size(); i++ ) {
            Ticket ticket = ticketInfo.get(i);
            if (person.getName().equals(ticket.getPerson().getName())) {                  // Checking whether the username is correct
                if (person.getSurname().equals(ticket.getPerson().getSurname())) {       // Checking whether the surname is correct
                    if (person.getEmail().equals(ticket.getPerson().getEmail())) {      // Checking whether the email is correct
                        double myPrice = ticket.getPrice();
                        Total += myPrice;
                        ticket.print();
                        check = false;
                    }
                }
            }
        }
        if(!check) {
            System.out.println("Total ticket price is " + Total);
        } else {
            System.out.println("The Account is wrong");
        }
    }

    // Creating method to sorting the tickets in ascending order
    public static void  sort_tickets() {
        boolean exchanged = true;

        while (exchanged) {
            exchanged = false;

            for (int i = 0; i < ticketInfo.size() - 1; i++) {
                Ticket ticket = ticketInfo.get(i);
                double price = ticket.getPrice();
                Ticket ticketTemp = ticketInfo.get(i + 1);
                double price1 = ticketTemp.getPrice();

                if (price > price1) {
                    ticketInfo.set(i, ticketTemp);
                    ticketInfo.set(i + 1, ticket);
                    exchanged = true;
                }
            }
        }
        for (int  i = 0; i < ticketInfo.size(); i++){
            Ticket ticket = ticketInfo.get(i);
            ticket.print();
        }
    }
}

//References
// Creating while loop to get the integer input from the user = https://kodejava.org/how-do-i-validate-input-when-using-scanner/




