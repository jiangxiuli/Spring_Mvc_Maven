package com.zhht.project.design.prototype;

public class ManMessage implements Cloneable {
    private Address address;

    public ManMessage() {
        super();
    }

    private int age;

    private String name;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ManMessage [address=" + address + ", age=" + age + ", name=" + name + "]";
    }
    
    public Object clone() throws CloneNotSupportedException{
        ManMessage manMessage = (ManMessage) super.clone();
        manMessage.address = (Address) address.clone();
        return manMessage;
    }


    public static void main(String[] args) {
        Address address1 = new Address();
        address1.setCountry("中国");
        address1.setCity("北京");
        address1.setStreet("丹棱街");

        ManMessage manMessage1 = new ManMessage();
        manMessage1.setAge(6);
        manMessage1.setName("峻");
        manMessage1.setAddress(address1);

        try {
            ManMessage manMessage2 = (ManMessage) manMessage1.clone();
            Address address2 = manMessage2.getAddress();
            address2.setCity("东北");
            manMessage2.setAddress(address2);
            System.out.println(manMessage1.toString());
            System.out.println(manMessage2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
