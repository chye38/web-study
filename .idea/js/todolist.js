/*
localStorage는
(날짜, [할일들])로 저장되어야함
 */
'strict mode'

const date = new Date();

let year = date.getFullYear();
let month = date.getMonth() + 1;


function initialize() {
    const currentMonth = document.getElementById("date");
    currentMonth.innerText = `${year}-${month}`;
    todoItem(year, month);
}

function todoItem(year, month) {
    const item = document.getElementById("todo");
    // 해당 월 일수
    const days = getDaysInmonth(year, month);
    item.innerHTML = "";
    for (let i = 1; i <= days; i++) {
        const dayHtml = `
        <div class="todoItem">
            ${todoDay(i)}
            ${todoForm(i)}
            ${todoList(i)}
        </div>
        `
        item.innerHTML += dayHtml;
    }
}

// todoDay 만들기
function todoDay(day){
    let todoDay = `<div class="todoDay">${day}</div>`;
    return todoDay;
}

// todoForm 만들기
function todoForm(day){
    const inputDay = `<input type="text" name="" id="" value="${year}-${month}-${day}">`;
    const inputTodo = `<input type="text" name="" id="" placeholder="todo 입력">`;
    const btn = `<button>등록</button>`;
    let todoForm = `<div class="todoForm">
        ${inputDay}
        ${inputTodo}
        ${btn}
    </div>`;
    return todoForm;
}
// todoList 만들기
function todoList(day) {
    const listHtml = list(day);
    let todoList = `<div class="todoList">
        ${listHtml}
</div>`;
    return todoList;
}

// list가져오기
function list(day){
    let listHtml = "<ul>";
    const key = `${year}-${month}-${day}`;
    let values = localStorage.getItem(key);
    console.log(key);
    if(values){
        values = values.split(",");
        values.forEach(value => {
            listHtml += `<li>${value}</li>`;
        });
    }

    listHtml += "</ul>";

    return listHtml;
}

// 특정 연도와 월별로 일수 구하기
function getDaysInmonth(year,month){
    return new Date(year, month, 0).getDate();
}

initialize();