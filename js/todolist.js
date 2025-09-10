'use strict'

function initialize() {
    const parameter = new URLSearchParams(window.location.search);
    const currentDate = getDate(parameter);
    const days = daysInMonth(currentDate);

    function makeTodoDays() {
        const todoDays = document.getElementById("todoDays");
        let item = "";
        for (let i = 1; i <= todoDays ; i++) {
            const html = `
            <div class="todoItem">
                ${makeTodoDay(i)}
                ${makeTodoForm(i)}
                ${makeTodoList(i)}
            </div>
            `;
            item += html;
        }

        todoDays.innerHTML = item;
    }

    // class todoDay
    function makeTodoDay(day) {
        return `
            <div class="todoDay">
                ${day}
            </div>
        `;
    }
    // class todoForm
    function makeTodoForm(day) {
        return `
            <div class="todoForm">
                ${form(day)}
            </div>
        `

        function form(day) {
            document.type
            return `
                <input
            `
        }

        function save() {

        }
    }
    // class todoList
    function makeTodoList(day) {
        return `
            <div class="todoList">
                ${}
            </div>
        `
    }

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

    function daysInMonth(date) {
        return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
    }
}

document.addEventListener("DOMContentLoaded", initialize);