package kr.co.trito.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.trito.domain.repository.mybatis.logAPI.LogAPIRepository;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type= StatementHandler.class, method = "update", args={Statement.class})
        , @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
})
public class MybatisInterceptor implements Interceptor {

    @Autowired
    @Lazy
    private LogAPIRepository logAPIRepository;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 쿼리 실행 전 처리할 작업
        // sql문
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        String originalSql = statementHandler.getBoundSql().getSql();

        // 로그 추가 SQL문을 제외하기 위한 조건
        if (originalSql.contains("INSERT INTO TP_API_LOG")) {
            // 로그 추가 SQL문이라면 바로 실행
            return invocation.proceed();
        }

        // 상세구분
        int idx = originalSql.indexOf(" ");
        String preStrSQL = originalSql.substring(0, idx);

        // 조회의 경우 로그 기록 X
        if(!preStrSQL.equals("SELECT")) {
            // 파라미터
            Object paramList = statementHandler.getParameterHandler().getParameterObject();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.convertValue(paramList, Map.class);

            String param = "";
            for(String key: map.keySet()){
                if(map.get(key) != null){
                    param += key + "=" + map.get(key) + ", ";
                }
            }

            Map<String, Object> params = new HashMap<>();
            params.put("preStrSQL", preStrSQL);
            params.put("originalSql", originalSql);
            params.put("param", param);

            // SQL을 DB에 저장하는 로직(조회 제외)
            int res = logAPIRepository.addSql(params);

            // 원래 SQL 실행
            return invocation.proceed();
        } else {
            // 원래 SQL 실행
            return invocation.proceed();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 설정된 속성 사용
    }

}

