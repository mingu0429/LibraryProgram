import java.util.Vector;//이 프로그램은 Vector를 사용

public abstract class Person {
	public String name;//이름
	public int age;//나이
	public String address;//주소
	public int numofbook;//빌려간 도서 수
	String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumofbook() {
		return numofbook;
	}
	public void setNumofbook(int numofbook) {
		this.numofbook = numofbook;
	}
	public Book[] getBook() {
		return bk;
	}
	public void setBook(Book[] bk) {
		this.bk = bk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	Book[] bk;
	public Vector<Object> getall() {
		Vector<Object> myvector = new Vector<Object>();//벡터 선언
		myvector.add(name);
		myvector.add(age);
		myvector.add(numofbook);
		myvector.add(status);
		return myvector;
	}
}
