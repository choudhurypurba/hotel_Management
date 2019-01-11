import java.util.ArrayList;
import java.util.List;

public class HotelLogic {

    List<Customer> customer = new ArrayList<>();
    List<Room> room = new ArrayList<>();
    List<Booking> bookingList = new ArrayList<>();

    public HotelLogic() {

        //customers list
        customer.add(new Customer("1", "1", "1", "1"));
        customer.add(new Customer("2", "2", "2", "3"));
        customer.add(new Customer("3", "3", "3", "3"));
        customer.add(new Customer("4", "4", "4", "4"));

        //rooms list
        room.add(new Room(1, 1, 100, false));
        room.add(new Room(2, 2, 200, false));
        room.add(new Room(3, 3, 300, false));
        room.add(new Room(4, 4, 400, false));

    }

    public Customer getCustomer(String ssn) {

        for (int i = 0; i < customer.size(); i++) {

            if (customer.get(i).getSsn().equals(ssn)) {
                return customer.get(i);
            }

        }
        return null;
    }

    public List<Customer> getCustomers() {

        return customer;
    }

    public Room getRoom(int id) {

        for (int i = 0; i < room.size(); i++) {

            if (room.get(i).getRoomNumber() == id) {

                room.get(i).setIsBooked(true);
                return room.get(i);
            }

        }
        return null;
    }

    public List<Room> getRooms() {

        return room;
    }

    public List<Room> getAvailableRooms() {

        List<Room> avail = new ArrayList<>();

        for (int i = 0; i < room.size(); i++) {
            if (!room.get(i).isIsBooked()) {

                avail.add(room.get(i));

            }
        }

        return avail;
    }

    public Boolean addCustomer(Customer addCustomer) {

        customer.add(addCustomer);

        return true;
    }

    public Boolean addRoom(Room addRoom) {

        for (int i = 0; i < room.size(); i++) {

            if (room.get(i).getRoomNumber() == addRoom.getRoomNumber()) {

                return false;
            }
        }
        room.add(addRoom);

        return true;
    }
     public void removeRoom(int roomNumber){

        Boolean removedStatus = room.removeIf(item -> item.getRoomNumber() == roomNumber ? true : false );

        if(removedStatus)
            System.out.println("Room " + roomNumber + " removed successsfully");
        else
            System.out.println("Room " + roomNumber + " doesn't exist");
    }

    public Booking searchByBookingId(int bookingId){

//    	bookingList.forEach(System.out::println);

        Booking booking = bookingList.stream().filter(item -> item.getBookingId() == bookingId ? true : false).findAny().orElse(null);
        if(booking != null)
            return booking;
        else
            return null;
    }
    
    public Boolean checkInCustomer(String ssn, Booking booking) {

        this.bookingList.add(booking);

        return true;
    }

        public Boolean checkOutCustomer(String ssn, int bookingId) {

        List<Room> tempRoomList = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++) {

            if(bookingList.get(i).getBookingId() == bookingId){

                tempRoomList = bookingList.get(i).getRoomList();

            }



        }
            
           for (Room room1 : tempRoomList) {

            int num = room1.getRoomNumber();
            for (int i = 0; i < room.size(); i++) {
                if (num == room.get(i).getRoomNumber()) {
                    room.get(i).setIsBooked(false);
                }
            }

        }
        return true;         


  }

}
