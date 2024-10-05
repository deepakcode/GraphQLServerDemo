package com.example.demo.graphql;

import com.example.demo.db.DummyDB;
import com.example.demo.model.Laptop;
import org.springframework.stereotype.Component;

@Component
public class DataFetcher {
    DummyDB dummyLaptopDB = new DummyDB();

    public graphql.schema.DataFetcher getAllLaptops() {
        return dataFetchingEnvironment -> dummyLaptopDB.getAllLaptops();
    }

    public graphql.schema.DataFetcher getLaptopByID() {
        return dataFetchingEnvironment -> dummyLaptopDB
                .getLaptopByID(Integer.parseInt(dataFetchingEnvironment.getArgument("id")))
                .orElse(new Laptop(0, "None", 0, 0));
    }

    public graphql.schema.DataFetcher getLaptopsLessThan() {
        return dataFetchingEnvironment -> dummyLaptopDB.getLaptopsLessThan(dataFetchingEnvironment.getArgument("price"));
    }

    public graphql.schema.DataFetcher deleteLaptop() {
        return dataFetchingEnvironment -> dummyLaptopDB.deleteLaptop(Integer.parseInt(dataFetchingEnvironment.getArgument("id")));
    }
}
