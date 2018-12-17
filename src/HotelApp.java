import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelApp {

    HotelLogic hotelLogic = new HotelLogic();
    Customer c;
    Booking booking = new Booking();

    public void showMenu() {

        Scanner getInput = new Scanner(System.in);
        boolean yORn = true;
        int getIndex;
        String ssn, name, add, tel;
        int roomNum, numOfBeds;
        double price;
        String isBook;

        while (yORn) {

            System.out.println("1.  Customer Details");
            System.out.println("2.  Customer List");

            System.out.println("3.  Room List");
            System.out.println("4.  Check Available Rooms");
            System.out.println("5.  Add Customer");
            System.out.println("6.  Add Room");
            System.out.println("7.  Checkin Customer");
            System.out.println("8.  Checkout Customer");
            System.out.println("9.  Remove Customer");
            System.out.println("10. Exit\n");
            System.out.print("Press num: ");

            getIndex = getInput.nextInt();

            System.out.println();

            switch (getIndex) {

                case 1:

                    System.out.print("Enter ssn number:");
                    c = hotelLogic.getCustomer(getInput.next());
                    System.out.println("Customer Details");
                    System.out.println("SSN: " + c.getSsn() + "\nName: " + c.getName() + "\nAddress: " + c.getAddress() + "\nTelephone: " + c.getTelephoneNumber());
                    System.out.println();
                    break;

                case 2:
                 break;



                case 3:

                    List<Room> roomlist = hotelLogic.getRooms();

                    for (int i = 0; i < roomlist.size(); i++) {

                        if (roomlist.get(i).isIsBooked()) {

                            isBook = "Booked";
                        } else {
                            isBook = "Available";
                        }
                        System.out.println("Room num: " + roomlist.get(i).getRoomNumber() + "\nNo of beds: " + roomlist.get(i).getNumberOfBeds() + "\nPrice: " + roomlist.get(i).getPricePerNight() + "\nRoom is: " + isBook);
                        System.out.println();
                    }

                    break;

                case 4:

                    List<Room> availRooms = hotelLogic.getAvailableRooms();

                    for (int i = 0; i < availRooms.size(); i++) {

                        System.out.println("Room num: " + availRooms.get(i).getRoomNumber() + "\nNo of beds: " + availRooms.get(i).getNumberOfBeds() + "\nPrice: " + availRooms.get(i).getPricePerNight());
                        System.out.println();
                    }

                    break;

                case 5:

                    System.out.print("Enter SSN: ");

                    ssn = getInput.next();
                    System.out.print("\nEnter Name: ");

                    name = getInput.next();
                    System.out.print("\nEnter Address: ");

                    add = getInput.next();
                    System.out.print("\nEnter Telephone: ");

                    tel = getInput.next();

                    if (hotelLogic.addCustomer(new Customer(ssn, name, add, tel))) {
                        System.out.println("\nCustomer Add successfully\n");
                    }

                    break;

                case 6:

                    System.out.print("Enter Room number: ");

                    roomNum = getInput.nextInt();
                    System.out.print("\nEnter no of beds: ");

                    numOfBeds = getInput.nextInt();
                    System.out.print("\nEnter price per night: ");

                    price = getInput.nextDouble();

                    if (hotelLogic.addRoom(new Room(roomNum, numOfBeds, price, false))) {
                        System.out.println("\nRoom Add successfully\n");
                    } else {
                        System.out.println("\nRoom number already exists\n");
                    }

                    break;

                case 7:
                    break;

                case 8:
                case 9:

                    break;
                case 10:

                    yORn = false;
                    break;

            }

        }

    }

    public static void main(String[] args) {
        System.out.println("--------------------\n");
        System.out.println("Welcome to Hotel XYZ\n");
        System.out.println("--------------------\n");
        System.out.println("Hotel Menu\n");
        HotelApp app = new HotelApp();
        app.showMenu();

    }

}
