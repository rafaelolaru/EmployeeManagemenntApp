package org.loose.fis.registration.example.model;

import org.loose.fis.registration.example.exceptions.LunaInexistenta;
import org.loose.fis.registration.example.exceptions.ZiInexistenta;

public class Transaction {
    private float payment;
    private int day;
    private int month;
    private boolean salary;

    public Transaction(){

    }

    public Transaction(float payment, int day, int month, boolean salary) {
        this.payment = payment;
        this.day = day;
        this.month = month;
        this.salary = salary;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setSalary(boolean salary){this.salary = salary;}

    public float getPayment() {
        return payment;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public boolean getSalary() {return salary;}

    public boolean isSalary(){return salary;}

    public boolean verificaDate() throws ZiInexistenta, LunaInexistenta {
        boolean ok = true;
        if(this.month<1 || this.month>12){
            ok = false;
            throw new LunaInexistenta("Luna inexistenta");
        }
        if (this.month == 1) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 2) {
            if (this.day < 1 || this.day > 28) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 3) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 4) {
            if (this.day < 1 || this.day > 30) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 5) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 6) {
            if (this.day < 1 || this.day > 30) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 7) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 8) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 9) {
            if (this.day < 1 || this.day > 30) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 10) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 11) {
            if (this.day < 1 || this.day > 30) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        if (this.month == 12) {
            if (this.day < 1 || this.day > 31) {
                ok = false;
                throw new ZiInexistenta("Zi inexistenta");
            }
        }
        return ok;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "payment=" + payment +
                ", day=" + day +
                ", month=" + month +
                ", salary=" + salary +
                '}';
    }
}
