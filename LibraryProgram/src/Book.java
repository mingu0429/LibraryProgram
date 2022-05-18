import java.util.Vector;

public class Book {
	boolean borrow;
	public String name;
	int num;
	public String auth;
	public String publisher;
	String personname; 
	public Book(String name2, String auth2) {
		borrow = false;
		name = name2;
		auth = auth2;
	}
	public Book() {} 
	public Vector<Object> getall() { 
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(name);
		myvector.add(auth);
		myvector.add(personname);
		myvector.add(classification);
		return myvector;
	}
	public boolean isBorrow() {
		return borrow;
	}
	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	String classification;
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
}
