
public class Guest extends Person{
	String visit;
	public Guest(String name, int age, String address, String visit) {
		super();
		super.status = "Guest";
		super.name = name;
		super.age = age;
		super.address = address;
		this.visit = visit;
	}
}
