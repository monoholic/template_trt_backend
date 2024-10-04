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

    // ThreadLocal 변수를 사용하여 현재 인터셉터가 동작 중인지 여부를 확인
    private static final ThreadLocal<Boolean> isInserting = ThreadLocal.withInitial(() -> false);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 이미 SQL 삽입 작업 중이라면 바로 기존 동작을 진행하도록 처리
        if (isInserting.get()) {
            return invocation.proceed();
        }

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

            // 무한반복 방지 플래그 설정
            isInserting.set(true);
            try {
                // SQL을 DB에 저장하는 로직(조회 제외)
                int res = logAPIRepository.addSql(params);

                // 원래 SQL 실행
                return invocation.proceed();
            } finally {
                // 작업 완료 후 플래그 해제
                isInserting.set(false);
            }
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

