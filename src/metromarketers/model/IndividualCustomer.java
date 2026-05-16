package metromarketers.model;

public class IndividualCustomer extends Customer {
    private int age;
    private String gender;
    private String occupation;

    public IndividualCustomer(String id, String name, String address, String phone, String email, int age, String gender, String occupation) {
        super(id, name, address, phone, email);
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }

    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getOccupation() { return occupation; }

    @Override
    public String getCustomerType() { return "Individual"; }
}