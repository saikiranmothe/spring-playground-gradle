package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FlightsController {

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Calendar calendar = new GregorianCalendar(2017,4,21,14,34);

        Map <Person, Integer> hm1 = new HashMap<Person, Integer>();
        Person person1 = new Person("Sai", "Sai");
        hm1.put(person1, 200);

        Flight flight1 = new Flight(calendar.getTime(), getPassengerInfo(hm1));

        Map <Person, Integer> hm2 = new HashMap<Person, Integer>();
        Person person2 = new Person("Sai", "Test");
        hm2.put(person2, 400);

        Flight flight2 = new Flight(calendar.getTime(), getPassengerInfo(hm2));

        flight2.departs = flight2.getDeparts();
        flight2.tickets = flight2.getTickets();

        return Arrays.asList(flight1, flight2);
    }

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Calendar calendar = new GregorianCalendar(2017,4,21,14,34);

        Map <Person, Integer> hm = new HashMap<Person, Integer>();
        Person person1 = new Person("Sai", "Sai");
        hm.put(person1, 200);

        Flight flight = new Flight(calendar.getTime(), getPassengerInfo(hm));

        flight.tickets = flight.getTickets();
        return flight;
    }


    @PostMapping("/flights/tickets/total")
    public FlightTotal getFlightTotal(@RequestBody Flight flight) {
        return new FlightTotal(flight);
    }

    public static class Flight {
        private Date departs;
        private List<PassengerInfo> tickets;

        public Flight() {}

        public Flight(Date date, List<PassengerInfo> tickets) {
            this.setDeparts(date);
            this.setTickets(tickets);
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        public Date getDeparts() {
            return departs;
        }

        public void setDeparts(Date departs) {
            this.departs = departs;
        }

        public List<PassengerInfo> getTickets() {
            return tickets;
        }

        public void setTickets(List<PassengerInfo> tickets) {
            this.tickets = tickets;
        }
    }

    public static List<PassengerInfo> getPassengerInfo(Map<Person, Integer> peoplePriceMap) {
        List<PassengerInfo> passengerInfoList = new ArrayList<PassengerInfo>();
        Iterator it = peoplePriceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            Person person = Person.class.cast(pair.getKey());
            int price = Integer.class.cast(pair.getValue());

            PassengerInfo passengerInfo = new PassengerInfo(person, price);
            passengerInfo.passenger = passengerInfo.getPassenger();
            passengerInfo.price = passengerInfo.getPrice();

            passengerInfoList.add(passengerInfo);
            it.remove();
        }

        return passengerInfoList;
    }

    public static class PassengerInfo {
        private Person passenger;
        private int price;

        public PassengerInfo() {}

        public PassengerInfo(Person passenger, int price) {
            this.setPerson(passenger);
            this.setPrice(price);
        }

        public Person getPassenger() {
            return passenger;
        }

        public void setPerson(Person person) {
            this.passenger = person;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static class Person {
        private String firstName;
        private String lastName;

        public Person() {}

        public Person(String firstName, String lastName) {
            this.setFirstName(firstName);
            this.setLastName(lastName);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static class FlightTotal {
        private String result;

        public FlightTotal(Flight flight) {
            int total = 0;
            for (PassengerInfo passengerInfo : flight.getTickets()) {
                total += passengerInfo.getPrice();
            }
            this.setResult(Integer.toString(total));
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

}