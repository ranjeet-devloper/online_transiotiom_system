package com.company;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.EOFException;

public class Main {

    public static void main (String[] args)  throws Exception {
        // write your code here
        File f1 = new File("F:/my_portfolio/ONLINE TRANSITION SYSTEM/login_detail.txt");
        f1.createNewFile();
        File f2 = new File("F:/my_portfolio/ONLINE TRANSITION SYSTEM/account_detail.txt");
        f2.createNewFile();
        File f3 = new File("F:/my_portfolio/ONLINE TRANSITION SYSTEM/account_no.txt");
        f3.createNewFile();
        File f4 = new File("F:/my_portfolio/ONLINE TRANSITION SYSTEM/account_userId.txt");
        f4.createNewFile();
        ObjectOutputStream oos = null;
        ObjectOutputStream oos2 = null;
        ObjectInputStream ois = null;
        ObjectInputStream ois2 = null;
        ObjectOutputStream oos3 = null;
        ObjectInputStream ois3 = null;
        ObjectOutputStream oos4 = null;
        ObjectInputStream ois4 = null;
        Scanner sc = new Scanner(System.in);
        Scanner scr = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader(f3));
        String str;
        HashMap<String, String> hs = new HashMap<>();
        HashMap<Integer, Integer> hs2 = new HashMap<>();
        HashMap<Integer,String> hs3= new HashMap<>();
        int n = -1;
        Integer k = 0;
        while (n != 0) {
            if (f1.isFile()&&f1.length()!=0) {
                ois = new ObjectInputStream(new FileInputStream(f1));
                try {
                    hs = (HashMap<String, String>) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ois.close();

            }
            if (f2.isFile()&&f2.length()!=0) {
                ois2 = new ObjectInputStream(new FileInputStream(f2));
                hs2 = (HashMap<Integer, Integer>) ois2.readObject();
                ois2.close();
            }
           if (f4.isFile()&&f4.length()!=0) {
                ois4 = new ObjectInputStream(new FileInputStream(f4));
                hs3 = (HashMap<Integer, String>) ois4.readObject();
                ois4.close();
            }
            if (f3.isFile()&&f3.length()!=0) {
                ois3 = new ObjectInputStream(new FileInputStream(f3));
                k = (Integer) ois3.readObject();
                ois3.close();
            }
            System.out.println("Enter Your Choice");
            System.out.println("1.New User");
            System.out.println("2.Existing User");
            System.out.println("0.Exit");
            n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter User Id to Create");
                    String s1 = scr.nextLine();
                    if (hs.containsKey(s1)) {
                        System.out.println("User Id Already Exist");
                        break;
                    } else {
                        // scr.next();
                        System.out.println("Enter Pasword to create");
                        String s2 = scr.nextLine();
                        hs.put(s1, s2);
                        k++;
                        hs2.put(k, 0);
                        hs3.put(k,s1);
                        System.out.println("Your Account no is:- " + k);
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(f1));
                    oos.writeObject(hs);
                    oos.close();
                    oos2 = new ObjectOutputStream(new FileOutputStream(f2));
                    oos2.writeObject(hs2);
                    oos2.close();
                    oos3 = new ObjectOutputStream(new FileOutputStream(f3));
                    oos3.writeObject(k);
                    oos3.close();
                    oos4 = new ObjectOutputStream(new FileOutputStream(f4));
                    oos4.writeObject(hs3);
                    oos4.close();
                    break;
                case 2:
                    System.out.println("Enter your User Id");
                    String s3 = scr.nextLine();
                    if (hs.containsKey(s3)) {
                        System.out.println("Enter your password");
                        String s4 = scr.nextLine();
                        if (getKeys(hs, s4, s3)) {
                            System.out.println("welcome to the OnlineBanking Systems " + s3);
                            System.out.println("----------------------------------------------");

                            int g=-1;
                            while(g!=0) {
                                System.out.println(s3 + " Please Chosse Your Choice");
                                System.out.println("1.view your Balance");
                                System.out.println("2.Send Money");
                                System.out.println("3.Withdraw Money");
                                System.out.println("4.Deposite Money");
                                System.out.println("5.Exit");
                                switch (sc.nextInt()) {
                                    case 1:
                                        System.out.println("Enter your Account no");
                                        int acc = sc.nextInt();
                                        if (hs2.containsKey(acc)) {
                                            if (hs3.containsKey(acc)) {
                                                if (Objects.equals(s3, hs3.get(acc))) {
                                                    for (Map.Entry<Integer, Integer> entry : hs2.entrySet()) {
                                                        if (Objects.equals(entry.getKey(), acc)) {
                                                            System.out.println("Your Account Balance is:- " + entry.getValue());
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("It is not Your Account no");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("This account is not Mapped");
                                                break;
                                            }
                                        } else
                                            System.out.println("Wrong Account no");
                                        break;
                                    case 2:
                                        System.out.println("Enter your Account no");
                                        int yourAcc = sc.nextInt();
                                        if (hs2.containsKey(yourAcc)) ;
                                    {
                                        if (hs3.containsKey(yourAcc)) {
                                            if (Objects.equals(s3, hs3.get(yourAcc))) {
                                                System.out.println("Enter Benificiary Account no");
                                                int benificaryAcc = sc.nextInt();
                                                if (hs.containsKey(benificaryAcc)) ;
                                                {
                                                    System.out.println("Enter Amount To Transfer");
                                                    int Amount = sc.nextInt();
                                                    int amt = hs2.get(yourAcc);
                                                    if (amt < Amount) {
                                                        System.out.println("Insufficient Balance");
                                                        break;
                                                    } else {
                                                        hs2.put(yourAcc, hs2.get(yourAcc) - Amount);
                                                        hs2.put(benificaryAcc, Amount + hs2.get(benificaryAcc));
                                                        System.out.println("Your Tranction is Sucessful");
                                                        oos2 = new ObjectOutputStream(new FileOutputStream(f2));
                                                        oos2.writeObject(hs2);
                                                        oos2.close();
                                                        break;
                                                    }
                                                }
                                            } else {
                                                System.out.println("It is not Your Account");
                                                break;
                                            }
                                        }
                                    }
                                    case 3:
                                        System.out.println("Enter Your Account No");
                                        int ac = sc.nextInt();
                                        if (hs2.containsKey(ac)) {
                                            if(hs3.containsKey(ac)) {
                                                if (Objects.equals(s3, hs3.get(ac))){
                                                    System.out.println("Enter Amount to Withdraw");
                                                int rupee = sc.nextInt();
                                                int paisa = (hs2.get(ac));
                                                if (rupee > paisa) {
                                                    System.out.println("Insufficient Balance");
                                                    break;
                                                } else {
                                                    hs2.put(ac, hs2.get(ac) - paisa);
                                                    System.out.println("Transaction Sucessful");
                                                    oos2 = new ObjectOutputStream(new FileOutputStream(f2));
                                                    oos2.writeObject(hs2);
                                                    oos2.close();
                                                    break;
                                                }
                                            }
                                                else
                                                {
                                                    System.out.println("It is not Your Account");
                                                    break;
                                                }
                                        }
                                }else
                                            System.out.println("Invalid Account no");
                                        break;
                                    case 4:
                                        System.out.println("Enter Account no");
                                        int dep = sc.nextInt();
                                        if (hs2.containsKey(dep)) {
                                            System.out.println("Enter Amount to e Deposite");
                                            int depamt = sc.nextInt();
                                            hs2.put(dep, depamt + hs2.get(dep));
                                            System.out.println("Your Transaction is Sucessfull");
                                            oos2 = new ObjectOutputStream(new FileOutputStream(f2));
                                            oos2.writeObject(hs2);
                                            oos2.close();
                                        } else
                                            System.out.println("Invalid Account");
                                        break;
                                    case 5:
                                        return;
                                    default:
                                        System.out.println("Invalid Choice");
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    System.out.println("Access Denied");
                    break;
                case 0:
                    return;
                default :
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    private static boolean getKeys(Map<String,String> hs,String value,String s2)
    {
        if(hs.containsValue(value))
        {
            for(Map.Entry<String,String> entry : hs.entrySet())
            {
                if(Objects.equals(entry.getValue(),value))
                {
                    if(Objects.equals(entry.getKey(),s2))
                        return true;
                }
            }
        }
        return false;
    }
}
// class logi
// {
//     String userid;
//     String password;
//     logi(String userid,String password)
//     {
//         this.userid=userid;
//         this.password=password;
//     }
// }