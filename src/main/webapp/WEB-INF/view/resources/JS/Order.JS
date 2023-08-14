setInterval(show,1000);

var totalProducts = 0;
var totalPrice = 0.0;

function check(elem, price){
    if(elem.checked){
     increment(parseFloat(price));
    }
    else{
        decrement(parseFloat(price))
    }
}
function increment(price){
totalPrice+=price;
++totalProducts;
show();
}
function decrement(price){
totalPrice-=price;
--totalProducts;
show();
}
function show(){
document.getElementById("totalCount").innerHTML = totalProducts;
document.getElementById("totalPay").innerHTML = totalPrice;
}


