package br.sp.msoares.services;

public class mockServices {

    public int somar(int a, int b) {
        return a + b;
    }

    public boolean boleano(boolean a, boolean b) {

        if (a || b) {
            return true;
        } else if (a && b) {
            return true;
        } else {
            return false;
        }
    }

    public String stringer(String mystring){
        return mystring;
    }

}