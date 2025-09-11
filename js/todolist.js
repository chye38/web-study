let params = new URLSearchParams(window.location.search);
let todoDays = document.getElementById("todoDays");

let currentDate = getDate();

function getDate(){
    const date = new Date();
    const year = params.get("year");
    const month = params.get("month");
    if(year && month){
        date.setFullYear(parseInt(year), parseInt(month) - 1);
    }

    return date;
}


let todoData = {};
let savedTodos = localStorage.getItem('todo');
if(savedTodos) {
    todoData = JSON.parse(savedTodos);
}

function save() {
    localStorage.setItem('todo', JSON.stringify(todoData));
}

function render() {
    todoDays.innerHTML = "";
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth() + 1;
    const daysInMonth = new Date(year, month, 0).getDate();

    for (let i = 1; i <= daysInMonth; i++) {
        //
        let todoItem = document.createElement('div');
        todoItem.className = "todoItem";

        let todoDay = document.createElement('div');
        todoDay.className = "todoDay";
        todoDay.innerText = `${i}`;
        todoItem.appendChild(todoDay);

        //
        let todoForm = document.createElement('div');
        todoForm.className = "todoForm";

        let p1 = document.createElement('p');

        let inputDate = document.createElement('input');
        inputDate.type = "text";
        inputDate.value = `${year.toString()}-${month.toString().padStart(2, '0')}-${i.toString().padStart(2, '0')}`;
        inputDate.readOnly = true;

        p1.appendChild(inputDate);

        let p2 = document.createElement('p');

        let inputSubject = document.createElement('input');
        inputSubject.type = "text";
        inputSubject.placeholder = "할일 입력";

        p2.appendChild(inputSubject)

        let p3 = document.createElement('p');

        let addBtn = document.createElement('button');
        addBtn.textContent = "추가";
        p3.appendChild(addBtn);

        let p4 = document.createElement('p');

        let removeAllBtn = document.createElement('button');
        removeAllBtn.textContent = "전체 삭제";
        p4.append(removeAllBtn);

        // form 조립
        todoForm.appendChild(p1);
        todoForm.appendChild(p2);
        todoForm.appendChild(p3);
        todoForm.appendChild(p4);

        todoItem.appendChild(todoForm);

        //
        let todoList = document.createElement('div');
        todoList.className = "todoList";

        let todoListUl = document.createElement("ul");
        todoList.appendChild(todoListUl);

        todoItem.appendChild(todoList);

        todoDays.appendChild(todoItem);

        // todolist 출력
        let dateStr = `${year}-${month}-${i}`;
        if(todoData[dateStr]){
            todoData[dateStr].forEach((item, index) => {
                let li = document.createElement('li');
                li.textContent = item;

                todoListUl.appendChild(li);
            });
        }

        addBtn.addEventListener("click", () =>{
            const subject = inputSubject.value.trim();
            if(!subject){
                return;
            }

            if(!todoData[dateStr]) {
                todoData[dateStr] = [];
            }

            todoData[dateStr].push(subject);
            save();
            render();
        });

        removeAllBtn.addEventListener("click", () => {
            delete todoData[dateStr];
            save();
            render();
        })
    }

}

render();