package OnlineStore.P1_files.P1_1_customer_file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersCSVFile {

    private final static String FILE_PATH = "E:\\Wantsome\\OnlineStoreBackendSystem\\customers.csv";
    private List<Customer> customerList = new ArrayList<>();

    public List<Customer> getCustomerList() {
        return customerList;
    }


    public List<Customer> readCustomerFromFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            importCustomers(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerList;

    }

    public void importCustomers(BufferedReader br) throws IOException {
        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            customerList.add(parseLine(line));
        }
    }

    public Customer parseLine(String line) {
        String[] customerDetails = line.split(",");
        //System.out.println(Arrays.toString(customerDetails));

        if (customerDetails.length == 4) {
            Customer customer = new Customer();
            customer.setCustomerID(Integer.parseInt(customerDetails[0]));
            customer.setFirstName(customerDetails[1]);
            customer.setLastName(customerDetails[2]);
            customer.setAddress(customerDetails[3]);

            return customer;
        }
        return new Customer();

    }

    //public CustomersCSVFile readCustomerFromFile(){
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            importCustomers(br);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return this;
//
//    }


}


