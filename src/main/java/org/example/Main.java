package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;



public class Main {
    static int Index = 50;

    static String[][] books = new String[Index][4];// title,author, ISBN, status
    static String[][] users = new String[Index][4];// TC, email, password, fullName
    static String[][] transactions = new String[Index][4];// userId, ISBN, date, status
    static Scanner scanner = new Scanner(System.in);
    private int[] itemCount;

    static int bookQuantity = 0;
    static int transactionQuantity = 0;
    static int userQuantity = 0;


    static void deleteUserInformation(String userId) {
        int index = -1;
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][0].equals(userId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Kullanıcı bulunamadı!");
            return;
        }
        String[][] newUsers = new String[userQuantity - 1][4];
        int newIndex = 0;
        for (int i = 0; i < userQuantity; i++) {
            if (i != index) {
                newUsers[newIndex] = users[i];
                newIndex++;
            }
        }
        users = newUsers;
        userQuantity--;

        System.out.println("Kullanıcı başarılı bir şekilde silindi.");
    }

    public static void main(String[] args) {

    }

    public static void signUp() {

        System.out.println("User Name :");
        String userName = scanner.nextLine();

        System.out.println("User ıd :");
        String userId = scanner.nextLine();

        System.out.println("Email adress: ");
        String email = scanner.nextLine();

        System.out.println("Password :");
        String password = scanner.nextLine();

        users[userQuantity][0] = userName;
        users[userQuantity][1] = userId;
        users[userQuantity][2] = email;
        users[userQuantity][3] = password;

        successTransaction("Sign up");
    }

    public static void updateUserInfo(String userId, String newEmail, String newPassword, String newFullName) {
        userId = (users[Index][0]);

        System.out.print("Güncellemek için kullanıcı TC giriniz: ");
        String tckn = users[Index][0];

        int userIndex = -1;
        for (int i = 0; i < userQuantity; i++) {
            if (userId.equals(tckn)) {
                userIndex = i;
                break;
            }
        }
        if (!userId.equals(tckn)) {
            System.out.println("Kullanıcı bulunamadı!");
            return;
        }

        users[userIndex][1] = newEmail;
        users[userIndex][2] = newPassword;
        users[userIndex][3] = newFullName;

        System.out.println("Kullanıcı bilgileri başarıyla güncellendi!");
    }

    public static int invalidLoginCheck(String email, String password) {
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][2].equals(email) && users[i][3].equals(password)) {
                System.out.println("Giriş kontrol edildi");
                return i;
            }
        }
        System.out.println("Giriş başarısız! Lütfen email ve şifrenizi kontrol ediniz.");
        return -1;
    }


    public static void successTransaction(String temp) {

        System.out.println(temp + "Başarılı İşlem: ");
    }


    static void viewAvailableBooks() {
        if (bookQuantity == 0) {

            System.out.println("Kütüphanede uygun kitap kalmadı.");
        } else {
            System.out.println("\nUygun Kitapların Listesi:");
            System.out.println("Title \t Author \t ISBN \t Status");
            for (int i = 0; i < bookQuantity; i++) {
                System.out.println(books[i][0] + "\t" + books[i][1] + "\t" + books[i][2] + "\t" + books[i][3]);
            }
        }
    }

    public void updateArrays() {

        System.out.println("Kitap Listesi:");
        for (int i = 0; i < bookQuantity; i++) {
            System.out.println("Kitap " + (i + 1) + ":");
            System.out.println("Başlık: " + books[i][0]);
            System.out.println("Yazar: " + books[i][1]);
            System.out.println("ISBN: " + books[i][2]);
            System.out.println("Durum: " + books[i][3]);
        }

        System.out.println("Kullanıcı Listesi:");
        for (int i = 0; i < userQuantity; i++) {
            System.out.println("Kullanıcı " + (i + 1) + ":");
            System.out.println("TC: " + users[i][0]);
            System.out.println("Email: " + users[i][1]);
            System.out.println("İsim: " + users[i][3]);
        }

        System.out.println("İşlem Listesi:");
        for (int i = 0; i < transactionQuantity; i++) {
            System.out.println("İşlem " + (i + 1) + ":");
            System.out.println("Kullanıcı ID: " + transactions[i][0]);
            System.out.println("Kitap ISBN: " + transactions[i][1]);
            System.out.println("Tarih: " + transactions[i][2]);
            System.out.println("Durum: " + transactions[i][3]);
        }
    }

    public void addBook(String title, String author, String isbn) {

        if (bookQuantity < Index) {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = isbn;
            books[bookQuantity][3] = "alınabilir";
            bookQuantity++;
            System.out.println("Kitap başarıyla eklendi.");
        } else {
            System.out.println("Kitap eklenemedi. Kütüphane dolu");
            extendBooksArrayOnAddition();
        }
    }

    public static void addTransaction(String userId, String ISBN, String date, String status) {
        if (transactionQuantity < Index) {
            transactions[transactionQuantity][0] = userId;
            transactions[transactionQuantity][1] = ISBN;
            transactions[transactionQuantity][2] = date;
            transactions[transactionQuantity][3] = status;
            transactionQuantity++;

            System.out.println("İşlem yapıldı");
        } else {
            System.out.println("İşlem yapılamadı.");
        }
    }

    public static int isAvailable(String ISBN) {
        int temp = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i][2].equals(ISBN)) {
                temp = i;

            }
        }
        return temp;
    }

    public static void reserveBook(String ISBN, String authorId, String date) {
        int response = isAvailable(ISBN);
        if (response != -1) {
            System.out.println("Kitap rezerve edildi.");
            addTransaction(authorId, ISBN, date, "Rezerve Edildi");
        } else {
            System.out.println("Bu kitap kütüphanede bulunmuyor.");
        }

    }

    static void generateReport() {
        int availableBooks = bookQuantity - transactionQuantity;

        System.out.println("Toplam kitap sayısı: " + bookQuantity);
        System.out.println("Kullanıcıdaki kitap sayısı: " + transactionQuantity);
        System.out.println("Mevcut kitap sayısı: " + availableBooks);
    }

    public boolean bookAvailable(String ISBN) {
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                return true;
            }

        }
        return false;
    }

    public static boolean checkOutBook(String userID, String bookISBN) {

        if (checkBookReturnDeadline(userID)) {
            System.out.println("İade tarihi geçmiş kitabınız var, bu nedenle yeni bir kitap alamazsınız.");
            return false;
        }

        boolean isFound = false;

        for (String[] book : books) {
            if (book != null && book[2] == null && book[2].equals(bookISBN)) {
                isFound = true;

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(dateFormatter);

                transactions[transactionQuantity][0] = bookISBN;
                transactions[transactionQuantity][1] = userID;
                transactions[transactionQuantity][2] = formattedDate;

                System.out.println("Kitap başarıyla " + userID + " numaralı kullanıcıya verildi. ");
                break;
            }
        }
        if (isFound) {

            System.out.println("Aradığınız kitap bulunamıyor!");
        }
        return isFound;

    }

    public static void detailBook(String data) {
        boolean isFound = false;

        for (String[] book : books) {

            if (book[0].equalsIgnoreCase(data) || book[1].equalsIgnoreCase(data)) {
                System.out.println("Kitabın ismi : " + book[0] + "Yazarı : " + book[1] + "ISBN : " + book[2]
                        + "Açıklaması : " + book[3]);
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("DETAYINI GÖRMEK İSTEDİĞİNİZ KITAB BULUNAMADI ");
        }
    }

    public void updateBook(String isbnToUpdate, String newTitle, String NewAuthor) {
        boolean found = false;

        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(isbnToUpdate)) {
                books[i][0] = newTitle;
                books[i][1] = NewAuthor;

                found = true;
                System.out.println("Kitap başarıyla güncellendi.");
                break;
            }
        }
        if (!found) {
            System.out.println("Girilen isbn numarasına ait kitap bulunamadı.");
        }

    }



  

    public static void truncateBooksArrayOnDeletion(int index){

        for (int i = index; i < bookQuantity - 1; i++) {
            books[i] = books[i + 1];
        }
        bookQuantity--; // deleteBook() metodu gelebilir mi?

        if (bookQuantity < Index) {
            String[][] newBooks = new String[Index--][4];
            if (bookQuantity >= 0)
                System.arraycopy(books, 0, newBooks, 0, bookQuantity);
            books = newBooks;
        }

        System.out.println("Kitap başarıyla silindi!");

    }


    public void countTotalBooks() {
        if (bookQuantity == 0) {
            System.out.println("Kütüphanede kitap yok.");
        } else {
            System.out.println("\nToplam kitap sayısı:");
            System.out.println(bookQuantity);
        }
    }


    private static void extendBooksArrayOnAddition() {
        Index++;
        String[][] newBooks = new String[Index][4];

        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        books = newBooks;
        System.out.println("Kitap dizisi başarıyla uzatıldı.");
    }

    public static boolean checkBookReturnDeadline(String userId) {
        String lastBorrowedDateStr = null;
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId) && transactions[i][3].equals("alındı")) {
                lastBorrowedDateStr = transactions[i][2];
                break;
            }
            if (lastBorrowedDateStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate lastBorrowedDate = LocalDate.parse(lastBorrowedDateStr, formatter);
                LocalDate currentDate = LocalDate.now();


                long diffInDays = ChronoUnit.DAYS.between(lastBorrowedDate, currentDate);
                if (diffInDays > 30) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void checkPatronEligibilityForCheckout(String userId) {
        String lastBorrowedDateStr = null;

        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId) && transactions[i][3].equals("alındı")) {
                lastBorrowedDateStr = transactions[i][2];
                break;
            }
            if (lastBorrowedDateStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate lastBorrowedDate = LocalDate.parse(lastBorrowedDateStr, formatter);
                LocalDate currentDate = LocalDate.now();

                long diffInDays = ChronoUnit.DAYS.between(lastBorrowedDate, currentDate);

                if (diffInDays > 30) {
                    System.out.println("Kitap iade süreniz dolmuştur en kısa sürede kitabı iade ediniz.");
                } else {
                    System.out.println("Şuan elinizde kitap olduğu için yenisini alamazsınız.");
                }
            }
            if (lastBorrowedDateStr == null) {
                System.out.println("İstediğiniz kitabı alabilirsiniz.");
            }
        }
    }
}



