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





}
