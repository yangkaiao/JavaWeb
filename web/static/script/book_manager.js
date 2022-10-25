    //点击后 才访问的地址
    function page(pageNo){
        //window:指的是整个网页
        //location:指的是网页上的地址栏对象  .href是地址栏对象的属性，给地址栏赋值的

        window.location.href="Admin?method=book_manager&pageNo="+pageNo;
    }

    //自定义页数
    let num = document.getElementById("number");
    let btn = document.getElementById("btn");
    let pagevalue = document.getElementById("pagevalue");//所有页数
    console.log(num.value)
    console.log(pagevalue.value)
    //实时监听input框
    num.oninput=function () {
        var numvalue = Number(num.value); //因为有bug，所以把字符串转成数值类型
        if(numvalue > pagevalue.value || numvalue < 1){
            btn.disabled=true; //禁用确定按钮
        }else {
            btn.disabled=false; //解开
        }
    }
    btn.onclick=function () {
        page(num.value)
    }


    // 获取当前li(页码)
    var active = document.querySelector(".active");
    var e = document.querySelector(".e");
    // 让当前页码和其上下兄弟显示
    active.style.display = "block";
    if(active.innerText == "1"){
        active.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
    }else if(active.innerText == e.innerText){
        active.previousElementSibling.style = "block";
        active.previousElementSibling.previousElementSibling.style = "block";
        active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
        active.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
    }else if(active.innerText == "2"){
        active.previousElementSibling.style = "block";
        active.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.nextElementSibling.style = "block";
    }else if(Number(active.innerText) == Number(e.innerText)-1){
        active.previousElementSibling.style = "block";
        active.previousElementSibling.previousElementSibling.style = "block";
        active.previousElementSibling.previousElementSibling.previousElementSibling.style = "block";
        active.nextElementSibling.style = "block";
    }else{
        active.previousElementSibling.previousElementSibling.style = "block";
        active.previousElementSibling.style = "block";
        active.nextElementSibling.style = "block";
        active.nextElementSibling.nextElementSibling.style = "block";
    }