package com.example.needforneedy;

import java.io.Serializable;

public class VolunteerModel implements Serializable {
   String  firstName,lastName, email, phone, Password , ConfirmPassword ,weekday,spinner;

   public VolunteerModel(String firstName, String lastName, String email, String phone, String password, String confirmPassword, String weekday, String spinner) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phone = phone;
      Password = password;
      ConfirmPassword = confirmPassword;
      this.weekday = weekday;
      this.spinner = spinner;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPassword() {
      return Password;
   }

   public void setPassword(String password) {
      Password = password;
   }

   public String getConfirmPassword() {
      return ConfirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
      ConfirmPassword = confirmPassword;
   }

   public String getWeekday() {
      return weekday;
   }

   public void setWeekday(String weekday) {
      this.weekday = weekday;
   }

   public String getSpinner() {
      return spinner;
   }

   public void setSpinner(String spinner) {
      this.spinner = spinner;
   }
}
