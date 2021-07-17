package dev.wg.xy.infrastructure;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CrudProvider implements ProviderMethodResolver {

    public String findById(final String id) {
        return new SQL() {{
            SELECT("");

        }}.toString();
    }
}
