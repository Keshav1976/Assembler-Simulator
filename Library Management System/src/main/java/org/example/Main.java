package org.example;
import java.util.*;

class Book {
    private long bt;
    private int bID;
    private String title;
    private String author;
    private int TC;
    private long rt;
    int AVBooks;

    public Book(int bID, String title, String author, int TC) {
        this.bID = bID;
        this.title = title;
        this.author = author;
        this.TC = TC;
        this.AVBooks = TC;
    }

    public Book(long bt, long rt) {
        this.bt = bt;
        this.rt = rt;

    }

    public long getrt() {
        return rt;
    }

    public void setrt(long rt) {
        this.rt = rt;
    }

    public int getbID() {
        return bID;
    }

    public void setbt(long h) {
        this.bt = h;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvb() {
        return AVBooks;
    }

    public long getbt() {
        return bt;
    }

    public void DEC1() {
        if (AVBooks > 0) {
            AVBooks--;
        }
    }
}

class Member {
    private int fi;
    private int Id;
    private String name;
    private int age;
    private long pn;
    private List<Book> BB;

    public Member(String name, int age, long pn, int ID) {
        this.age = age;
        this.name = name;
        this.pn = pn;
        this.BB = new ArrayList<>();
        this.Id = ID;
    }

    public Member(int fi) {
        this.fi = fi;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public long getpn() {
        return pn;
    }

    public int retf() {
        return fi;
    }

    public int borrowBook(Book book) {
        for (Book b : BB) {
            updatef(b);
        }
        int d = 0;
        if (fi == 0) {
            if (BB.size() < 2 && book.getAvb() > 0) {
                BB.add(book);
                System.out.println("----------------");
                System.out.println("Book issued successfully!!");
                book.setbt(System.currentTimeMillis());
                d = 1;
            } else if (BB.size() == 2) {
                System.out.println("----------------");
                System.out.println("You have already Borrowed 2 Books.!!");
            } else if (book.getAvb() == 0) {
                System.out.println("----------------");
                System.out.println("Book with this Id is not available at the moment!!");
            }
        } else {
            System.out.println("Firstly by pressing 5 pay the fine charged on you of Rs." + fi);
        }
        return d;
    }

    public int listbook() {
        int yy = 0;
        if (BB.size() == 0) {
            System.out.println("No books are borrowed by you!!");
            yy = 0;
        } else {
            System.out.println("----------------");
            System.out.println("Books Borrowed by you are: ");
            for (Book b : BB) {
                yy = 1;
                System.out.println("Book Id: " + b.getbID());
                System.out.println("Name: " + b.getTitle());
                System.out.println("Author: " + b.getAuthor());
            }
        }
        return yy;
    }

    public int retb(Book b) {
        int f = 0;
        for (Book g : BB) {
            if (g.getbID() == b.getbID()) {
                long returnTime = System.currentTimeMillis();
                long midt = (returnTime - b.getbt()) / 1000;

                if (midt > 10) {
                    int SL = (int) (midt - 10);
                    int TF = SL * 3;
                    fi = +TF;
                    System.out.printf(
                            "Book Id %d returned Successfully. %d Rupees has been charged for delay of %d days: Press 5 to pay the fine!!",
                            b.getbID(), fi, SL);
                    b.AVBooks++;
                    f = 1;
                    BB.remove(b);
                    break;
                } else if (midt < 10) {
                    System.out.println("Book returned on time.!!");
                    BB.remove(b);
                    b.AVBooks++;
                    f = 1;
                    break;
                }
            }
        }
        return f;
    }

    public int updatef(Book c) {
        if (c.getbt() == 0) {
            fi = 0;
        } else {
            c.setrt(System.currentTimeMillis());
            long ET = (c.getrt() - c.getbt()) / 1000;
            if (ET > 10) {
                int SL = (int) (ET - 10);
                int CP = SL * 3;
                fi = CP;
            }
        }
        return fi;
    }

    public void pf() {
        if (fi == 0) {
            System.out.println("No fine till now");
        } else {
            System.out.print("You have total fine of Rs. " + fi);
            System.out.println("\n");
            System.out.println("It has been paid successfully!!");
            fi = 0;
        }
    }

    public int tfine() {
        int zz = 0;
        for (Book b : BB) {
            zz = zz + updatef(b);
        }
        return zz;
    }

    public int checks(int d) {
        int uuu = 0;
        for (Book bu : BB) {
            if (d == bu.getbID()) {
                uuu = 1;
            }
        }
        return uuu;
    }

    public Book rb(int e) {
        Book j = null;
        for (Book H : BB) {
            if (H.getbID() == e) {
                j = H;
            }
        }
        return j;
    }
}

class Library {
    static int zz = 0;
    static int q = 0;
    static int a;
    static int c;
    static int d;
    static int h;
    List<Book> BOOK;
    List<Member> MEM;

    public Library() {
        this.BOOK = new ArrayList<>();
        this.MEM = new ArrayList<>();
    }

    public void Addbook(String name, String author, int c) {
        for (int i = 0; i < c; i++) {
            zz = zz + 1;
            Book book = new Book(zz, name, author, c);
            BOOK.add(book);
        }
    }

    public int checker(int g) {
        for (Book bb : BOOK) {
            if (g == bb.getbID()) {
                c = 1;
                break;
            } else {
                c = 0;
            }
        }
        return c;
    }

    public int enterasmem(String na, long n) {
        for (Member memm : MEM) {
            if (memm.getpn() == n && memm.getName().equals(na)) {
                System.out.println("----------------");
                System.out.printf("welcome %s . Member Id: %d", memm.getName(), memm.getId());
                a = 1;
                break;
            } else {
                a = 0;
            }
        }
        return a;
    }

    public int removebook(int a) {
        int h = 0;
        if (BOOK.size() == 0) {
            System.out.println("No Book Present!");
        } else {
            for (Book bo : BOOK) {
                if (bo.getbID() == a) {
                    BOOK.remove(bo);
                    bo.DEC1();
                    h = 1;
                    break;
                }
            }
        }
        return h;
    }

    public void removemem(int a) {
        boolean rm = MEM.removeIf(member -> member.getId() == a);
        if (rm) {
            System.out.println("----------------");
            System.out.printf("Member removed successfully with Id %d !! ", a);
            System.out.println("\n");
        } else {
            System.out.println("----------------");
            System.out.println("Member doesn't exist!!");
            System.out.println("----------------");
        }
    }

    public void listbook() {
        System.out.println("List of Books");
        System.out.println("----------------");
        for (Book bo : BOOK) {
            System.out.println("Book Id: " + bo.getbID());
            System.out.println("Name: " + bo.getTitle());
            System.out.println("Author: " + bo.getAuthor());
            System.out.println("----------------");
        }
    }

    public int si() {
        return BOOK.size();
    }

    public Book retbo(int f) {
        Book i = null;
        for (Book j : BOOK) {
            if (j.getbID() == f) {
                i = j;
                break;
            }
        }
        return i;
    }

    public void Addreturnedbook(int id, String name, String author) {
        Book b = new Book(id, name, author, 1);
        BOOK.add(b);
    }

    public Member retmem(String s, Long d) {
        Member mmm = null;
        for (Member mo : MEM) {
            if (mo.getName().equals(s) && mo.getpn() == d) {
                mmm = mo;
                break;
            }
        }
        return mmm;
    }

    public void listmem() {
        if (MEM.size() == 0) {
            System.out.println("No Member registered.");
        }

        for (Member me : MEM) {
            System.out.println("----------------");
            System.out.println("Name: " + me.getName());
            System.out.println("Id: " + me.getId());
            System.out.println("Phone Number: " + me.getpn());
            me.listbook();
            System.out.println("Fine: " + me.tfine());
        }
    }

    public int chechp(long f) {
        int u = 0;
        for (Member mmmm : MEM) {
            if (f == mmmm.getpn()) {
                u = 1;
                System.out.println("----------------");
                System.out.println("Member with this phone number is already registered.!");
                break;
            }
        }
        return u;
    }

    public void registermem(String d, int s, long f) {
        q = q + 1;
        Member m = new Member(d, s, f, q);
        MEM.add(m);
        System.out.println("Member registered succesfully with member ID " + q + "!!");
    }

    public int Getint(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input, Please Try Again!!");
            }
        }
    }

    public long Getlong(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please Try Again!!");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        System.out.println("Library Portal initialized....");
        while (true) {
            System.out.println("----------------");
            System.out.println("1.Enter as Librarian");
            System.out.println("2.Enter as member");
            System.out.println("3.Exit");
            System.out.println("----------------");
            String a = sc.next();

            if (a.equals("1")) {
                while (true) {
                    System.out.println("----------------");
                    System.out.println("1. Register a member\r\n" + //
                            "2. Remove a member\r\n" + //
                            "3. Add a book\r\n" + //
                            "4. Remove a book\r\n" + //
                            "5. View all members along with their books and fines to be paid\r\n" + //
                            "6. View all books\r\n" + //
                            "7. Back");
                    System.out.println("----------------");
                    String b = sc.next();
                    if (b.equals("1")) {
                        System.out.print("Enter name : ");
                        sc.nextLine();
                        String c = sc.nextLine();
                        int d = library.Getint(sc, "Enter age: ");
                        long f = library.Getlong(sc, "Enter phone Number: ");
                        String r = Long.toString(f);
                        int l = r.length();
                        int na = library.chechp(f);
                        if (l > 10 || l < 9) {
                            System.out.println("----------------");
                            System.out.println("Invalid phone number!");
                        } else if (na == 1) {
                            continue;
                        } else if (d < 0 || d == 0 || d > 130) {
                            System.out.println("----------------");
                            System.out.println("Invalid age!!");
                        } else {
                            System.out.println("----------------");
                            library.registermem(c, d, f);
                        }

                    } else if (b.equals("3")) {
                        System.out.print("Enter Name of the book to be added: ");
                        sc.nextLine();
                        String h = sc.nextLine();
                        System.out.print("Enter author of the book: ");
                        String i = sc.nextLine();
                        int j = library.Getint(sc, "Enter total no. of copies: ");
                        library.Addbook(h, i, j);
                        System.out.println("----------------");
                        System.out.println("Book Added successfully!!");
                    } else if (b.equals("7")) {
                        break;
                    } else if (b.equals("5")) {
                        library.listmem();
                    } else if (b.equals("2")) {
                        sc.nextLine();
                        int g = library.Getint(sc, "Enter Id of member to be removed: ");
                        library.removemem(g);
                    } else if (b.equals("6")) {
                        int ss = library.si();
                        if (ss == 0) {
                            System.out.println("----------------");
                            System.out.println("No books are present!!");
                        } else {
                            library.listbook();
                        }
                    } else if (b.equals("4")) {
                        sc.nextLine();
                        int j = library.Getint(sc, "Enter Id of the book to be removed: ");
                        int u = library.removebook(j);
                        if (u == 1) {
                            System.out.println("----------------");
                            System.out.printf("Book removed successfully with Id %d !", j);
                            System.out.println("\n");
                        } else {
                            System.out.println("Book with this Id is not present");
                        }
                    } else {
                        System.out.println("Invalid Input , pls try again!!");
                    }
                }
            } else if (a.equals("3")) {
                System.out.println("----------------");
                System.out.println("Thanks for visiting!");
                System.out.println("----------------");
                break;
            } else if (a.equals("2")) {
                System.out.print("Enter your name for access : ");
                sc.nextLine();
                String k = sc.nextLine();
                long l = library.Getlong(sc, "Enter your Phone. no.for access : ");
                int z = library.enterasmem(k, l);
                if (z == 1) {
                    while (true) {
                        System.out.println("\n");
                        System.out.println("----------------");
                        System.out.println("1. List Available Books\r\n" + //
                                "2. List My Books\r\n" + //
                                "3. Issue book\r\n" + //
                                "4. Return book\r\n" + //
                                "5. Pay Fine\r\n" + //
                                "6. Back");
                        System.out.println("----------------");
                        String x = sc.next();
                        if (x.equals("6")) {
                            break;
                        } else if (x.equals("1")) {
                            library.listbook();
                        } else if (x.equals("3")) {
                            int tt = library.si();
                            if (tt == 0) {
                                System.out.println("No books are present!!");
                            } else {
                                library.listbook();
                                sc.nextLine();
                                int m = library.Getint(sc, "Enter the book Id for issue: ");
                                int y = library.checker(m);
                                if (y == 1) {
                                    Book e = library.retbo(m);
                                    Member d = library.retmem(k, l);
                                    int jj = d.borrowBook(e);
                                    if (jj == 1) {
                                        library.removebook(m);
                                    }
                                } else if (y == 0) {
                                    System.out.println("Sorry Book with this Id is not present!!");
                                }
                            }

                        } else if (x.equals("2")) {
                            Member mee = library.retmem(k, l);
                            mee.listbook();
                        } else if (x.equals("4")) {
                            Member h = library.retmem(k, l);
                            int kk = h.listbook();
                            if (kk == 1) {
                                sc.nextLine();
                                int y = library.Getint(sc, "Enter the book id you want to return: ");
                                int yyy = h.checks(y);
                                if (yyy == 1) {
                                    Book r = h.rb(y);
                                    int i = h.retb(r);
                                    if (i == 1) {
                                        library.Addreturnedbook(y, r.getTitle(), r.getAuthor());
                                    }
                                } else {
                                    System.out.println("----------------");
                                    System.out.println("You have not borrowed book for this ID!!");
                                }
                            }
                        } else if (x.equals("5")) {
                            Member uu = library.retmem(k, l);
                            uu.pf();
                        } else {
                            System.out.println("----------------");
                            System.out.println("Invalid input , please Try Again!!");
                        }
                    }
                } else {
                    System.out.println("----------------");
                    System.out.printf("Member with name: %s and phone number %d doesn't exists!!", k, l);
                    System.out.println("\n");
                }
            } else {
                System.out.println("----------------");
                System.out.println("Invalid input , please Try Again!!");
            }
        }
        sc.close();
    }
}