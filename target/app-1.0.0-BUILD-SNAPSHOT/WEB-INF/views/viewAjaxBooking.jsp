<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="booking_process">
    <div class="option_choice_sec">
        <div class="choice_title">날짜 선택</div>
        <div class="option_box" id="option_date">
            <c:forEach var="mapi" items="${seats}" varStatus="statusi">
                <c:forEach var="mapj" items="${mapi.value}" varStatus="statusj" begin="0" end="0">
                    <c:forEach var="seatInvt" items="${mapj.value}" varStatus="statusk" begin="0" end="0">
                        <input type="radio" name="perf_date" id="perf_date${statusi.index + 1}">
                        <label for="perf_date${statusi.index + 1}" class="choice_btn label_date${statusi.index + 1}" data-labeltime="label_time${statusi.index+1}">${seatInvt.showDate}</label>
                    </c:forEach>
                </c:forEach>
            </c:forEach>

        </div>
    </div>

    <div class="option_choice_sec">
        <div class="choice_title">회차 선택</div>
        <div class="option_box" id="option_time">
            <c:forEach var="mapi" items="${seats}" varStatus="statusi">
                <c:forEach var="mapj" items="${mapi.value}" varStatus="statusj">
                    <c:forEach var="seatInvt" items="${mapj.value}" varStatus="statusk" begin="0" end="0">
                        <input type="radio" name="perf_time" id="perf_time${statusi.index+1}${statusj.index+1}">
                        <label for="perf_time${statusi.index+1}${statusj.index+1}" class="choice_btn2 label_time${statusi.index+1}" data-labelseat="label_seat${statusi.index+1}${statusj.index+1}">${seatInvt.showTime}</label>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </div>
    </div>

    <div class="option_choice_sec">
        <div class="choice_title">좌석 선택</div>
        <div class="option_box">
            <div class="seat_ment">선택한 회차의 등급별 잔여석을 확인할 수 있어요!</div>
            <div class="seat_stock_box">
                <c:forEach var="mapi" items="${seats}" varStatus="statusi">
                    <c:forEach var="mapj" items="${mapi.value}" varStatus="statusj">
                        <c:forEach var="seatInvt" items="${mapj.value}" varStatus="statusk">
                            <input type="radio" name="perf_seat" id="perf_seat${statusi.index+1}${statusj.index+1}${statusk.index+1}">
                            <label for="perf_seat${statusi.index+1}${statusj.index+1}${statusk.index+1}" class="choice_btn3 label_seat${statusi.index+1}${statusj.index+1}" data-seatcode="${seatInvt.seatInvtId}">${seatInvt.seatClass} (잔여석 : <span id="seatSpan${seatInvt.seatInvtId}" class="seatSpan">${seatInvt.seatStock}</span>석)</span></label>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
