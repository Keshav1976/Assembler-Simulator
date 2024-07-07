import java.util.*;

class Members{
    String name;
    int age;
    long number;
    long id;
    int count;
    int fines;
    List<Books> BOOK;

    public void mybook( ){

        if(BOOK.size()!=0){
            // for(int i=0;i<member.BOOK.size();i++){
            //   System.out.println(member.BOOK);
            for(Books bb: BOOK){
                System.out.println("BookId: "+bb.getID());
                System.out.println("title: "+bb.getT());
                System.out.println("author: "+bb.getA()+"\n\n");
            }
        }
        else{
            System.out.println("\nYou have not issued any Book \n");
        }
    }

    public void returnbook(int brID){

        Books rbook = null;
        for (Books bb : BOOK) {
            if (bb.getID() == brID) {
                rbook = bb;
                break;
            }
        }

        if (rbook != null) {
            long currentTimeMillis = System.currentTimeMillis();
            long issueTimeMillis = rbook.getIssueTimeMillis();
            long totaltime = (currentTimeMillis - issueTimeMillis) / 1000;

            if (totaltime >= 10) {
                int finePerSecond = 3;
                int overdueFine = (int) (finePerSecond * (totaltime - 10));

                if (overdueFine > 0) {
                    System.out.println("Overdue Fine on this book: Rs" + overdueFine);
                    fines += overdueFine;
                }
            }


            rbook.available++;

            BOOK.remove(rbook);
            System.out.println("Book '" + rbook.getT() + "' returned successfully.\n");

        }
        else {
            System.out.println("The book with ID " + brID + " is not borrowed by you.\n");

        }
    }
    public void finepay(Books bookToReturn,int fineq){
        for (Books bb : BOOK) {

            bookToReturn = bb;

            long currentTimeMillis = System.currentTimeMillis();
            long issueTimeMillis = bookToReturn.getIssueTimeMillis();
            long totaltime = (currentTimeMillis - issueTimeMillis) / 1000;

            if (totaltime >= 10) {
                int finePerSecond = 3;
                int overdueFine = (int) (finePerSecond * (totaltime - 10));

                if (overdueFine > 0) {
                    fineq =+overdueFine;

                }
            }
        }
    }

}
class Books{
    String title;
    int id;
    String author;
    int copies;
    int available;
    public int getID(){
        return id;
    }
    public String getT(){
        return title;
    }
    public String getA(){
        return author;
    }
    long issueTimeMillis;

    public void setIssueTimeMillis(long timeMillis) {
        this.issueTimeMillis = timeMillis;
    }

    public long getIssueTimeMillis() {
        return issueTimeMillis;
    }

}

public class library{
    public static void main(String[] args) {


        ArrayList<Members> membersarray = new ArrayList<>();
        ArrayList<Books> booksarray = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        System.out.println("Library Portal Initialized");
        int order=0;
        int order1=0;
        int order2=0;
        int idcount=1;
        while(order!=3){
            if(order!=1 || order!=2 ||order!=3){
                System.out.println("\nEnter your choice between 1- 3\n");
            }
            System.out.println("---------------------------------\r\n" +
                    "1. Enter as a librarian\r\n" +
                    "2. Enter as a member\r\n" +
                    "3. Exit\r\n"+
                    "---------------------------------\r\n"+
                    "choose option: ");
            order = scn.nextInt();
            order1=0;
            order2=0;
            if(order==1){

                while(order1!=7){

                    if(order1!=1 || order1!=2 ||order1!=3 ||order1!=4||order1!=5 ||order1!=6 ||order1!=7){
                        System.out.println("\nEnter your choice between 1- 7\n");
                    }
                    System.out.println("---------------------------------\r\n"+
                            "1. Register a member\r\n"+
                            "2. Remove a member\r\n"+
                            "3. Add a book\r\n"+
                            "4. Remove a book\r\n"+
                            "5. View all members along with their books and fines to be paid\r\n"+
                            "6. View all books\r\n"+
                            "7. Back\r\n"+
                            "---------------------------------\r\n"+
                            "choose option: ");
                    order1 = scn.nextInt();
                    if(order1==1){

                        System.out.println("enter name: ");
                        scn.nextLine();
                        String name=scn.nextLine();

                        int age;
                        while (true) {
                            System.out.println("Enter age: ");
                            if (scn.hasNextInt()) {
                                age = scn.nextInt();

                                if (age <= 0) {
                                    System.out.println("Please enter a positive age\n");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(" Please enter a valid age.\n");
                                scn.nextLine();
                            }
                        }

                        // scn.nextLine();
                        // System.out.println("enter phone number: ");
                        // int number=scn.nextInt();
                        // scn.nextLine();

                        long number;
                        while (true) {
                            System.out.println("Enter phone no.: ");
                            if (scn.hasNextLong()) {
                                number = scn.nextLong();

                                if (number/(1000000000) <= 0) {
                                    System.out.println("Please enter 10 digit number");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(" Please enter a valid number");
                                scn.nextLine();
                            }
                        }

                        int index0=-1;
                        for (int i = 0; i < membersarray.size(); i++) {
                            Object obj = membersarray.get(i);
                            if (obj instanceof Members) {
                                Members member = (Members) obj;
                                if (member.id == number) {
                                    index0 = i;
                                    break;
                                }
                            }
                        }

                        if(index0==-1){

                            Members person= new Members();
                            person.name=name;
                            person.age=age;
                            person.number=number;
                            person.id=number;
                            person.count=0;
                            person.fines = 0;
                            person.BOOK = new ArrayList<>();
                            membersarray.add(person);
                            System.out.println("\nMember Successfully Registered with <"+number+">!\n");

                        }
                        else System.out.println("\nmember already exists\n");


                    }

                    else if(order1==2){
                        // System.out.println("Enter member id: ");
                        // int number1 = scn.nextInt();
                        // System.out.println("hello there");
                        long number1;
                        while (true) {
                            
                           System.out.println("Enter phone no.: ");
                            if (scn.hasNextLong()) {
                                number1 = scn.nextLong();
                                // System.out.println("hello");
                                if (number1/(1000000000) <= 0) {
                                    System.out.println("Please enter 10 digit number\n");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println("else ka else");
                                System.out.println(" Please enter a valid number\n");
                                scn.nextLine();
                            } 
                        }

                        int index = -1;

                        for (int i = 0; i < membersarray.size(); i++) {
                            Object obj = membersarray.get(i);
                            if (obj instanceof Members) {
                                Members member = (Members) obj;
                                if (member.id == number1) {
                                    index = i;
                                    break;
                                }
                            }
                        }
                        if(index!=-1){
                            membersarray.remove(index);
                        }
                        else {
                            System.out.println("id not found");}

                    }

                    else if(order1==3){

                        System.out.println("enter book title: ");
                        scn.nextLine();
                        String name = scn.nextLine();

                        System.out.println("enter author: ");
                        String author = scn.nextLine();

                        int copies;
                        while (true) {
                            System.out.println("Enter count: ");
                            if (scn.hasNextInt()) {
                                copies = scn.nextInt();

                                if (copies <= 0) {
                                    System.out.println("enter valid copies amount\n");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(" Please enter a valid number of copies\n");
                                scn.nextLine();
                            }
                        }


                        int index0=-1;

                        if(index0==-1){

                            for(int i=idcount;i<idcount+copies;i++){
                                Books book= new Books();
                                book.title=name;
                                book.author=author;
                                book.copies=copies;
                                book.id=i;
                                book.available=1;
                                booksarray.add(book);
                                // System.out.println("\nbook added succesfully");
                            }
                            idcount=idcount+copies;
                            System.out.println("\nall copies of the book added succesfully\n");

                        }
                        else System.out.println("\nbook already exists\n");

                    }

                    else if(order1==4){

                        // System.out.println("Enter book id: ");
                        // int number2 = scn.nextInt();

                        int number2;
                        while (true) {
                            System.out.println("Enter id: ");
                            if (scn.hasNextInt()) {
                                number2 = scn.nextInt();

                                if (number2 <= 0) {
                                    System.out.println("Please enter a postive id\n");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(" Please enter a valid id.\n");
                                scn.nextLine();
                            }
                        }

                        int index = -1;

                        for (int i = 0; i < booksarray.size(); i++) {
                            Object obj = booksarray.get(i);
                            if (obj instanceof Books) {
                                Books book = (Books) obj;
                                if (book.id == number2) {
                                    index = i;
                                    break;
                                }
                            }
                        }
                        if(index!=-1){
                            booksarray.remove(index);
                        }
                        else {
                            System.out.println("id not found");}

                    }

                    else if (order1==5){
                        if(membersarray.size()==0){

                            System.out.println("No members found");

                        }
                        else{
                            for (int i = 0; i < membersarray.size(); i++) {
                                Object obj = membersarray.get(i);
                                if (obj instanceof Members) {

                                    Members member = (Members) obj;
                                    System.out.println("name: "+member.name);
                                    System.out.println("age: "+member.age);
                                    System.out.println("phone no.: "+member.number);
                                    // System.out.println("fines due: "+member.fines+"\n\n");
                                    Books bookToReturn = null;
                                    int fine=0;
                                    for (Books bb : member.BOOK) {

                                        bookToReturn = bb;

                                        long currentTimeMillis = System.currentTimeMillis();
                                        long issueTimeMillis = bookToReturn.getIssueTimeMillis();
                                        long totaltime = (currentTimeMillis - issueTimeMillis) / 1000;

                                        if (totaltime >= 10) {
                                            int finePerSecond = 3;
                                            int overdueFine = (int) (finePerSecond * (totaltime - 10));

                                            if (overdueFine > 0) {
                                                fine =+overdueFine;

                                            }
                                        }
                                    }
                                    if(fine!=0){
                                        System.out.println("Overdue Fine: Rs" + fine +"\n\n");}
                                    else {System.out.println("Overdue Fine: Rs" + member.fines +"\n\n");}

                                }

                            }
                        }

                    }

                    else if(order1==6){

                        if(booksarray.size()==0){

                            System.out.println("No book found");

                        }
                        else{

                            for (int i = 0; i < booksarray.size(); i++) {
                                Object obj = booksarray.get(i);
                                if (obj instanceof Books) {

                                    Books book = (Books) obj;
                                    System.out.println("id: "+book.id);
                                    System.out.println("title: "+book.title);
                                    System.out.println("author: "+book.author+"\n\n");
                                }

                            }
                        }

                    }

                }


            }

            int index2=-1;

            if(order==2){

                //STUDENT LOGIN ID CODE
                // System.out.println("enter id: ");
                // int id1 = scn.nextInt();

                long id1;
                while (true) {
                    System.out.println("Enter member id: ");
                    if (scn.hasNextLong()) {
                        id1 = scn.nextLong();

                        if (id1/(1000000000) <= 0) {
                            System.out.println("Please enter 10 digit number\n");
                        } else {
                            break;
                        }
                    } else {
                        scn.nextLine();
                        System.out.println(" Please enter a valid number\n");

                    }
                }
                System.out.println("enter name: ");
                scn.nextLine();
                String name = scn.nextLine();


                for (int i = 0; i < membersarray.size(); i++) {
                    Object obj = membersarray.get(i);
                    if (obj instanceof Members) {
                        Members member = (Members) obj;
                        if (member.id == id1) {
                            index2 = i;
                            break;
                        }
                    }
                }
                if(index2!=-1){
                    Object obj = membersarray.get(index2);
                    Members member = (Members) obj;
                    System.out.println("welcome "+name+" "+"member id "+id1);

                    while(order2!=6){
                        if(order2!=1 || order2!=2 ||order2!=3 ||order2!=4||order2!=5 ||order2!=6){
                            System.out.println("\nEnter your choice between 1- 6");
                        }
                        System.out.println(
                                "1.List Available Books\r\n"+
                                        "2.List My Books\r\n"+
                                        "3.Issue book\r\n"+
                                        "4.Return book\r\n"+
                                        "5.Pay Fine\r\n"+
                                        "6.Back\r\n"
                        );

                        System.out.println("choose option: ");
                        order2 = scn.nextInt();

                        if(order2==1){
                            if(booksarray.size()==0){

                                System.out.println("No book found");

                            }
                            else{

                                for (int i = 0; i < booksarray.size(); i++) {
                                    Object obj1 = booksarray.get(i);
                                    if (obj1 instanceof Books) {

                                        Books book = (Books) obj1;

                                        if(book.available>0){
                                            System.out.println("id: "+book.id);
                                            System.out.println("title: "+book.title);
                                            System.out.println("author: "+book.author+"\n\n");
                                        }
                                    }
                                }
                            }

                        }

                        else if(order2==2){
                            member.mybook();
                        }


                        else if (order2 == 3) {

                            if (booksarray.size() == 0) {
                                System.out.println("No book available");
                            } else {
                                // Check if the member has any fines
                                // Object obj = membersarray.get(index2);
                                // Members member = (Members) obj;

                                Books bookToReturn = null;
                                int fineq=0;
                                for (Books bb : member.BOOK) {

                                    bookToReturn = bb;

                                    long currentTimeMillis = System.currentTimeMillis();
                                    long issueTimeMillis = bookToReturn.getIssueTimeMillis();
                                    long totaltime = (currentTimeMillis - issueTimeMillis) / 1000;

                                    if (totaltime >= 10) {
                                        int finePerSecond = 3;
                                        int overdueFine = (int) (finePerSecond * (totaltime - 10));

                                        if (overdueFine > 0) {
                                            fineq =+overdueFine;

                                        }
                                    }
                                }


                                if (member.fines > 0) {
                                    System.out.println("Your fines are due to pay before issuing a new book\n");
                                } else if (member.BOOK.size() >= 2) {
                                    System.out.println("No more than 2 books can be issued at a time\n");

                                }
                                else if (fineq>0){
                                    System.out.println("Your fines are due of your previos book before issuing a new book\n");
                                }
                                else {

                                    // System.out.println("Enter ID of the book you want to issue: ");
                                    // int sbookid = scn.nextInt();

                                    int sbookid;
                                    while (true) {
                                        System.out.println("Enter book id to issue: ");
                                        if (scn.hasNextInt()) {
                                            sbookid = scn.nextInt();

                                            if (sbookid <= 0) {
                                                System.out.println("Please enter a postive id");
                                            } else {
                                                break;
                                            }
                                        } else {
                                            System.out.println(" Please enter a valid id.");
                                            scn.nextLine();
                                        }
                                    }


                                    Books sbook = null;
                                    for (Books book : booksarray) {
                                        if (book.id == sbookid && book.available > 0) {
                                            sbook = book;
                                            break;
                                        }
                                    }

                                    if (sbook != null) {

                                        sbook.setIssueTimeMillis(System.currentTimeMillis());


                                        member.BOOK.add(sbook);
                                        sbook.available--;

                                        System.out.println("Book '" + sbook.title + "' issued successfully.\n");
                                    } else {
                                        System.out.println("The selected book is not available for issue or does not exist.\n");
                                    }
                                }
                            }


                        }
                        else if(order2==4){


                            // Object obj = membersarray.get(index2);
                            // Members member = (Members) obj;

                            if (member.BOOK.size() == 0) {
                                System.out.println("You have not borrowed any books.\n");
                            } else {



                                // System.out.println("Enter the ID of the book you want to return: ");
                                // int brID = scn.nextInt();

                                int brID;
                                while (true) {
                                    System.out.println("Enter the ID of the book you want to return: ");
                                    if (scn.hasNextInt()) {
                                        brID = scn.nextInt();

                                        if (brID <= 0) {
                                            System.out.println("Please enter a postive id\n");
                                        } else {
                                            break;
                                        }
                                    } else {
                                        System.out.println(" Please enter a valid id.\n");
                                        scn.nextLine();
                                    }
                                }

                                Books rbook = null;
                                for (Books bb : member.BOOK) {
                                    if (bb.getID() == brID) {
                                        rbook = bb;
                                        break;
                                    }
                                }

                                if (rbook != null) {
                                    long currentTimeMillis = System.currentTimeMillis();
                                    long issueTimeMillis = rbook.getIssueTimeMillis();
                                    long totaltime = (currentTimeMillis - issueTimeMillis) / 1000;

                                    if (totaltime >= 10) {
                                        int finePerSecond = 3;
                                        int overdueFine = (int) (finePerSecond * (totaltime - 10));

                                        if (overdueFine > 0) {
                                            System.out.println("Overdue Fine on this book: Rs" + overdueFine);
                                            member.fines += overdueFine;
                                        }
                                    }


                                    rbook.available++;

                                    member.BOOK.remove(rbook);

                                    System.out.println("Book '" + rbook.getT() + "' returned successfully.\n");
                                } else {
                                    System.out.println("The book with ID " + brID + " is not borrowed by you.\n");

                                }
                            }

                        }

                        else if(order2==5){


                            int fines = member.fines;

                            if (fines == 0) {
                                System.out.println("You don't have any fines to pay.\n");
                            } else {
                                System.out.println("Your fines was of Rs" + fines);
                                System.out.println("You have successfully paid your fines\n");
                            }
                            member.fines=0;
                        }

                        //   else {

                        //     System.out.println("id does not exist\n");

                        //   }

                    }

                }
                else {

                    System.out.println("id does not exist\n");

                }

            }
        }
        scn.close();
        System.out.println("Thank for using the portal\n");
    }
}