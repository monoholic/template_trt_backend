package kr.co.trito.utils;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;

public class ExpressionUtil {
    public static Expression<String> getDeptName(StringPath val1, String val2, String alias) {
        return Expressions.stringTemplate(
                "F_GETDEPTNAME({0}, {1})",
                val1, val2
        ).as(alias);
    }

    public static Expression<String> toChar(DateTimePath<?> val1, String val2, String alias) {
        return Expressions.stringTemplate(
                "TO_CHAR({0}, {1})",
                val1, val2
        ).as(alias);
    }
}
