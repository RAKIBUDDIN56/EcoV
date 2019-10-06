package com.example.eco_v;

public class UsersDetailsDB {


        private String name;
        private String address;
        private int phoneNumber;
        private  String email;
        private String password;
        private  String id;


        public UsersDetailsDB(String name, String address) {
            //this.id=id;
            this.name=name;
            this.address=address;


        }

    public UsersDetailsDB(String name, String address, int phoneNumber, String email, String password) {
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.password=password;

    }

    public void setName(String name)
        {
            this.name = name;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }




        public String getName()
        {
            return name;
        }

        public String getAddress()
        {
            return address;
        }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



