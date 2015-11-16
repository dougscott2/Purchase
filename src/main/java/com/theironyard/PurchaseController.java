package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DrScott on 11/11/15.
 */
@Controller
public class PurchaseController {
    @Autowired
    CustomerRepository customers;
    @Autowired
    PurchaseRepository purchases;


    @PostConstruct
    public void init() throws FileNotFoundException {//to win it


       //scanner method of reading and parsing a file

       /* if (customers.count() == 0) {
        Scanner scanner = new Scanner(new File("customers.csv"));
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Customer c = new Customer();
                c.name = columns[0];
                c.email = columns[1];
                customers.save(c);
            }
        }
        if (purchases.count() == 0){
            Scanner scanner = new Scanner(new File("purchase.csv"));
            scanner.nextLine();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Purchase p = new Purchase();
                p.date = columns[1];
                p.creditCard = columns[2];
                p.cvv = columns[3];
                p.category = columns[4];
                int count = Integer.valueOf(columns[0]);
                Customer customer = customers.findOne(count);
                p.customer = customer;
                purchases.save(p);
            }

        }*/


        //reading file using the readFile thang
        if (customers.count() == 0) {
            String fileContent = readFile("customers.csv");
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                if (line == lines[0])
                 continue;
                String[] columns = line.split(",");
                Customer customer = new Customer();
                customer.name = columns[0];
                customer.email = columns[1];
                customers.save(customer);
            }
        }

        if (purchases.count() == 0) {
            String fileContent = readFile("purchases.csv");
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                if (line == lines[0])
                    continue;
                    String[] columns = line.split(",");
                    Purchase purchase = new Purchase();
                    purchase.date = columns[1];
                    purchase.creditCard = columns[2];
                    purchase.cvv = columns[3];
                    purchase.category = columns[4];
                    int customerId = Integer.valueOf(columns[0]);//
                    Customer customer = customers.findOne(customerId); //this part was bananas
                    purchase.customer = customer;//
                    purchases.save(purchase);
                }
            }
    }

    @RequestMapping("/")
    public String home( Model model, String category, @RequestParam(defaultValue = "0") int page){
        PageRequest pr = new PageRequest(page, 10);
        Page p;

        if (category != null) {
          //  model.addAttribute("customers", customers.findAll());
          //  model.addAttribute("purchases", purchases.findByCategory(pr, category));
            p = purchases.findByCategory(pr, category);
        }else {
            // model.addAttribute("customers", customers.findAll());
            p = purchases.findAll(pr);
        }
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("category", category);
        model.addAttribute("purchases", p);
        model.addAttribute("showNext", p.hasNext());
        return "home";
    }
    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }
}
