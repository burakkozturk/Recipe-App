package recipe_book.demo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DataSourceShutdownHandler {

    @Autowired
    private HikariDataSource dataSource;

    @PreDestroy
    public void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
            System.out.println("HikariDataSource closed successfully.");
        }
    }
}
