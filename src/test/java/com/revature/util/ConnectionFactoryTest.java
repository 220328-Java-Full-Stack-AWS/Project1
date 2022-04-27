package com.revature.util;

import org.junit.Test;

import java.sql.Connection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConnectionFactoryTest {

    private static ConnectionFactory connectionFactory;

    @Test
    public void testConnectionFactoryIsAbleToGetConnection() {
        Connection conn = connectionFactory.getConnection();

        assertThat(conn, instanceOf(Connection.class));
    }
}
