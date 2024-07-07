package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

interface VisitorInterface {
    int getMembership();
    void setMembership(int membership);
    void setBalance(double balance);
    double getBalance();
    String getEmail();
    String getPassword();
    boolean login(String email, String password);
    void exploreZoo();
    void buyMembership(String level);
    void buyTickets(Admin admin, int a, int n,Discount d);
    void visitAnimal(Admin admin);
    void visitAttraction(Admin admin);
    void leaveFeedback(String feedback);
}

class Visitor implements VisitorInterface{
    private String name;
    private int age;
    private String phoneNumber;
    private double balance;
    private String email;
    private String password;

    private int membership=0;
    private List<Integer>tickets=new ArrayList<Integer>();


    public int getMembership() {
        return membership;
    }
    public void setMembership(int membership) {
        this.membership = membership;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    Scanner scanner = new Scanner(System.in);


    public Visitor(String name,int age ,String phoneNumber,double balance,String email,String password){
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
        this.balance=balance;
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(String email, String password) {


        return true;
    }

    public void exploreZoo() {

    }

    public void buyMembership(String level) {

    }

    public void buyTickets(Admin admin,int a,int n,Discount d) {


//        System.out.println("Buy Tickets:\n" +
//                "Select an Attraction to Buy a Ticket:\n");
//        admin.vA();
//        System.out.println(admin.length()+1+"."+"exit");
//        System.out.println("Enter your choice: ");
//        int a=scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Enter number of tickets to buy: ");
//        int n=scanner.nextInt();
//        scanner.nextLine();
        admin.getPopular().set(a,admin.getPopular().get(a)+ n);
        admin.setCount(admin.getCount()+n);
        double price=admin.getA(a).getPrice()*n;
        if(n>3){
            price=price-(price*d.getA_3()/100);
        } else if (n>2) {
            price=price-(price*d.getA_2()/100);
        }
        if(this.balance>=price) {
            for (int i = 0; i < n; i++) {
//            integer in =new integer();
//            in.setI(a-1);
                tickets.add(a);

            }
            this.balance = this.balance - price;
            admin.setRevenue(admin.getRevenue() + admin.getA(a).getPrice() * n);
            System.out.println("Available balance: " + this.balance);
        }
        else{
            System.out.println("insufficient balance\n");
        }

    }


    public void visitAnimal(Admin admin) {

        int q=0;
        int n=1;
        while(q!=3){
            n=1;
            if(this.getMembership()==0){
                System.out.println("buy membership first\n");
                break;
            }
            for (Animal aa: admin.getAnimals()){
                System.out.println(n+"."+aa.getName()+"\n");
                n++;
            }
            System.out.println(n+"."+"exit");

            System.out.println("enter name of animal from the list: ");
            String name=scanner.nextLine();
            if(name.equals("exit")){
                break;
            }
            System.out.println("1. feed animal\n2. read description\n3. exit");
            System.out.println("enter choice: ");
            q=scanner.nextInt();
            scanner.nextLine();
            Animal av = null;
            int s=0;
            for (Animal aa: admin.getAnimals()){
                if(aa.getName().equals(name.toLowerCase())){
                    av=aa;
                    s=1;
                }
            }
            if(q==1&& s==1){
                System.out.println(av.getSound());
            } else if (q==2 && s==1) {
                System.out.println(av.getDescription());
            } else if (s==0) {
                System.out.println("animal not found");

            }

        }

    }

    public void visitAttraction(Admin admin) {
        // Logic for visiting an attraction
        System.out.println("Select an Attraction to Visit:\n");
        admin.vA();
        System.out.println(admin.length()+1+"."+"exit\n");
        System.out.println("Enter your choice: ");
        int c=scanner.nextInt();
        scanner.nextLine();
        if(this.membership==2){
            System.out.println("1 Ticket Used.\n" +
                    "Thank you for visiting "+admin.getA(c).getName()+". Hope you enjoyed the attraction.\n");
        } else if (this.membership==1) {
            int t=0;
            for(Integer tic: tickets){
                if(c==tic){
                    t=1;
                    tickets.remove(c);
                    System.out.println("1 Ticket Used.\n" +
                            "Thank you for visiting "+admin.getA(c).getName()+". Hope you enjoyed the attraction.\n");
                }
            }
            if (t==0){
                System.out.println("Ticket not available. Basic Members need to buy separate tickets for the attractions.\n");
            }
        }
    }

    public void leaveFeedback(String feedback) {

    }
}

interface OwnersInterface {
    String login(String username, String password);
}

class Owners implements OwnersInterface{
    private String username;
    private String password;
    public Owners(String user, String pass){

        this.username=user;
        this.password=pass;
    }

    public String login(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)){
            return "true";
        }
        else {
            return "false";// Logic for admin login
        }
    }
}
interface AnimalManagementInterface {
    void addAnimal(Animal a);
    void amammal(Mammal a);
    void areptile(Reptile r);
    void aamphibian(Amphibian a);
    void manageAnimals(Admin admin);
}
interface AttractionManagementInterface {
    void addAttractions(int AID);
    void viewAttractions();
    void vA();
    void vD(int index);
    void modifyAttraction(int AID);
    void removeAttraction(int AID);
    void status_scheduleEvents(int id, int s);
    void price_scheduleEvents(int id, double p);
}
interface StatisticsInterface {
    void viewVisitorStats();
    void addFeedback(String s);
    void viewFeedback();
}


class Admin implements AnimalManagementInterface, AttractionManagementInterface, StatisticsInterface {


    private List<Discount> discounts=new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();
    private List<Mammal> mammals = new ArrayList<>();
    private List<Amphibian> amphibians = new ArrayList<>();
    private List<Reptile> reptiles = new ArrayList<>();
    private List<Attraction> attractions=new ArrayList<>();
    // Constructor and getter
    private List<String>feedback=new ArrayList<String>();
    private List<Integer>popular=new ArrayList<Integer>();


    public List<Integer> getPopular() {
        return popular;
    }

    private int count=0;
    private double revenue=0;
    public int length(){
        return attractions.size();
    }


    public List<Animal> getAnimals(){
        return animals;
    }
    public double getRevenue() {
        return revenue;
    }
    public int mostp(){
        int maxIndex = 0;
        int maxValue = popular.get(0);

        for (int i = 1; i < popular.size(); i++) {
            if (popular.get(i) > maxValue) {
                maxValue = popular.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Attraction getA(int id){

        return attractions.get(id);
    }


    public void vAnimals(){

        int a=1;
        for (Animal an : animals){

            System.out.println(a+". " + an.getName());
            a++;
        }
    }
    Scanner scanner = new Scanner(System.in);
    public void addAttractions(int AID) {

        // Logic for managing attractions
        System.out.println("enter name of attraction: ");
        String attraction = scanner.nextLine();

        System.out.println("enter description: ");
        String des = scanner.nextLine();

        System.out.println("enter price: ");
        double p = scanner.nextDouble();
        scanner.nextLine();

        Attraction a= new Attraction(AID,attraction,des,p);
        attractions.add(a);
        System.out.println("Attraction added successfully");
    }


    public void viewAttractions(){

        for (Attraction attraction : attractions) {
            System.out.println("ID:"+attraction.getId());
            System.out.println("Name of Attraction: " + attraction.getName());
            System.out.println("whats in the attraction: " + attraction.getDescription());
            System.out.println("Price of ticket: rs" + attraction.getPrice());
            System.out.println();
        }
    }
    public void vA(){
        int a=1;
        for (Attraction attraction : attractions){

            System.out.println(attraction.getId()+". " + attraction.getName()+" rs"+ attraction.getPrice());
            a++;
        }
        if(attractions == null){
            System.out.println("no attraction present");
        }
    }
    public void vD(int index){
        System.out.println("description of Attraction:\n " + attractions.get(index-1).getDescription());
    }

    public void modifyAttraction(int AID){
        if(attractions.size()>AID){
        System.out.println("enter new descripition: ");
        String des= scanner.nextLine();
        System.out.println("enter new price: ");
        double p=scanner.nextInt();
        attractions.get(AID).setDescription(des);
        attractions.get(AID).setPrice(p);
        System.out.println("Details of attraction modifies successfully");}
        else{
            System.out.println("id not present\n");
        }

    }
    public void removeAttraction(int AID){
        if(attractions.size()>AID){
        attractions.remove(AID);
        System.out.println("attraction removed");}
        else{
            System.out.println("id not found\n");
        }

    }

    public void addAnimal(Animal a){
        animals.add(a);

    }
    public void amammal(Mammal a){
        mammals.add(a);

    }
    public void areptile(Reptile r){
        reptiles.add(r);
    }
    public void aamphibian(Amphibian a){
        amphibians.add(a);
    }



    public void manageAnimals(Admin admin) {


        int c=0;
        int qw=1;
        for(Animal aaa :animals){
            System.out.println(qw+"."+aaa.getName());
            qw++;
        }
        while(c!=4){
        System.out.println("Manage Animals:\n" +
                "1. Add Animal\n" +
                "2. Update Animal Details\n" +
                "3. Remove Animal\n" +
                "4. Exit\n");
        System.out.println("Enter your choice: ");
        c=scanner.nextInt();
        scanner.nextLine();
        if (c==1){
            System.out.println("enter name: ");
            String a=scanner.nextLine();
            a=a.toLowerCase();
            System.out.println("enter type: ");
            String b=scanner.nextLine();
            System.out.println("Enter voice: ");
            String v=scanner.nextLine();
            System.out.println("Enter description: ");
            String d=scanner.nextLine();
            if(b.toLowerCase().equals("mammal")){
                Mammal m=new Mammal(a,v,d);
                animals.add(m);
                admin.amammal(m);
            } else if (b.toLowerCase().equals("amphibian")) {
                Amphibian m=new Amphibian(a,v,d);
                animals.add(m);
                admin.aamphibian(m);
            } else if (b.toLowerCase().equals("reptile")) {
                Reptile m= new Reptile(a,v,d);
                animals.add(m);
                admin.areptile(m);
            }
            System.out.println("animal added successfully\n");
        } else if (c==2) {

            System.out.println("enter name of animal: ");
            String name=scanner.nextLine();
            int s=0;
            for(Animal a: animals){
                if(a.getName().equals(name.toLowerCase())){
                    System.out.println("enter new description: ");
                    String s1=scanner.nextLine();
                    a.setDescription(s1);
                    System.out.println("description changed successfully\n");
                    s=1;
                }
            }
            if(s==0){
                System.out.println("animal with that name not found");
            }
        } else if (c==3) {
            System.out.println("enter name of animal to remove: ");
            String an=scanner.nextLine();
            int s=0;
            for(Animal a: animals){
                if(a.getName().equals(an.toLowerCase())){
//                    System.out.println("enter new description: ");
//                    String s1=scanner.nextLine();
//                    a.setDescription(s1);
//                    System.out.println("description changed successfully");
                    if(a.getType().equals("mammal") && mammals.size()>2){
                        animals.remove(a);
                        mammals.remove(0);
                        System.out.println("Animal removed from zoo");
                    } else if (a.getType().equals("amphibian") && amphibians.size()>2) {
                        animals.remove(a);
                        amphibians.remove(0);
                        System.out.println("Animal removed from zoo");
                    } else if (a.getType().equals("reptile") && reptiles.size()>2) {
                        animals.remove(a);
                        reptiles.remove(0);
                        System.out.println("Animal removed from zoo");
                    }
                    else{
                        System.out.println("minimum 2 animals should be there of ech type so it can not be removed\n");
                    }
                    s=1;
                }
            }
            if(s==0){
                System.out.println("animal with that name not found\n");
            }
        }}
    }

    public void status_scheduleEvents(int id,int s) {

        //status
        //price
        //count of visitors
        if(s==1){
        attractions.get(id).setStatus(1);}
        else {
            attractions.get(id).setStatus(0);
        }


    }
    public void price_scheduleEvents(int id, double p){


        attractions.get(id).setPrice(p);


    }

    public void viewVisitorStats() {

        if(this.count==0){
            System.out.println("no visitors yet\n");
        }
        else {
            System.out.println("Visitor Statistics:\n" +
                    "- Total Visitors: " + this.count +
                    "- Total Revenue: rs" + this.revenue + "\n" + "most popular attraction: " + getA(mostp()).getName() + "\n");
        }
    }
    public void addFeedback(String s){

        feedback.add(s);
    }
    public void viewFeedback() {
        if(feedback!=null){
        for(String s : feedback ){
            System.out.println(s+"\n");
        }}
        else {
            System.out.println("no feedback present\n");
        }
    }
}


class integer {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}


class Attraction {
    private int id;
    private String name;
    private String description;
    private double price;

    private int status=0;

    private int count=0;

    public Attraction(int id, String name, String des,double p){
        this.id=id;
        this.name=name;
        this.description=des;
        this.price=p;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int a){
        this.status=a;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}



class Animal {
    private String name;
    private String type;
    private String description;


    // Constructor
    public Animal(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description=description;
    }


    public String getName() {
        return name;
    }
    protected String sound;
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSound() {
        return sound;
    }
}

class Mammal extends Animal {


    public Mammal(String name,String sound,String des) {
        super(name, "Mammal",des);
        this.sound = sound;
    }



}

class Amphibian extends Animal {
    private String sound;

    public Amphibian(String name,String sound,String des) {
        super(name, "Amphibian",des);
        this.sound=sound;
    }

}

class Reptile extends Animal {
    private String sound;

    public Reptile(String name,String sound,String des) {
        super(name, "Reptile",des);
        this.sound=sound;
    }


}

class Discount{

    private double minor=0;
    private int m_code= 1342;
    private int m_status=0;
    private double senior=0;
    private int s_code=1332;
    private int s_status=0;

    private int a_2=0;
    private int s_a_2=1;
    private int a_3=0;
    private int s_a_3=1;

    public int getM_code() {
        return m_code;
    }

    public int getS_code() {
        return s_code;
    }

    public int getA_2() {
        return a_2;
    }

    public int getA_3() {
        return a_3;
    }

    public void setS_a_2(int s_a_2) {
        this.s_a_2 = s_a_2;
    }

    public void setS_a_3(int s_a_3) {
        this.s_a_3 = s_a_3;
    }

    public double getMinor() {
        return minor;
    }

    public double getSenior() {
        return senior;
    }

    public void setMinor(double minor) {
        this.minor = minor;
    }

    public void setSenior(double senior) {
        this.senior = senior;
    }

    public void setM_status(int m_status) {
        this.m_status = m_status;
    }

    public void setS_status(int s_status) {
        this.s_status = s_status;
    }

    public int getM_status() {
        return m_status;
    }

    public int getS_status() {
        return s_status;
    }
}


public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        int AID=0;
        int choose=0;
        int choose1=0;
        int choose2=0;
        int choiceA=0;
        int choice1=0;
        int choose4=0;
        Mammal elephant=new Mammal("rajju","aww","with trunk and huge body");
        Mammal giraffe= new Mammal("skindy","hehe","long neck");
        admin.amammal(elephant);
        admin.amammal(giraffe);
        Amphibian toad= new Amphibian("rodd","uhuh","green in colour");
        Amphibian crocodile=new Amphibian("ludd","khakha","sharp teeth");
        admin.aamphibian(toad);
        admin.aamphibian(crocodile);
        Reptile turtle=new Reptile("lappy","eww","slow in motion");
        Reptile snake= new Reptile("ross","sisis","crawls, slimy");
        admin.areptile(turtle);
        admin.areptile(snake);
        admin.addAnimal(elephant);
        admin.addAnimal(giraffe);
        admin.addAnimal(toad);
        admin.addAnimal(crocodile);
        admin.addAnimal(turtle);
        admin.addAnimal(snake);

        for(int i=0;i<100;i++){
        admin.getPopular().add(0);
        }

        Discount d= new Discount();
        Scanner scanner = new Scanner(System.in);
        List<Visitor> visit=new ArrayList<>();
        System.out.println("Welcome to Zootopia");

        Owners ad= new Owners("admin","admin123");

        while (true) {

        System.out.println("1. Enter as Admin");
        System.out.println("2. Enter as a Visitor");
        System.out.println("3. View Special Deals");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            // Admin login
            // Implement admin login logic here


            //LOGIN DETAIL CHECK
            while(true){

                System.out.println("enter username: ");
                String user = scanner.nextLine();

                System.out.println("enter password: ");
                String pass = scanner.nextLine();



            if (ad.login(user,pass).equals("true")){
                System.out.println("logged in as admin");
                break;
             }

            else {System.out.println("Either username or password is wrong.Re-enter details.");}

            }
            choice1=0;
            while(choice1!=8) {
                System.out.println("1. Manage Attractions\n" +
                        "2. Manage Animals\n" +
                        "3. Schedule Events\n" +
                        "4. Set Discounts\n" +
                        "5. Set Special Deal\n" +
                        "6. View Visitor Stats\n" +
                        "7. View Feedback\n" +
                        "8. Exit\n");

                System.out.println("Enter your choice: ");
                choice1 = scanner.nextInt();


                if (choice1 == 1) {
                    choiceA=0;
                    while (choiceA != 5) {
                        System.out.println("Manage Attractions:\n" +
                                "1. Add Attraction\n" +
                                "2. View Attractions\n" +
                                "3. Modify Attraction\n" +
                                "4. Remove Attraction\n" +
                                "5. Exit\n");

                        System.out.println("Enter your choice: ");
                        choiceA = scanner.nextInt();
                        scanner.nextLine();



                        if (choiceA == 1) {

                            admin.addAttractions(AID);
                            AID++;

                        } else if (choiceA == 2) {

                            admin.viewAttractions();

                        } else if (choiceA == 3) {
                            System.out.println("enter attraction id: ");
                            int id = scanner.nextInt();
                            admin.modifyAttraction(id);
                        } else if (choiceA == 4) {
                            System.out.println("enter attraction id: ");
                            int id = scanner.nextInt();
                            admin.removeAttraction(id);
                        }
                    }//loop ending choiceA
                } else if (choice1==2) {


                    admin.manageAnimals(admin);



                } else if (choice1==3) {

                    System.out.println("1.set whether attraction open/closed\n");
                    System.out.println("2.set attraction price\n");
                    int c1= scanner.nextInt();
                    admin.viewAttractions();
                    if(c1==1){

                        System.out.println("enter attraction id: ");
                        int id1=scanner.nextInt();
                        System.out.println("1.open\n2.close");
                        int op=scanner.nextInt();
                        if(op==1){
                            admin.status_scheduleEvents(id1,1);
                        }
                        else{
                            admin.status_scheduleEvents(id1,0);

                        }
                    }
                    else{
                        System.out.println("enter attraction id: ");
                        int id0=scanner.nextInt();
                        System.out.println("enter new ticket price: ");
                        double pr=scanner.nextDouble();
                        admin.price_scheduleEvents(id0, pr );


                    }



                }
                else if (choice1==4){

                    System.out.println("1.add discount\n2.remove discount\n3.modify discount\n(1 2 3)");
                    int opt=scanner.nextInt();
                    if(opt==1){
                        System.out.println("1.minor\n2.senior\n(1 2)");
                        int opt1=scanner.nextInt();
                        if(opt1==1){
//                            System.out.println("enter new discount: ");
//                            double p1=scanner.nextDouble();
//                            d.setMinor(p1);
                            d.setM_status(1);
                            System.out.println("minor discount is enabled\n");
                        }
                        else{
//                            System.out.println("enter new discount: ");
//                            double p1=scanner.nextDouble();
//                            d.setSenior(p1);
                            d.setS_status(1);
                            System.out.println("senior discount is enabled\n");
                        }
                    }
                    else if(opt==2){
                        System.out.println("1.minor\n2.senior\n(1 2)");
                        int opt2=scanner.nextInt();
                        if(opt2==1){
                            d.setM_status(0);
                            System.out.println("minor discount is disabled\n");
                        }
                        else{
                            d.setS_status(0);
                            System.out.println("senior discount is disabled\n");
                        }
                    }
                    else if(opt==3){
                        System.out.println("1.minor\n2.senior\n(1 2)");
                        int opt3=scanner.nextInt();
                        if(opt3==1){
                            System.out.println("enter new discount: ");
                            double p1=scanner.nextDouble();
                            d.setMinor(p1);
                            System.out.println("Discount updated");

                        }
                        else{
                            System.out.println("enter new discount: ");
                            double p1=scanner.nextDouble();
                            d.setSenior(p1);
                            System.out.println("Discount updated");
                        }
                    }

                } else if (choice1==5) {

                    System.out.println("1.add deal\n2.remove deal\n(1 2)");
                    int optt=scanner.nextInt();
                    if(optt==1){
                        System.out.println("1.2 attraction\n2.3 attraction\n(1 2)");
                        int optt1=scanner.nextInt();
                        if(optt1==1){
//                            System.out.println("enter new discount: ");
//                            double p1=scanner.nextDouble();
//                            d.setMinor(p1);
                            d.setS_a_2(1);
                            System.out.println("discount enabled");
                        }
                        else{
//                            System.out.println("enter new discount: ");
//                            double p1=scanner.nextDouble();
//                            d.setSenior(p1);
                            d.setS_a_3(1);
                            System.out.println("discount enabled");
                        }
                    }
                    else if(optt==2){
                        System.out.println("1. 2 attraction\n2. 3 attraction\n(1 2)");
                        int optt2=scanner.nextInt();
                        if(optt2==1){
                            d.setS_a_2(0);
                            System.out.println("discount disabled");
                        }
                        else{
                            d.setS_a_3(0);
                            System.out.println("discount disabled");
                        }
                    }
                    else if(optt==3){
                        System.out.println("1.minor\n2.senior\n(1 2)");
                        int optt3=scanner.nextInt();
                        if(optt3==1){
                            System.out.println("enter new discount: ");
                            double p1=scanner.nextDouble();
                            d.setMinor(p1);
                            System.out.println("discount updated");

                        }
                        else{
                            System.out.println("enter new discount: ");
                            double p1=scanner.nextDouble();
                            d.setSenior(p1);
                            System.out.println("discount updated");
                        }
                    }


                } else if (choice1==6) {

                    admin.viewVisitorStats();

                } else if (choice1==7) {

                    admin.viewFeedback();

                }
            }//loop ending choice1
            System.out.println("logged out");


        } else if (choice == 2) {


            choose=0;
            while(choose!=3) {
                System.out.println("1. Register");
                System.out.println("2. Log In");
                System.out.println("3. exit");
                choose = scanner.nextInt();
                scanner.nextLine();

                if (choose == 1) {
                    // Register new visitor
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter your age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter your phone number: ");
                    String pn = scanner.nextLine();

                    System.out.println("Enter your balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Enter your email: ");
                    String email = scanner.nextLine();

                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();

                    Visitor newVisitor = new Visitor(name, age, pn, balance, email, password);
                    visit.add(newVisitor);
                    System.out.println("registration is successful");
                } else if (choose == 2) {
                    int aa=0;
                    Visitor vs = null;
                    while(aa==0){
                    System.out.println("Enter your email: ");
                    String email = scanner.nextLine();

                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    
                    for (Visitor v : visit){

                        if(v.getEmail().equals(email) && v.getPassword().equals(password)){
                            vs=v;
                            
                            System.out.println("logged in successfully");
                            aa=1;
                            break;
                        }
                    }
                    
                    if(aa==0){
                        
                        System.out.println("wrong password or email entered");
                    }
                    }

                    if(aa==1){
                        
                        choose1=0;
                        while(choose1!=9) {
                            System.out.println("Visitor Menu:\n" +
                                    "1. Explore the Zoo\n" +
                                    "2. Buy Membership\n" +
                                    "3. Buy Tickets\n" +
                                    "4. View Discounts\n" +
                                    "5. View Special Deals\n" +
                                    "6. Visit Animals\n" +
                                    "7. Visit Attractions\n" +
                                    "8. Leave Feedback\n" +
                                    "9. Log Out");
                            
                            System.out.print("Enter your choice: ");
                            choose1 = scanner.nextInt();

                            if (choose1==1){

                                choose2=0;
                                while(choose2!=3) {
                                    System.out.println("Explore the zoo:\n" +
                                            "1. view attractions\n" +
                                            "2. view animals\n" +
                                            "3. exit\n");
                                    System.out.print("Enter your choice: ");
                                    choose2= scanner.nextInt();

                                    if(choose2==1){
                                        System.out.println("\nattractions in the zoo: \n");
                                        admin.vA();
                                        System.out.println("Enter your choice to get description: \n");
                                        int c=scanner.nextInt();
                                        admin.vD(c);
                                    } else if (choose2==2) {

                                        admin.vAnimals();
                                        System.out.println("\n");

                                    }

                                }
                            } else if (choose1==2) {

                                if (vs.getMembership() == 1 || vs.getMembership()==2) {
                                    System.out.println("\nAlready have a membership\n");
                                } else{
                                    System.out.println("\nBuy Membership:");
                                System.out.println("1. Basic Membership (₹20)");
                                System.out.println("2. Premium Membership (₹50)");
                                System.out.print("Enter your choice: ");
                                int choose3 = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("\nAvailable discount codes:\n");
                                System.out.println("For minor(<18):" + d.getM_code() + "\n" + "For senior(60<):" + d.getS_code() + "\n");

                                System.out.print("Apply Discount Code: ");
                                String dis = scanner.nextLine();
                                double price = 0;
                                if (dis.equals("1342") && d.getM_status() == 1) {

                                    if (choose3 == 1) {
                                        price = 20 - (20 * d.getMinor() / 100);
                                        vs.setMembership(1);
                                    } else if (choose3 == 2) {
                                        price = 50 - (50 * d.getMinor() / 100);
                                        vs.setMembership(2);
                                    }


                                } else if (dis.equals("1332") && d.getS_status() == 1) {
                                    if (choose3 == 1) {
                                        price = 20 - (20 * d.getSenior() / 100);
                                        vs.setMembership(1);
                                    } else if (choose3 == 2) {
                                        price = 50 - (50 * d.getSenior() / 100);
                                        vs.setMembership(2);

                                    }

                                } else {
                                    System.out.println("no coupon applied");
                                    if(choose3==1){
                                        price=20;
                                        vs.setMembership(1);
                                    } else if (choose3==2) {
                                        price=50;
                                        vs.setMembership(2);
                                    }
                                }
                                if (vs.getBalance() >= price) {
                                    vs.setBalance(vs.getBalance() - price);
                                    if(d.getM_status()==0||d.getS_status()==0 ){
                                        System.out.println("coupon not currently applicable");
                                    }
                                    System.out.println("membership purchased successfully");
                                    System.out.println("available balance: " + vs.getBalance());

                                } else {

                                    System.out.println("\n\ninsufficient balance\n\n");
                                    vs.setMembership(0);
                                }
                            }
                            } else if (choose1==3) {
                                choose4=0;
                                if(vs.getMembership()==1){
                                while(choose4!=(admin.length()+1)){

                                    System.out.println("Buy Tickets:\n" +
                                            "Select an Attraction to Buy a Ticket:\n");
                                    admin.vA();
                                    System.out.println(admin.length()+1+"."+"exit");
                                    System.out.println("Enter your choice: ");
                                    choose4=scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Enter number of tickets to buy: ");
                                    int n=scanner.nextInt();
                                    scanner.nextLine();
                                    vs.buyTickets(admin,choose4,n,d);

                                }
                                } else if (vs.getMembership()==2) {
                                    System.out.println("\nticket are free for you\n");
                                }
                                else {
                                    System.out.println("\nbuy the membership first\n ");
                                }

                            } else if (choose1==4) {

                                System.out.println("\n Available discount codes:\n");
                                System.out.println("For minor(<18): " +d.getMinor()+"%: "+ d.getM_code() +
                                        "\n" + "For senior(60<):" + d.getSenior()+"%: "+ d.getS_code() + "\n");

                            } else if (choose1==5) {

                                System.out.println("Special Deals:\n" +
                                        "1. Buy 2 tickets and get" +d.getA_2()+"% off\n" +
                                        "2. Buy 3 tickets and get"+d.getA_3()+"% off\n");

                            } else if (choose1==6) {

                                vs.visitAnimal(admin);

                            } else if (choose1==7) {

                                System.out.println("Visit Attractions:\n");
                                vs.visitAttraction(admin);

                            } else if (choose1==8) {
                                while (true) {
                                    System.out.println("Leave Feedback:\nEnter your feedback (max 200 characters):");
                                    String st = scanner.nextLine();
                                    String s =scanner.nextLine();
                                    if (s.length() > 200) {
                                        System.out.println("Word limit exceeded.");
                                    } else {
                                        admin.addFeedback(s);
                                        System.out.println("Thank you for your feedback.");
                                        break;
                                    }
                                }

                            }
                        }
                    }

                }


            }
            System.out.println("logged out\n");

        } else if (choice == 3) {

            System.out.println("Special Deals:\n" +
                    "1. Buy 2 tickets and get" +d.getA_2()+"% off\n" +
                    "2. Buy 3 tickets and get"+d.getA_3()+"% off\n");

        } else {
            System.out.println("Invalid choice. Please try again.");
        }

    }


    }
}
