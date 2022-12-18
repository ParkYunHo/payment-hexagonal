package com.john.payment.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.john.payment.PaymentHexagonalApplicationTests;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = PaymentHexagonalApplicationTests.class)
public class SimpleTest {
    @Test
    public void TEST_java17_function() {
        // java17 - 텍스트블록
        String query = """
            {
                [
                    "a1": "a1",
                    "b2": "b2"
                ]
            }
            """;
        assertEquals(
            """
            {
                [
                    "a1": "a1",
                    "b2": "b2"
                ]
            }
            """, query
        );

        // java17 - switch문
        String tmp = "D";
        var t = switch (tmp) {
            case "A", "B" -> "Succ";
            case "C" -> "Fail";
            case "D" -> {
                log.info("TEST");
                yield "D";
            }
            default -> "ELSE";
        };
        assertEquals("D", t);

        // java17 - Record Data Class
        RecordTest rt = new RecordTest("A", "B");
        assertEquals("A", rt.r1());
        assertEquals("B", rt.r2());
        rt.toString();
        rt.equals("C");
        rt.hashCode();
        rt.logTest();

        // java11 - Collection Factory Method
        List<String> a1 = List.of("A", "B");
        assertThrows(UnsupportedOperationException.class, () -> {
            a1.add("c");
        });
        List<String> a2 = new ArrayList<>(a1);
        a2.add("c");

        Map<String, String> b1 = Map.of("A_Key", "A_Value", "B_Key", "B_Value");
        assertThrows(UnsupportedOperationException.class, () -> {
            b1.put("C_Key", "C_Value");
        });
        Map<String, String> b2 = new HashMap<>(b1);
        b2.put("C_Key", "C_Value");

        // java11 - var 람다식 사용가능
        List<String> c = new ArrayList<>();
        c.stream().map((var x) -> x.equals("C"));
    }
}
