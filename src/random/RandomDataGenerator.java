package random;

import java.util.Random;

public class RandomDataGenerator {

    public static String prepareUserName() {
        String userName[] = { "Karol", "Wojtek", "Dorota", "Marian", "Krzysiek", "Jan" };
        String randomUserName = userName[new Random().nextInt(userName.length)];
        return randomUserName;
    }


    public static String prepareFirstName() {
        String[] firstName = {"Aneta", "Joanna", "Tomek", "WOjtek", "Czarek"};
        String randomfirstName = firstName[new Random().nextInt(firstName.length)];
        return randomfirstName;
    }

    public static  String prepareLastName() {
        String[] lastName = {"Aneta", "Joanna", "Tomek", "WOjtek", "Czarek"};
        String randomlastName = lastName[new Random().nextInt(lastName.length)];
        return randomlastName;

    }

    public static  String prepareEmail () {
        String[] email = {"test", "test1", "test2", "test3", "test4"};
        String randomEmail = email[new Random().nextInt(email.length)];
        return  randomEmail;

    }
    public static  String preparePassword() {
        String[] password = {"test", "test1", "test2", "test3", "test4"};
        String randompasssword = password[new Random().nextInt(password.length)];
        return randompasssword;


    }
    //*************************************************************************************
    /*PaymentGenerator*////////////////////////////////////////////////////////////////////

    public static String CardNumberRandom(){

        //Zatwierdzenie typów oraz wykorzystanie Random
        String numerkarty="";
        Integer Liczba;
        Random random = new Random();

        //Dodawanie kolejnych liczb do numeru karty ... lepiej niż losowac mega dużą liczbe, po drugie bałem się że liczba przekroczy granice 32bitów
        for(int i =0;i<16;i++){
            Liczba=random.nextInt(9);
            numerkarty=numerkarty+Liczba.toString();
        }
        return numerkarty;
    }

    public static String Expires(){

        //wykorzystanie metody Random
        Random losowanie =new Random();

        //Wyznaczenie granic losowania, integer rok jest wpisany taki ... bo tak. Do zmiany najprawdopodobniej !!!
        Integer miesiac = losowanie.nextInt(11)+1;
        Integer rok = losowanie.nextInt(30);

        //Format Daty ma ../.. więc zabezpieczyłem go przed przypadkowym przycinaniem cyfr
        if(miesiac<10&&rok<10){
            return "0"+miesiac+"/"+"0"+rok;
        }
        if(miesiac<10){
            return "0"+miesiac+"/"+rok;
        }
        if(rok<10){
            return miesiac+"/"+"0"+rok;
        }
        return miesiac+"/"+rok;
    }

    public static String RandomCCV(){

        //dodanie metody Random
        Random generator = new Random();

        //tak to napisane zostało bo kod CCV msui być 3 cyfrowy
        Integer ccv = generator.nextInt(899)+100;

        //Sprytne zamienienie na Stringa, bez "" nie działa
        return ""+ccv;
    }



    //*************************************************************************************
}
