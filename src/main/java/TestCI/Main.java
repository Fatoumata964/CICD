package TestCI;
public class Main {
        public static void main(String[] args)
        {
        Person person = new Person(args[0] ,Integer.parseInt(args[1]));
        System.out.println(person.getAge());
        Person2 person2 = new Person2(args[0] ,Integer.parseInt(args[1]));
        System.out.println(person2.getAge())
        }
}

