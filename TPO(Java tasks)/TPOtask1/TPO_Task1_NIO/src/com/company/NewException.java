package com.company;

public class NewException extends Throwable{
    public NewException(String mess){ super(mess);}
    public NewException(Throwable mess){ super(mess);}
}