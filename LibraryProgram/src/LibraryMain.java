import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LibraryMain implements ActionListener{
	//컴포넌트 설정
	JFrame iFrame;
	JPanel iPaneLbl, temp;
	JButton newPerson, newButton, Borrow, Return, extProgram;
	JLabel iName;
	JTable bookTable, personTable;//이 프로그램은 JTable을 사용
	String category[] = {"책이름", "저자", "대출자","분류"};
	String category2[] = {"회원이름", "나이", "현재 대출 권수", "직업"};
	String category3[] = {"Student", "Faculty", "Guest", "Staff"};
	String category4[] = {"Magazine","EBook","Science","Art","Religion","기타"};
	DefaultTableModel model, model2;//DefaultTableModel을 사용
	JScrollPane tbl_sp, tbl_sp2;
	Person[] libPerson;
	int countPerson;
	Book[] libBook;
	int countBook;	
	public LibraryMain(){		
		libPerson = new Person[100];
		libBook = new Book[100];		
		countPerson = countBook = 0;
		
		iFrame=new JFrame("도서관리프로그램");//J프레임
		iFrame.setLayout(null);
		iFrame.setBounds(40, 40, 700, 500);
		iFrame.setResizable(false);
		iFrame.getContentPane().setBackground(new Color(50, 150, 100));
		
		newPerson=new JButton("회원등록");//이하 프로그램 구성 버튼
		newPerson.setBounds(30, 20, 150, 40);
		newPerson.addActionListener(this);
		iFrame.add(newPerson);

		newButton=new JButton("도서등록");
		newButton.setBounds(30, 90, 150, 40);
		newButton.addActionListener(this);
		iFrame.add(newButton);

		extProgram=new JButton("프로그램 종료");
		extProgram.setBounds(550, 20, 120, 30);
		extProgram.addActionListener(this);
		iFrame.add(extProgram);
		
		Borrow=new JButton("도서대여");
		Borrow.setBounds(250, 20, 150, 40);
		Borrow.addActionListener(this);
		iFrame.add(Borrow);
		
		Return=new JButton("도서반납");
		Return.setBounds(250, 90, 150, 40);
		Return.addActionListener(this);
		iFrame.add(Return);
		
		model = new DefaultTableModel(category,0);
		bookTable = new JTable(model);
		tbl_sp = new JScrollPane(bookTable);
		tbl_sp.setBounds(0, 150, 250, 400);		
		tbl_sp.getViewport().setBackground(new Color(50, 100, 100));
		iFrame.add(tbl_sp);
	
		model2 = new DefaultTableModel(category2, 0);
		personTable = new JTable(model2);
		tbl_sp2 = new JScrollPane(personTable);
		tbl_sp2.setBounds(250, 150, 440, 400);
		tbl_sp2.getViewport().setBackground(new Color(50, 100, 100));
		iFrame.add(tbl_sp2);	
		
		iFrame.setVisible(true);
	}
	private void load() {//library.txt 텍스트파일에 쓰고 저장하며 도서관리내역을 저장
		try {
			FileInputStream fis = new FileInputStream("library.txt");
			InputStreamReader isr = new InputStreamReader(fis, "MS949");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			if(br.readLine().trim().equals("Book")){
				while(true){
					temp = br.readLine().trim();
					if(!temp.equals("Person")){
						libBook[countBook] = new Book();
						libBook[countBook].setName(temp);
						libBook[countBook].setClassification(br.readLine().trim());
						libBook[countBook].setAuth(br.readLine().trim());
						temp = br.readLine().trim();
						if(temp.equals("null"))
							libBook[countBook++].setPersonname(null);
						else
							libBook[countBook++].setPersonname(temp);
					}
					else{
						while(true){
						libPerson[countPerson] = new Person() {
						};
						libPerson[countPerson].setName(br.readLine().trim());
						libPerson[countPerson].setAge(Integer.parseInt(br.readLine().trim()));
						libPerson[countPerson].setAddress(br.readLine().trim());
						libPerson[countPerson].setStatus(br.readLine().trim());
						libPerson[countPerson++].setNumofbook(Integer.parseInt(br.readLine().trim()));
						}
					}
				}
			}
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			refresh();
			return;
		}
		refresh();
	}
	public void actionPerformed(ActionEvent iEvent) {//이벤트 활용
		if(iEvent.getSource()==newPerson) {//회원등록버튼
			String name = JOptionPane.showInputDialog("이름을 입력하세요.");
			int age = Integer.parseInt(JOptionPane.showInputDialog("나이를 입력하세요."));
			String address = JOptionPane.showInputDialog("주소를 입력하세요.");
			
			int abc = JOptionPane.showOptionDialog(temp, "선택", "신분", 0, 0, null, category3, 0);
			switch (abc) {
			case 0:
				libPerson[countPerson++] = new Student(name, age, address);
				refresh();
				break;
			case 1:
				libPerson[countPerson++] = new Faculty(name, age, address);
				refresh();
				break;
			case 2:
				String visit = JOptionPane.showInputDialog("방문 목적을 입력하세요.");//Guest에게만 방문목적 질문
				libPerson[countPerson++] = new Guest(name, age, address, visit);
				refresh();
				break;
			case 3:
				libPerson[countPerson++] = new Staff(name, age, address);
				refresh();
				break;
			}
		}
		else if(iEvent.getSource()==newButton) {//도서등록버튼
			String name = JOptionPane.showInputDialog("책 이름을 입력하세요.");
			String auth = JOptionPane.showInputDialog("저자를 입력하세요.");	
			int aaa = JOptionPane.showOptionDialog(temp, "선택", "분류", 0, 0, null, category4, 0);
			switch (aaa) {
			case 0:
				libBook[countBook++] = new Magazine(name, auth);
				refresh();
				break;
			
			case 1:
				libBook[countBook++] = new EBook(name, auth);
				refresh();
				break;
			case 2:
				libBook[countBook++] = new Science(name, auth);
				refresh();
				break;
			case 3:
				libBook[countBook++] = new Art(name, auth);
				refresh();
				break;
			case 4:
				libBook[countBook++] = new Religion(name, auth);
				refresh();
				break;
			case 5:
				libBook[countBook++] = new Other(name, auth);
				refresh();
				break;
			}
		}
		else if(iEvent.getSource()==Borrow){//도서대여버튼
			int book = bookTable.getSelectedRow();
			int person = personTable.getSelectedRow();
			String bookname = (String) model.getValueAt(book, 0);
			String personname = (String) model2.getValueAt(person, 0);
			if(model.getValueAt(book, 2) !=null){//이미 빌려간 도서를 대여하려고 할 때
				JOptionPane.showMessageDialog(temp, "먼저\"" + bookname +"\"을 대여한 사람이 있습니다.");
				return;
			}
			borrow(bookname, personname);
		}
		else if(iEvent.getSource()==Return){//도서반납버튼
			int book = bookTable.getSelectedRow();
			if(model.getValueAt(book, 2)==null){//대여하지 않은 도서를 반납하고자 할 때
				JOptionPane.showMessageDialog(temp, "대여하지 않은 도서입니다.");
			}
			else{
				String personname = (String) model.getValueAt(book, 2);
				Return(personname, (String) model.getValueAt(book, 0));
				refresh();
			}			
		}
		else
		{
			try {
				FileOutputStream fos = new FileOutputStream("library.txt");//library.txt에 저장
				OutputStreamWriter osw = new OutputStreamWriter(fos, "MS949");
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write("Book\r\n");
				for(int i=0; i<countBook; i++){
					bw.write(libBook[i].getName()+"\r\n");
					bw.write(libBook[i].getAuth()+"\r\n");
					bw.write(libBook[i].getClassification()+"\r\n");
					bw.write(libBook[i].getPersonname()+"\r\n");
				}
				bw.write("Person\r\n");
				for(int i=0; i<countPerson; i++){
					bw.write(libPerson[i].getName()+"\r\n");
					bw.write(libPerson[i].getAge()+"\r\n");
					bw.write(libPerson[i].getAddress()+"\r\n");
					bw.write(libPerson[i].getStatus()+"\r\n");
					bw.write(libPerson[i].getNumofbook()+"\r\n");
				}
				bw.flush();
				osw.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
	private void Return(String personname, String bookname) {//도서반납시
		for (int i = 0; i < countPerson; i++) {
			if(libPerson[i].getName().equals(personname)){
				libPerson[i].setNumofbook(libPerson[i].getNumofbook()-1);
				break;
			}
		}
		for (int i = 0; i < countBook; i++) {
			if(libBook[i].getName().equals(bookname)){
				libBook[i].setPersonname(null);
				return;
			}
		}
	}
	private void borrow(String bookname, String personname) {//도서대여시
		for(int i=0; i<countPerson; i++){
			if(libPerson[i].getName().equals(personname)){
				libPerson[i].setNumofbook(libPerson[i].getNumofbook()+1);
				break;
			}
		}
		for(int i=0; i<countBook; i++){
			if(libBook[i].getName().equals(bookname)){
				libBook[i].setPersonname(personname);
				break;
			}
		}		
		refresh();
	}
	private void refresh() {//행추가를 위한 refresh()
		model = new DefaultTableModel(category, 0);
		for(int i=0; i<countBook; i++){
			model.addRow(libBook[i].getall());
		}
		model2 = new DefaultTableModel(category2, 0);
		for(int i=0; i<countPerson; i++){
			model2.addRow(libPerson[i].getall());
		}
		bookTable.setModel(model);
		personTable.setModel(model2);
		iFrame.invalidate();
	}
	public static void main(String[] args) {
		LibraryMain LM=new LibraryMain();
		LM.load();
	}
}