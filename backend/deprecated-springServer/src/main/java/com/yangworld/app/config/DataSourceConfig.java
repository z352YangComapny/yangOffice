/*
package com.yangworld.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        String path = this.getClass().getResource("/Wallet_yang").getPath(); // src/main/resources 하위에 위치시킨 지갑폴더를 target/classse하위 클래스패스에서 참조한다.
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os); // windows 10
        if(os.contains("win"))
            path = path.replaceFirst("/", ""); // window에서는 시작되는 /를 제거해야 한다.

        System.out.println(path);
        dataSource.setURL("jdbc:oracle:thin:@yang_high?TNS_ADMIN=" + path); // 서비스명은 지갑폴더명에서 WALLET_을 제외한 소문자이름_high (high/midium/low 중 선택)
        dataSource.setUser("yang");
        dataSource.setPassword("Thdud@170821");
        return dataSource;
    }




}
*/
