package com.company;

import java.io.*;

public class MySerializable implements Serializable {
  private int age;
  // 名字
  @Serial private static final long serialVersionUID = 1;
  private String name;

  public MySerializable() {}

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

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    FileOutputStream fileOutputStream = new FileOutputStream("temp.txt");
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    MySerializable mySerializable = new MySerializable();

    mySerializable.setAge(19);
    mySerializable.setName("saf");

    objectOutputStream.writeObject(mySerializable);
    objectOutputStream.flush();
    objectOutputStream.close();

    FileInputStream fis = new FileInputStream("temp.txt");
    ObjectInputStream oin = new ObjectInputStream(fis);
    MySerializable user = (MySerializable) oin.readObject();
    System.out.println("name=" + user.getName());
  }
}
