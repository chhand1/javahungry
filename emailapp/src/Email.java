import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;
    private String alternateEmail;
    private int defaultPasswordLength = 10;
    private String companySuffix = "xyz.com";

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        this.password = randomPassword(defaultPasswordLength);
        this.email = this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() + "@" + this.department.toLowerCase() + "." + this.companySuffix.toLowerCase();
    }

    private String setDepartment() {
        System.out.println("Enter the department\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None\nEnter department code: ");
        Scanner in = new Scanner(System.in);
        int deptChoice = in.nextInt();
        if(deptChoice == 1) {return "Sales";}
        else if(deptChoice == 2) {return "Development";}
        else if(deptChoice == 3) {return "Accounting";}
        else {return "";}
    }

    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password = new char[length];
        for(int i = 0; i<length; i++) {
           int rand = (int) (Math.random() * passwordSet.length());
           password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }
    public String getAlternateEmail() {
        return this.alternateEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public String showInfo() {
        return "Display Name: " + this.firstName + " " + this.lastName +
                "\n" + "Company email: " + this.email + "\n" +
                "MailBox Capacity: " + this.mailboxCapacity;
    }


}
