
//更改购物车图书项的数量 +- 的点击事件
function update(bookCount,cartId){
    window.location.href="/JavaWeb/Cart?method=updateBookCount&bookCount="+bookCount +"&cartId="+cartId
}

let iptcount = document.getElementsByClassName("count-num");
    for (let i = 0; i < iptcount.length;i++){
        iptcount[i].index = i;
        let value = iptcount[i].value;//修改前的value值
        iptcount[i].onblur = function(){
            if(Number(iptcount[this.index].value) > 0 ){
                update(iptcount[this.index].value,iptcount[this.index].name);
            }else {
                alert("你输入的信息有错")
                iptcount[this.index].value=value;//如果输入信息有误把修改前的信息改回去
            }
        }
    }
