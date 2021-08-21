package com.ardi.apppenyimpananktp;

import com.google.firebase.database.Exclude;



public class User {
        private String password;
        private String email;
        private String status;
        private String keyusr;
        private int position;

        public User() {
            //empty constructor needed
        }
        public User (int position){
            this.position = position;
        }
        public User(String password, String email, String status) {
            if (email.trim().equals("")) {
                email = "Email Kosong";
            }
            this.email = email;
            this.password = password;
            this.status=status;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return getEmail();
        }
        @Exclude
        public String getKeyusr() {
            return keyusr;
        }
        @Exclude
        public void setKeyusr(String keyusr) {
            this.keyusr = keyusr;
        }
    }

