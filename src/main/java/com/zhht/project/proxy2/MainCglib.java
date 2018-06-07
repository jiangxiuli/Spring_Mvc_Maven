package com.zhht.project.proxy2;

public class MainCglib {
    public static void main(String[] args) {
        BookFacadeImpl bookFacade = new BookFacadeImpl();
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacade bookCglib = (BookFacade) cglib.getInstance(bookFacade);
        bookCglib.addBook();
    }
}
