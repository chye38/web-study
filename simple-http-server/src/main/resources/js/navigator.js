'use strict'

function setNavigator() {
    // 날짜 세팅
    const parameter = new URLSearchParams(window.location.search);
    const date = getDate(parameter);
    const printDate = document.getElementById("printDate");

    initialize(date, printDate);
    buttonEvent(date);

    // 출력
    function initialize(date, printDate) {
        printDate.innerText = formatYearMonth(date);
    }

    function formatYearMonth(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        return `${year}-${month}`;
    }

    // 날짜 가져오기
    function getDate(parameter) {
        const date = new Date();
        const year = parameter.get("year");
        const month = parameter.get("month");

        if(year === null || month === null){
            return date;
        }
        date.setFullYear(year, (month - 1));
        return date;
    }

    // 버튼 이벤트
    function buttonEvent(date) {
        const prevMonthBtn = document.getElementById("prevMonthBtn");
        const nextMonthBtn = document.getElementById("nextMonthBtn");
        const currentMonthBtn = document.getElementById("currentMonthBtn");

        // 이전 달
        prevMonthBtn.addEventListener("click", () => {
            date.setMonth(date.getMonth() - 1);
            redirectToDate(date);
        });

        nextMonthBtn.addEventListener("click", () => {
            date.setMonth(date.getMonth() + 1);
            redirectToDate(date);
        });


        // 현재 날짜
        currentMonthBtn.addEventListener("click", () => {
            const currentDate = new Date();
            redirectToDate(currentDate);
        });
    }

    function redirectToDate(date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const encode = encodeURI(`?year=${year}&month=${month}`);
        location.href = location.pathname + encode;
    }
}

document.addEventListener("DOMContentLoaded", setNavigator);