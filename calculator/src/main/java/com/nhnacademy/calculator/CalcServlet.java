package com.nhnacademy.calculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Queue;

public class CalcServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String op = request.getParameter("op");

        double sum = Calculator.Calculator(num1, num2, op);
        String strOp = Operator.opToString(op);
        LocalTime time = LocalTime.now();

        ValueList value = new ValueList(sum, time);
        Storage.addStorage(value);
        Queue<ValueList> list = Storage.getStorage();

        response.setContentType("text/html");

        String body = String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>계산기</title>
                    <style>
                        .left{
                            float: left;
                            width: 500px;
                        }
                        .right{
                            float: left;
                            width: 200px;
                        }
                
                        ul{
                            list-style-type: none;
                        }
                
                        input[type=text]{
                            font-size: 30px;
                        }
                    </style>
                </head>
                <body>
                    <h1>결과 : %.4f</h1>
                    <h2>시간 : %s</h2>
                    <div class="left">
                        <h1>고급 사칙 계산기</h1>
                        <form action="/calc" method="post">
                            <ul>
                                <li><p>첫 번째 숫자:</p></li>
                                <li><input type="text" name="num1" id="num1"></li>
                                <li><p>연산자:</p></li>
                                <li>
                                    <select name="op" id="op">
                                        <option value="add">덧셈(+)</option>
                                        <option value="sub">뺄셈(-)</option>
                                        <option value="mul">곱셈(×)</option>
                                        <option value="div">나눗셈(÷)</option>
                                        <option value="sqrt">제곱근(√)</option>
                                        <option value="pow">제곱(x²)</option>
                                        <option value="mod">나머지(%%)</option>
                                        <option value="reciprocal">역수(1/x)</option>
                                    </select>
                                </li>
                                <li><br>두 번째 숫자:</li>
                                <li><input type="text" name="num2" id="num2"><p>(제곱근의 경우 비워두세요)</p></li>
                                <li>
                                    <button type="submit">계산하기</button>
                                    <button type="reset">초기화</button>
                                </li>
                            </ul>
                        </form>
                    </div>
                """, sum, time);
        // Hello
        PrintWriter out = response.getWriter();
        out.println(body);
        for (ValueList v : list) {
            out.println("<h2>결과값 : %.4f</h2>".formatted(v.getValue()));
            out.println("<h2>결과값 : %s</h2>".formatted(v.getTime()));
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
