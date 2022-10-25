    //轮播图
    var swiper = new Swiper('.swiper-container', {
        autoplay: true,
        pagination: {
            el: '.swiper-pagination',
            dynamicBullets: true
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        }
    })



    //点击查询 筛选价格
    let minvalue = document.getElementById("leftipt");
    let maxvalue = document.getElementById("rightipt");
    let selbtn = document.getElementById("selbtn");
    selbtn.onclick = function (){
        let min = minvalue.value;
        let max = maxvalue.value;
        console.log(min)
        console.log(max)
        console.log("没进来")
        //不等于空 就是有了用价格筛选 把要查询的价格请求到服务器
        if(min != false && max != false){
            console.log("进来了")
            window.location.href="index?minprice="+min +"&maxprice=" + max;
        }else {
            window.location.href="index";
        }
    }

    //点击后 才访问的地址
    function page(pageNo){
        let min = minvalue.value;
        let max = maxvalue.value;
        //window:指的是整个网页
        //location:指的是网页上的地址栏对象  .href是地址栏对象的属性，给地址栏赋值的

        //等于空 就是没有用价格筛选 正常分页就行
        if(min == false || max == false){
            window.location.href="index?pageNo="+pageNo;
        }
        //否则就是用了价格筛选
        else {
            window.location.href="index?minprice="+min +"&maxprice=" + max + "&maxprice=" + "&pageNo=" + pageNo;
        }

    }

    //自定义页数
    let num = document.getElementById("number");
    let btn = document.getElementById("btn");
    let pagevalue = document.getElementById("pagevalue");//所有页数
    console.log(num.value)
    console.log(pagevalue.value)
    //实时监听input框
    num.oninput=function () {
        var numvalue = Number(num.value);//因为有bug，所以把字符串转成数值类型
        if(numvalue > pagevalue.value || numvalue < 1){
            console.log( num.value+"禁止了")
            btn.disabled=true; //禁用确定按钮
        }else {
            btn.disabled=false; //解开
        }
    }
    btn.onclick=function (e) {
            page(num.value)
    }



    // 获取当前li(页码) 就是li是循环出来的只设置了一个class active在谁身上谁就是当前的li（页码）
    var active = document.querySelector(".active");
    var e = document.querySelector(".e");//总页数
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


    //加入购物车
    function addCart(bookId) {
        window.location.href="Cart?method=addCart&bookId="+bookId;

    }
