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
            System.out.println("9.  Remove room");
            System.out.println("10.Edit customer informatiom");
            System.out.println("11.search for booking");
            System.out.println("12.Edit booking");
            System.out.println("20. Exit\n");
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
                    List<Customer> list = hotelLogic.getCustomers();

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("Customer id: " + (i + 1));
                        System.out.println("SSN: " + list.get(i).getSsn() + "\nName: " + list.get(i).getName() + "\nAddress: " + list.get(i).getAddress() + "\nTelephone: " + list.get(i).getTelephoneNumber());
                        System.out.println();
                    }

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
                    
                    boolean a = true;
                    int counter = 0;

                    System.out.print("Enter customer SSN: ");

                    String s = getInput.next();
                    c = hotelLogic.getCustomer(s);

                    List<Room> roomList2 = new ArrayList<>(),
                            rooms = hotelLogic.getAvailableRooms();

                    while (a) {
                        if (rooms.size() > 0) {
                            for (int i = 0; i < rooms.size(); i++) {

                                System.out.println("Room num: " + rooms.get(i).getRoomNumber() + "\nNo of beds: " + rooms.get(i).getNumberOfBeds() + "\nPrice: " + rooms.get(i).getPricePerNight());
                                System.out.println();

                            }

                            System.out.print("Enter Room number: ");

                            int num = getInput.nextInt();

                            for (int i = 0; i < rooms.size(); i++) {

                                if (num == rooms.get(i).getRoomNumber()) {
                                    roomList2.add(hotelLogic.getRoom(num));
                                    rooms.remove(i);
                                    System.out.println("\nRoom added successfully\n");
                                    counter++;
                                    break;
                                }
                            }

                            if (counter == 0) {
                                System.out.println("\nWrong room number added");
                            }
                            System.out.print("do you want to add more rooms? press y/n: ");

                            if (!getInput.next().equalsIgnoreCase("y")) {

                                a = false;
                            }
                            counter = 0;
                        } else {

                            a = false;
                            System.out.println("No rooms available");

                        }
                    }

                    System.out.println("Enter checkin date DD-MM-YYYY");
                    String[] checkindate = String.valueOf(getInput.next()).split("-");
                    System.out.println("Enter checkout date DD-MM-YYYY");
                    String[] checkoutdate = String.valueOf(getInput.next()).split("-");
                    booking.Booking(Integer.parseInt(c.getSsn()), new Date(Integer.parseInt(checkindate[2]), (Integer.parseInt(checkindate[1]) - 1), Integer.parseInt(checkindate[0])), new Date(Integer.parseInt(checkoutdate[2]), (Integer.parseInt(checkoutdate[1]) - 1), Integer.parseInt(checkoutdate[0])), roomList2);

                    if (hotelLogic.checkInCustomer(c.getSsn(), booking)) {
                        System.out.println("Booking success!\n");
                    }

                    break;

               case 8:

                    System.out.println("Enter SSN");
                    String ssnum = getInput.next();
                    System.out.println("Enter Booking (Note Booking id is same as SSN)");
                    int bookingId = getInput.nextInt();
                    c = hotelLogic.getCustomer(ssnum);
                    hotelLogic.checkOutCustomer(c.getSsn(), bookingId);

                    break; 
                    
                case 9:
                    System.out.println("Enter a room to be removed");
                    int roomNumber = getInput.nextInt();
                    
                	hotelLogic.removeRoom(roomNumber);

                    break;
                case 10:
                    
                	System.out.println("Enter a customer social security number : ");
                    String socialSecurityNumber = getInput.next();
                    
                    Customer customer = hotelLogic.getCustomer(socialSecurityNumber);
                    System.out.println("1. Edit name.");
                    System.out.println("2. Edit address.");
                    System.out.println("3. Edit telephone.");
                    
                    System.out.println("\nPress num :");
                    
                    int editOption = getInput.nextInt();
                    
                    if(editOption == 1){
                    	System.out.println("Enter the name to be changed : ");
                    	String nameToBeChanged = getInput.next();
                    	customer.setName(nameToBeChanged);
                    }
                    else if(editOption == 2){
                    	System.out.println("Enter the address to be changed : ");
                    	String addressToBeChanged = getInput.next();
                    	customer.setAddress(addressToBeChanged);
                    }
                    else if(editOption == 3){
                    	System.out.println("Enter the telephone number to be changed : ");
                    	String phoneToBeChanged = getInput.next();
                    	customer.setTelephoneNumber(phoneToBeChanged);	
                    }
                    else{
                    	System.out.println("Not a valid option");
                    }
                    break;
                case 11:
                    System.out.println("Enter the booking id:");
                	int bookingId1 = getInput.nextInt();
                	Booking booking = hotelLogic.searchByBookingId(bookingId1);
                	
                	if(booking != null){
                		System.out.println(booking.toString());
                	}
                	else{
                		System.out.println("No booking available");
                	}
                	
                	break;
                   
                case 20:

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
